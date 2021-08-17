package com.yule.action;

import java.io.File;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.Term;
import org.apache.lucene.search.BooleanClause;
import org.apache.lucene.search.BooleanQuery;
import org.apache.lucene.search.Filter;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.NumericRangeFilter;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.Sort;
import org.apache.lucene.search.SortField;
import org.apache.lucene.search.SortField.Type;
import org.apache.lucene.search.TermQuery;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.search.WildcardQuery;
import org.apache.lucene.store.FSDirectory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.yule.common.BaseAction;
import com.yule.constant.DoMainConst;
import com.yule.constant.ErrorConst;
import com.yule.constant.FileUploadConst;
import com.yule.constant.LuceneConst;
import com.yule.constant.PageConst;
import com.yule.enumerate.DecimalEnum;
import com.yule.exception.YuleException;
import com.yule.search.query.CompanyQuery;
import com.yule.search.query.CompanySearchQuery;
import com.yule.util.DecimalUtil;
import com.yule.util.MapSearchCriteriaUtil;
import com.yule.util.PaginationUtil;

@Controller
@Scope("prototype")
@RequestMapping("/mapSearchAction")
public class MapSearchAction extends BaseAction{

	@RequestMapping(value = "/searchCompany",method = RequestMethod.GET)
	public String searchCompany(@ModelAttribute("companyQuery")CompanySearchQuery companyQuery,@RequestParam(value="area_city_name",required=false)String area_city_name)throws Exception{
		if(companyQuery == null ||StringUtils.isEmpty(area_city_name)||StringUtils.isEmpty(companyQuery.getArea_city_id())){
			return "redirect:"+DoMainConst.PC_URL;
		}else{
			List<String> company_categorys = companyQuery.getCompany_category();
			JSONObject jo = new JSONObject();
			if(company_categorys != null && company_categorys.size() > 0){
				JSONArray category = JSONArray.fromObject(company_categorys);
				jo.put("company_category", category);
			}
			List<String> company_grades = companyQuery.getCompany_grade();
			if (company_grades != null && company_grades.size() > 0) {
				jo.put("company_grade", JSONArray.fromObject(company_grades));
			}
			Double company_point_category = companyQuery.getCompany_point_category();
			if(company_point_category != null){
				JSONArray ja = new JSONArray();
				ja.add(0,company_point_category);
				jo.put("company_point_category", company_point_category);
			}
			request.setAttribute("criteriaName", jo.toString());
			jo.clear();
			request.setAttribute("area_city_id", companyQuery.getArea_city_id());
			request.setAttribute("area_city_name", area_city_name);
			return "map-list";
		}
	}
	@RequestMapping(value = "/searchCompanyList",method = RequestMethod.POST)
	public String searchCompanyList(@ModelAttribute("companyQuery")CompanyQuery companyQuery,Integer pageNo) throws Exception {
		JSONObject object = new JSONObject();
		object.put("status", ErrorConst.STATUS_ERROR);
		if(null==pageNo||pageNo<1){
			pageNo = 1;
		}
		int pageSzie = PageConst.PAGE_SIZE_TEN;
		IndexReader reader = DirectoryReader.open(FSDirectory.open(new File(LuceneConst.INDEX_SEARCH_COMPANY_FILE_PATH)));
		IndexSearcher searcher = new IndexSearcher(reader);
		int pageSum = reader.numDocs();
		int pageCount = pageSum%pageSzie == 0 ?pageSum/pageSzie:pageSum/pageSzie+1;
		if(pageNo >= pageCount){
			pageNo = pageCount;
		}
		try {
			BooleanQuery booleanQuery=new BooleanQuery();
			if(!StringUtils.isEmpty(companyQuery.getCompany_id())){
				TermQuery query=new TermQuery(new Term("id", companyQuery.getCompany_id()));
				booleanQuery.add(query, BooleanClause.Occur.MUST);
			}
			if(!StringUtils.isEmpty(companyQuery.getArea_business_id())){
				TermQuery query=new TermQuery(new Term("area_business_id", companyQuery.getArea_business_id()));
				booleanQuery.add(query, BooleanClause.Occur.MUST);
			}
			if(!StringUtils.isEmpty(companyQuery.getArea_city_id())){
				TermQuery query=new TermQuery(new Term("area_city_id", companyQuery.getArea_city_id()));
				booleanQuery.add(query, BooleanClause.Occur.MUST);
			}
			if(!StringUtils.isEmpty(companyQuery.getArea_county_id())){
				TermQuery query=new TermQuery(new Term("area_county_id", companyQuery.getArea_county_id()));
				booleanQuery.add(query, BooleanClause.Occur.MUST);
			}
			if(!StringUtils.isEmpty(companyQuery.getArea_province_id())){
				TermQuery query=new TermQuery(new Term("area_province_id", companyQuery.getArea_province_id()));
				booleanQuery.add(query, BooleanClause.Occur.MUST);
			}
			if(!StringUtils.isEmpty(companyQuery.getCompany_grade_id())){
				TermQuery query=new TermQuery(new Term("company_grade_id", companyQuery.getCompany_grade_id()));
				booleanQuery.add(query, BooleanClause.Occur.MUST);
			}
			if(!StringUtils.isEmpty(companyQuery.getPinyin())){
				TermQuery query=new TermQuery(new Term("pinyin", companyQuery.getPinyin()));
				booleanQuery.add(query, BooleanClause.Occur.MUST);
			}
			if(!StringUtils.isEmpty(companyQuery.getName())){
				TermQuery query=new TermQuery(new Term("name", companyQuery.getName()));
				booleanQuery.add(query, BooleanClause.Occur.MUST);
			}
			Sort sort = new Sort(new SortField("create_time",Type.SCORE,true));
			int end = pageSzie*pageNo;
			TopDocs topDocs=searcher.search(booleanQuery,end,sort);
			ScoreDoc[] scoreDocs = topDocs.scoreDocs;
			StringBuffer companyHtml = new StringBuffer();
			if(null!=scoreDocs&&scoreDocs.length>0){
				int start=(pageNo-1)*pageSzie;  
				if(end > scoreDocs.length){
					end = scoreDocs.length;
				}
				int numCount = pageSzie*(pageNo-1);
				Document document = null;
	            for (int i = start; i < end; i++) {
	            	numCount++;
		            document = searcher.doc(scoreDocs[i].doc);
		            companyHtml.append("<div class=\"side_list_item\" data-id=\""+document.get("id")+"\">");
		            companyHtml.append("<div class=\"list_item_cont clearfix\">");
		            companyHtml.append("<span class=\"icon_num\">"+numCount+"</span>");
		            companyHtml.append("<p class=\"item_title\"><a href=\"http://detail.yuleing.com/company/findCompanyDetails.do?id="+document.get("id")+"\" class=\"name\" title=\""+document.get("name")+"\">"+document.get("name")+"【"+document.get("company_grade_name")+"】</a></p>");
		            companyHtml.append("<div class=\"list_item_b cf\"><p class=\"score\">");
		            //companyHtml.append("<span class=\"b\">"+document.get("company_comment_point")+"</span>分");	
		            companyHtml.append("&nbsp;&nbsp;</p>");
		            companyHtml.append("<span class=\"price\">");
		            if (StringUtils.isEmpty(document.get("rebast"))) {
		            	companyHtml.append("<dfn></dfn>优惠价</span>");	
					}else{
						companyHtml.append("<dfn></dfn><i>"+DecimalUtil.parseFloat(document.get("rebast"),DecimalEnum.FLOAT)+"</i>折</span>");
					}
		            companyHtml.append("</div>");
		            companyHtml.append("</div>");
		            companyHtml.append("</div>");
				}
	            companyHtml.append(PaginationUtil.getMapPaginationHtml(pageNo,pageCount));
			}else{
				companyHtml.append("暂无娱乐场所");
			}
			object.put("companyHtml", companyHtml.toString());
			object.put("status", ErrorConst.STATUS_SUCCESS);
		} catch (Exception e) {
			new YuleException("查询企业列表分页出现异常【searchCompanyList】",e);
		}finally{
			if(null!=reader){
				reader.close();
			}
			outputResult(object.toString());
		}
		return null;
	}
	@RequestMapping(value = "/searchCompanyMap",method = RequestMethod.POST)
	public String searchCompanyMap(@ModelAttribute("companyQuery")CompanySearchQuery companyQuery) throws Exception {
		JSONObject object = new JSONObject();
		object.put("status", ErrorConst.STATUS_ERROR);
		IndexReader reader = DirectoryReader.open(FSDirectory.open(new File(LuceneConst.INDEX_SEARCH_COMPANY_FILE_PATH)));
		IndexSearcher searcher = new IndexSearcher(reader);
		try {
			BooleanQuery booleanQuery=new BooleanQuery();
			if(!StringUtils.isEmpty(companyQuery.getArea_city_id())){
				TermQuery query=new TermQuery(new Term("area_city_id", companyQuery.getArea_city_id()));
				booleanQuery.add(query, BooleanClause.Occur.MUST);
			}
			if(!StringUtils.isEmpty(companyQuery.getName())){
				Term term=new Term("name","*"+companyQuery.getName()+"*");
				WildcardQuery wildcardquery=new WildcardQuery(term);
				booleanQuery.add(wildcardquery, BooleanClause.Occur.MUST);
			}
			if(null!=companyQuery.getCompany_grade()){
				BooleanQuery company_grade_Query=new BooleanQuery();
				for (String company_grade_id : companyQuery.getCompany_grade()) {
					TermQuery query=new TermQuery(new Term("company_grade_id", company_grade_id));
					company_grade_Query.add(query, BooleanClause.Occur.SHOULD);
				}
				booleanQuery.add(company_grade_Query, BooleanClause.Occur.MUST);
			}
			if(null!=companyQuery.getCompany_category()){
				BooleanQuery company_category_Query=new BooleanQuery();
				for (String company_category_id : companyQuery.getCompany_category()) {
					TermQuery query=new TermQuery(new Term("company_category_id", company_category_id));
					company_category_Query.add(query, BooleanClause.Occur.SHOULD);
				}
				booleanQuery.add(company_category_Query, BooleanClause.Occur.MUST);
			}
			Filter filter = null;
			if (null!=companyQuery.getCompany_point_category()) {
				filter = NumericRangeFilter.newDoubleRange("company_comment_point", companyQuery.getCompany_point_category(), 10.00, true, true);
			}
			
			if(null!=companyQuery.getArea_county()||null!=companyQuery.getArea_business()){
				BooleanQuery area_county_query=new BooleanQuery();
				List<String> areaCounties= companyQuery.getArea_county();
				List<String> areaBusinesses=  companyQuery.getArea_business();
				if (null!=areaCounties&&areaCounties.size()>0) {
					for (String area_county_id : areaCounties) {
						TermQuery query=new TermQuery(new Term("area_county_id", area_county_id));
						area_county_query.add(query, BooleanClause.Occur.SHOULD);
					}
					areaCounties.clear();
					areaCounties = null;
				}
				if (null!=areaBusinesses&&areaBusinesses.size()>0) {
					for(String area_business_id:areaBusinesses){
						TermQuery query=new TermQuery(new Term("area_business_id", area_business_id));
						area_county_query.add(query, BooleanClause.Occur.SHOULD);
					}
					areaBusinesses.clear();
					areaBusinesses = null;
				}
				booleanQuery.add(area_county_query, BooleanClause.Occur.MUST);
			}

			TopDocs topDocs = null;
			if(null!=filter){
				topDocs=searcher.search(booleanQuery,filter,reader.numDocs());
			}else{
				topDocs=searcher.search(booleanQuery,reader.numDocs());
			}
			 topDocs=searcher.search(booleanQuery,filter,reader.numDocs());
			ScoreDoc[] scoreDocs = topDocs.scoreDocs;
			JSONArray jsonArray = new JSONArray();
			if(null!=scoreDocs&&scoreDocs.length>0){
				for (ScoreDoc scoreDoc : scoreDocs) {
					Document doc = searcher.doc(scoreDoc.doc);
					JSONObject json = new JSONObject();
					json.put("companyId", doc.get("id"));
					json.put("companyName", doc.get("name"));
					json.put("lng", doc.get("lng"));
					json.put("lat", doc.get("lat"));
					jsonArray.add(json);
				}
			}
			object.put("companyMarker", jsonArray.toString());
			object.put("status", ErrorConst.STATUS_SUCCESS);
		} catch (Exception e) {
			new YuleException("查询企业列表分页出现异常【searchCompanyList】",e);
		}finally{
			if(null!=reader){
				reader.close();
			}
			outputResult(object.toString());
		}
		return null;
	}
	@RequestMapping(value = "/searchCompanyById",method = RequestMethod.POST)
	public String searchCompanyById(@RequestParam(value="companyId",required=false)String companyId) throws Exception {
		JSONObject object = new JSONObject();
		object.put("status", ErrorConst.STATUS_ERROR);
		IndexReader reader = DirectoryReader.open(FSDirectory.open(new File(LuceneConst.INDEX_SEARCH_COMPANY_FILE_PATH)));
		IndexSearcher searcher = new IndexSearcher(reader);
		try {
			BooleanQuery booleanQuery=new BooleanQuery();
			TermQuery query=new TermQuery(new Term("id",companyId));
			booleanQuery.add(query, BooleanClause.Occur.MUST);
			Sort sort = new Sort(new SortField("create_time",Type.SCORE,true));
			TopDocs topDocs=searcher.search(booleanQuery,reader.numDocs(),sort);
			ScoreDoc[] scoreDocs = topDocs.scoreDocs;
			if(null!=scoreDocs&&scoreDocs.length>0){
				for (ScoreDoc scoreDoc : scoreDocs) {
					Document doc = searcher.doc(scoreDoc.doc);
					String image_path = FileUploadConst.COMPANY_IMAGE_PATH+doc.get("id")+"/"+FileUploadConst.PX_50_50+FileUploadConst.COMPANY_FACE+FileUploadConst.IMAGE_TYPE;
					String business_name = doc.get("area_business_name");
					if(business_name == null ){
						business_name = "";
					}
					String companyAddress = doc.get("area_province_name")+"省"+doc.get("area_city_name")+"市"+doc.get("area_county_name")+business_name+doc.get("address_detail");
					String companyName = doc.get("name");
					object.put("image_path", image_path);
					object.put("companyAddress", companyAddress);
					object.put("companyName", companyName);
					object.put("company_id", companyId);
				}
			}
			object.put("status", ErrorConst.STATUS_SUCCESS);
		} catch (Exception e) {
			new YuleException("查询企业列表分页出现异常【searchCompanyList】",e);
		}finally{
			if(null!=reader){
				reader.close();
			}
			outputResult(object.toString());
		}
		return null;
	}
	@RequestMapping(value = "/findCriteriaHtml",method = RequestMethod.POST)
	public String findCriteriaHtml(@ModelAttribute("companyQuery")CompanySearchQuery companyQuery) throws Exception {
		JSONObject object = new JSONObject();
		object.put("status", ErrorConst.STATUS_ERROR);
		try {
			StringBuffer names = new StringBuffer();
			object.put("companyCategoryHTML", MapSearchCriteriaUtil.getCompanyCategory(companyQuery.getCompany_category(),names).toString());
			if (names.length() < 1) {
				names.append("不限,");
			}
			object.put("companyCategoryName", names.toString());
			names.setLength(0);
			object.put("companyGradeHTML", MapSearchCriteriaUtil.getCompanyGrade(companyQuery.getCompany_grade(),names).toString());
			if (names.length() < 1) {
				names.append("不限,");
			}
			object.put("companyGradeName", names.toString());
			names.setLength(0);
			object.put("companyPointCategory",MapSearchCriteriaUtil.getCompanyPointCategory(companyQuery.getCompany_point_category(),names).toString());
			if (names.length() < 1) {
				names.append("不限,");
			}
			object.put("companyPointName", names.toString());
			names.setLength(0);
			object.put("hotCityHTML", MapSearchCriteriaUtil.findAreaCityNav().toString());
			object.put("status", ErrorConst.STATUS_SUCCESS);
		} catch (Exception e) {
			new YuleException("查询企业列表分页出现异常【searchCompanyList】",e);
		}finally{
			outputResult(object.toString());
		}
		return null;
	}
}