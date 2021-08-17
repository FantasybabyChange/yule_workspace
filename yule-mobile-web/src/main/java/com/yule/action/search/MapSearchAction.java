package com.yule.action.search;

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
import org.apache.lucene.store.FSDirectory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.yule.common.BaseAction;
import com.yule.constant.ErrorConst;
import com.yule.constant.FileUploadConst;
import com.yule.constant.LuceneConst;
import com.yule.exception.YuleException;
import com.yule.search.query.CompanySearchQuery;

@Controller
@Scope("prototype")
@RequestMapping("/mapSearchAction")
public class MapSearchAction extends BaseAction{
	@RequestMapping(value = "/searchCompanyMap",method = RequestMethod.POST)
	public String searchCompanyMap(@ModelAttribute("companyQuery")CompanySearchQuery companyQuery) throws Exception {
		JSONObject obj = new JSONObject();
		obj.put("status", false);
		IndexReader reader = DirectoryReader.open(FSDirectory.open(new File(LuceneConst.INDEX_SEARCH_COMPANY_FILE_PATH)));
		IndexSearcher searcher = new IndexSearcher(reader);
		if (StringUtils.isEmpty(companyQuery.getArea_city_id())||StringUtils.isEmpty(companyQuery.getArea_city_name())) {
			companyQuery.setArea_city_id("610100");
			companyQuery.setArea_city_name("西安");
		}
		try {
			BooleanQuery booleanQuery=new BooleanQuery();
			if(!StringUtils.isEmpty(companyQuery.getArea_city_id())){
				TermQuery query=new TermQuery(new Term("area_city_id", companyQuery.getArea_city_id()));
				booleanQuery.add(query, BooleanClause.Occur.MUST);
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
			if (!StringUtils.isEmpty(companyQuery.getCompany_point_category())) {
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
			TopDocs topDocs=null;
			if(filter!=null){
				topDocs=searcher.search(booleanQuery,filter,reader.numDocs());
			}else{
				topDocs=searcher.search(booleanQuery,reader.numDocs());
			}
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
			obj.put("companyMarker", jsonArray.toString());
			obj.put("status", true);
		} catch (Exception e) {
			new YuleException("查询企业列表分页出现异常【searchCompanyListAjax】",e);
			e.printStackTrace();
		}finally{
			if(null!=reader){
				reader.close();
			}
			outputResult(obj.toString());
			obj.clear();
			obj = null;
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
}