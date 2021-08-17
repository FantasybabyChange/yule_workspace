package com.yule.action;

import java.io.File;
import java.util.List;

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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.yule.common.BaseAction;
import com.yule.constant.ActionReturnConst;
import com.yule.constant.DoMainConst;
import com.yule.constant.FileUploadConst;
import com.yule.constant.LuceneConst;
import com.yule.constant.PageConst;
import com.yule.enumerate.DateStyle;
import com.yule.enumerate.DecimalEnum;
import com.yule.exception.YuleException;
import com.yule.mongo.search.service.IOrdersMongo;
import com.yule.mongo.search.vo.OrdersHotVO;
import com.yule.search.query.CompanySearchQuery;
import com.yule.util.DateUtil;
import com.yule.util.DecimalUtil;
import com.yule.util.LucenePage;
import com.yule.util.PaginationUtil;
import com.yule.util.SearchCriteriaUtil;


@Controller
@Scope("prototype")
@RequestMapping("/search")
public class SearchAction extends BaseAction{
	
	@Autowired
	private IOrdersMongo ordersMongoImpl;
	
	@RequestMapping(value = "/searchCompany",method = RequestMethod.GET)
	public String searchCompanyList(@ModelAttribute("companyQuery")CompanySearchQuery companyQuery) throws Exception {
		int pageNo = 1;
		StringBuffer companyHtml = new StringBuffer();
		StringBuffer pageHtml = new StringBuffer();
		int pageSzie = PageConst.PAGE_SIZE_TEN;
		int rowCount = 0;
		IndexReader reader = DirectoryReader.open(FSDirectory.open(new File(LuceneConst.INDEX_SEARCH_COMPANY_FILE_PATH)));
		IndexSearcher searcher = new IndexSearcher(reader);
		LucenePage lucenePage = new LucenePage();
		if (StringUtils.isEmpty(companyQuery.getArea_city_id())||StringUtils.isEmpty(companyQuery.getArea_city_name())) {
			companyQuery.setArea_city_id("610100");
			companyQuery.setArea_city_name("西安");
		}
		try {
			BooleanQuery booleanQuery=new BooleanQuery();
			StringBuffer searchCriteriaHtml = new StringBuffer();
			searchCriteriaHtml.append(SearchCriteriaUtil.getCompanyGrade(companyQuery));
			searchCriteriaHtml.append(SearchCriteriaUtil.getCompanyCategory(companyQuery));
			searchCriteriaHtml.append(SearchCriteriaUtil.getCompanyPointCategory(companyQuery));
			searchCriteriaHtml.append(SearchCriteriaUtil.getCompanyAreaCounty(companyQuery));
			searchCriteriaHtml.append(SearchCriteriaUtil.getCompanyAreaBusiness(companyQuery.getArea_city_id()));
			request.setAttribute("searchCriteriaHtml", searchCriteriaHtml);
			
			if(!StringUtils.isEmpty(companyQuery.getArea_city_id())){
				TermQuery query=new TermQuery(new Term("area_city_id", companyQuery.getArea_city_id()));
				booleanQuery.add(query, BooleanClause.Occur.MUST);
			}
/*			if(!StringUtils.isEmpty(companyQuery.getName())){
				Term term=new Term("name","*"+companyQuery.getName()+"*");
				WildcardQuery wildcardquery=new WildcardQuery(term);
				booleanQuery.add(wildcardquery, BooleanClause.Occur.MUST);
			}*/
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
				if (null!=areaCounties&&areaCounties.size()>0) {
					for (String area_county_id : areaCounties) {
						TermQuery query=new TermQuery(new Term("area_county_id", area_county_id));
						area_county_query.add(query, BooleanClause.Occur.SHOULD);
					}
					areaCounties.clear();
					areaCounties = null;
				}
				booleanQuery.add(area_county_query, BooleanClause.Occur.MUST);
			}
			int end = pageSzie;
			TopDocs topDocs =null;
			if(filter!=null){
				topDocs=searcher.search(booleanQuery,filter,end);
			}else{
				topDocs=searcher.search(booleanQuery,end);
			}
			ScoreDoc[] scoreDocs = topDocs.scoreDocs;
			if(null!=scoreDocs&&scoreDocs.length>0){
				int start=(pageNo-1)*pageSzie;  
				if(end > scoreDocs.length){
					end = scoreDocs.length;
				}
				rowCount = topDocs.totalHits;
				lucenePage.setPageSize(pageSzie);
				lucenePage.setPageNo(pageNo);
				lucenePage.setRowCount(rowCount);
				OrdersHotVO ordersHotVO = null;
				StringBuffer timeAgo = new StringBuffer();
				Document document = null;
	            for (int i = start; i < end; i++) {
	            	document = searcher.doc(scoreDocs[i].doc);
	            	ordersHotVO = ordersMongoImpl.findOrdersHotVO(document.get("id"));
	                companyHtml.append("<div class=\"sr_item clearfix\">");
	                companyHtml.append("<div class=\"sr_item_photo pull-left\">");
	                companyHtml.append("<img class=\"hotel_image\" src=\""+FileUploadConst.COMPANY_IMAGE_PATH+document.get("id")+"/"+FileUploadConst.PX_150_150+FileUploadConst.COMPANY_FACE+FileUploadConst.IMAGE_TYPE+"\" width=\"150\" height=\"150\" ></div>");
	                companyHtml.append("<div class=\"sr_item_content sr_item_content-w650\">");
	                companyHtml.append("<div class=\"recommendation-item-right\">");
	                if(!StringUtils.isEmpty(document.get("rebast"))){
	                	companyHtml.append("<p class=\"f20 index-scorewordspan mb0\">"+DecimalUtil.parseFloat(document.get("rebast"), DecimalEnum.FLOAT)+"折</p>");
	                }else{
	                	companyHtml.append("<p class=\"f20 index-scorewordspan mb0\">优惠价</p>");
	                }
	                companyHtml.append("<a href=\"http://detail.yuleing.com/company/findCompanyDetails.do?id="+document.get("id")+"\" class=\"b-button index-order-btn mt10\">现在就预订</a></div>");
	                companyHtml.append("<p><a href=\"http://detail.yuleing.com/company/findCompanyDetails.do?id="+document.get("id")+"\" class=\"history-list-title\">"+document.get("name")+"   【"+document.get("company_grade_name")+"】</a></p>");
	                companyHtml.append("<span class=\"feataddress\">"+document.get("address_detail")+"</span>");
	                if (null!=ordersHotVO) {
	                	companyHtml.append("<p class=\"sr_no_desc_users\">已有"+ordersHotVO.getOrders_count()+"位客人预订</p>");
						timeAgo.append(DateUtil.DateToString(ordersHotVO.getCreate_time(), DateStyle.YYYY_MM_DD_HH_MM_SS_EN));
						companyHtml.append("<span class=\"lastbooking\">最新的预订：<abbr class=\"timeago\" title=\""+timeAgo+"\">"+timeAgo+"</abbr></span>");
						timeAgo.setLength(0);
	                }
	                String product_count=document.get("product_count");
	                if (null!=product_count&&Integer.valueOf(product_count)>0) {
	                	companyHtml.append("<p><a href=\"http://detail.yuleing.com/company/findCompanyDetails.do?id="+document.get("id")+"\"><strong>另外"+document.get("product_count")+"种客房类型</strong></a> <i class=\"b-sprite inline-block arrow_grey_sm\"></i></p>");
					}
	                companyHtml.append("</div></div>");
	            }
	            pageHtml.append(PaginationUtil.getPaginationHtml(lucenePage));    
			}else{
				companyHtml.append("暂无娱乐场所");
			}
			request.setAttribute("area_city_id", companyQuery.getArea_city_id());
			request.setAttribute("area_city_name", companyQuery.getArea_city_name());
			request.setAttribute("pageHtml", pageHtml);
			request.setAttribute("companyHtml", companyHtml);
		} catch (Exception e) {
			new YuleException("查询企业列表分页出现异常【searchCompanyList】",e);
			return ActionReturnConst.REDIRECT+DoMainConst.PC_URL;
		}finally{
			if(null!=reader){
				reader.close();
			}
		}
		return "/search-list";
	}
	
	@RequestMapping(value = "/searchCompanyListAjax",method = RequestMethod.POST)
	public String searchCompanyListAjax(@ModelAttribute("companyQuery")CompanySearchQuery companyQuery,@RequestParam(value = "pageNo", required = false)Integer pageNo) throws Exception {
		if(null==pageNo||pageNo<1){
			pageNo = 1;
		}
		JSONObject obj = new JSONObject();
		obj.put("status", false);
		StringBuffer companyHtml = new StringBuffer();
		StringBuffer pageHtml = new StringBuffer();
		int pageSzie = PageConst.PAGE_SIZE_TEN;
		int rowCount = 0;
		IndexReader reader = DirectoryReader.open(FSDirectory.open(new File(LuceneConst.INDEX_SEARCH_COMPANY_FILE_PATH)));
		IndexSearcher searcher = new IndexSearcher(reader);
		if (StringUtils.isEmpty(companyQuery.getArea_city_id())||StringUtils.isEmpty(companyQuery.getArea_city_name())) {
			companyQuery.setArea_city_id("610100");
			companyQuery.setArea_city_name("西安");
		}
		LucenePage lucenePage = new LucenePage();
		try {
			BooleanQuery booleanQuery=new BooleanQuery();
			if(!StringUtils.isEmpty(companyQuery.getArea_city_id())){
				TermQuery query=new TermQuery(new Term("area_city_id", companyQuery.getArea_city_id()));
				booleanQuery.add(query, BooleanClause.Occur.MUST);
			}
/*			if(!StringUtils.isEmpty(companyQuery.getName())){
				Term term=new Term("name","*"+companyQuery.getName()+"*");
				WildcardQuery wildcardquery=new WildcardQuery(term);
				booleanQuery.add(wildcardquery, BooleanClause.Occur.MUST);
			}*/
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
			Sort sort = new Sort();
			if (StringUtils.isEmpty(companyQuery.getSort_company_grade())) {
				companyQuery.setSort_company_grade(true);
			}
			SortField sortFieldGrade =new SortField("company_grade_id",Type.STRING_VAL,companyQuery.getSort_company_grade());
			if (StringUtils.isEmpty(companyQuery.getSort_company_point())) {
				companyQuery.setSort_company_point(true);
			}
			SortField sortFieldPoint= new SortField("company_comment_point",Type.DOUBLE,companyQuery.getSort_company_point());
			sort.setSort(sortFieldGrade,sortFieldPoint);
			int end = pageSzie*pageNo;
			TopDocs topDocs=null;
			if(filter!=null){
				topDocs=searcher.search(booleanQuery,filter,end,sort);
			}else{
				topDocs=searcher.search(booleanQuery,end,sort);
			}
			ScoreDoc[] scoreDocs = topDocs.scoreDocs;
			if(null!=scoreDocs&&scoreDocs.length>0){
				int start=(pageNo-1)*pageSzie;  
				if(end > scoreDocs.length){
					end = scoreDocs.length;
				}
				rowCount = topDocs.totalHits;
				lucenePage.setPageSize(pageSzie);
				lucenePage.setPageNo(pageNo);
				lucenePage.setRowCount(rowCount);
				OrdersHotVO ordersHotVO = null;
				StringBuffer timeAgo = new StringBuffer();
	            for (int i = start; i < end; i++) {
	            	Document document = searcher.doc(scoreDocs[i].doc);
	            	ordersHotVO = ordersMongoImpl.findOrdersHotVO(document.get("id"));
	                companyHtml.append("<div class=\"sr_item clearfix\">");
	                companyHtml.append("<div class=\"sr_item_photo pull-left\">");
	                companyHtml.append("<img class=\"hotel_image\" src=\""+FileUploadConst.COMPANY_IMAGE_PATH+document.get("id")+"/"+FileUploadConst.PX_150_150+FileUploadConst.COMPANY_FACE+FileUploadConst.IMAGE_TYPE+"\"  height=\"150\" width=\"150\" ></div>");
	                companyHtml.append("<div class=\"sr_item_content sr_item_content-w650\">");
	                companyHtml.append("<div class=\"recommendation-item-right\">");
	                if(!StringUtils.isEmpty(document.get("rebast"))){
	                	companyHtml.append("<p class=\"f20 index-scorewordspan mb0\">"+DecimalUtil.parseFloat(document.get("rebast"), DecimalEnum.FLOAT)+"折</p>");
	                }else{
	                	companyHtml.append("<p class=\"f20 index-scorewordspan mb0\">优惠价</p>");
	                }

	                companyHtml.append("<a href=\""+DoMainConst.DETAIL_URL+"/company/findCompanyDetails.do?id="+document.get("id")+"\" class=\"b-button index-order-btn mt10\">现在就预订</a></div>");
	                companyHtml.append("<p><a href=\""+DoMainConst.DETAIL_URL+"/company/findCompanyDetails.do?id="+document.get("id")+"\" class=\"history-list-title\">"+document.get("name")+"   【"+document.get("company_grade_name")+"】</a></p>");
	                companyHtml.append("<span class=\"feataddress\">"+document.get("address_detail")+"</span>");
	                if (null!=ordersHotVO) {
	                	companyHtml.append("<p class=\"sr_no_desc_users\">已有"+ordersHotVO.getOrders_count()+"位客人预订</p>");
						timeAgo.append(DateUtil.DateToString(ordersHotVO.getCreate_time(), DateStyle.YYYY_MM_DD_HH_MM_SS_EN));
						companyHtml.append("<span class=\"lastbooking\">最新的预订：<abbr class=\"timeago\" title=\""+timeAgo+"\">"+timeAgo+"</abbr></span>");
						timeAgo.setLength(0);
	                }
	               String product_count=document.get("product_count");
	                if (null!=product_count&&Integer.valueOf(product_count)>0) {
	                	companyHtml.append("<p><a href=\"http://detail.yuleing.com/company/findCompanyDetails.do?id="+document.get("id")+"\"><strong>另外"+document.get("product_count")+"种客房类型</strong></a> <i class=\"b-sprite inline-block arrow_grey_sm\"></i></p>");
					}
	                companyHtml.append("</div></div>");
	            }
	            pageHtml.append(PaginationUtil.getPaginationHtml(lucenePage));    
			}else{
				companyHtml.append("暂无娱乐场所");
			}
			obj.put("status", true);
			obj.put("pageHtml", pageHtml.toString());
			obj.put("listHtml", companyHtml.toString());
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
	
}
