package com.yule.action.search;

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
import org.apache.lucene.search.WildcardQuery;
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
import com.yule.constant.FileUploadConst;
import com.yule.constant.LuceneConst;
import com.yule.constant.OrderConst;
import com.yule.constant.PageConst;
import com.yule.enumerate.DateStyle;
import com.yule.enumerate.DecimalEnum;
import com.yule.exception.YuleException;
import com.yule.mobile.query.CompanySearchQuery;
import com.yule.mongo.mobile.service.IOrdersMongo;
import com.yule.mongo.mobile.vo.OrdersHotVO;
import com.yule.pojo.AreaCity;
import com.yule.redis.constant.RedisConst;
import com.yule.redis.util.JedisUtil;
import com.yule.util.DateUtil;
import com.yule.util.DecimalUtil;
import com.yule.util.MapDistance;
import com.yule.util.MobileSearchCriteriaUtil;
import com.yule.util.NumberUtil;
import com.yule.util.PaginationUtil;


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
		String area_city_id = getAreaCookieValue();
		companyQuery.setArea_city_id(area_city_id);
		AreaCity areaCity = (AreaCity) JSONObject.toBean(JSONObject.fromObject(JedisUtil.getInstance().get(RedisConst.AREA_CITY_INFO+area_city_id)),AreaCity.class);
		companyQuery.setArea_city_name(areaCity.getName());
		try {
			BooleanQuery booleanQuery=new BooleanQuery();
			StringBuffer searchCriteriaHtml = new StringBuffer();
			StringBuffer searchtabHtml = new StringBuffer();
			searchCriteriaHtml.append(MobileSearchCriteriaUtil.getCompanyCategory(companyQuery,searchtabHtml));
			searchCriteriaHtml.append(MobileSearchCriteriaUtil.getCompanyGrade(companyQuery,searchtabHtml));
			searchCriteriaHtml.append(MobileSearchCriteriaUtil.getCompanyPointCategory(companyQuery));
			searchCriteriaHtml.append(MobileSearchCriteriaUtil.getCompanyAreaCounty(companyQuery.getArea_city_id()));
			searchCriteriaHtml.append(MobileSearchCriteriaUtil.getCompanyAreaBusiness(companyQuery.getArea_city_id()));
			request.setAttribute("searchCriteriaHtml", searchCriteriaHtml);
			request.setAttribute("searchtabHtml", searchtabHtml);
			
			if(!StringUtils.isEmpty(companyQuery.getName())){
				Term term=new Term("name","*"+companyQuery.getName()+"*");
				WildcardQuery wildcardquery=new WildcardQuery(term);
				booleanQuery.add(wildcardquery, BooleanClause.Occur.MUST);
			}
			if(!StringUtils.isEmpty(companyQuery.getArea_city_id())){
				TermQuery query=new TermQuery(new Term("area_city_id", companyQuery.getArea_city_id()));
				booleanQuery.add(query, BooleanClause.Occur.MUST);
			}
			if(null!=companyQuery.getCompany_grade()){
				BooleanQuery company_grade_Query=new BooleanQuery();
				for (String company_grade_id : companyQuery.getCompany_grade()) {
					if (!StringUtils.isEmpty(company_grade_id)) {
						TermQuery query=new TermQuery(new Term("company_grade_id", company_grade_id));
						company_grade_Query.add(query, BooleanClause.Occur.SHOULD);
						booleanQuery.add(company_grade_Query, BooleanClause.Occur.MUST);
					}
				}
			}
			if(null!=companyQuery.getCompany_category()){
				BooleanQuery company_category_Query=new BooleanQuery();
				for (String company_category_id : companyQuery.getCompany_category()) {
					if(!StringUtils.isEmpty(company_category_id)){
						TermQuery query=new TermQuery(new Term("company_category_id", company_category_id));
						company_category_Query.add(query, BooleanClause.Occur.SHOULD);
						booleanQuery.add(company_category_Query, BooleanClause.Occur.MUST);
					}
				}
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
				OrdersHotVO ordersHotVO = null;
				StringBuffer timeAgo = new StringBuffer();
				Document document = null;
	            for (int i = start; i < end; i++) {
	            	document = searcher.doc(scoreDocs[i].doc);
	            	ordersHotVO = ordersMongoImpl.findOrdersHotVO(document.get("id"));
	            	companyHtml.append("<li class=\"mui-table-view-cell mui-media\">");
        			companyHtml.append("<a href=\"/companyDetails/findCompanyDetails.do?id="+document.get("id")+"\" class=\"mui-navigate-right\">");
					companyHtml.append("<img class=\"mui-media-object mui-pull-left\" src=\""+FileUploadConst.COMPANY_IMAGE_PATH+document.get("id")+"/"+FileUploadConst.PX_150_150+FileUploadConst.COMPANY_FACE+FileUploadConst.IMAGE_TYPE+"\">");
					companyHtml.append("<div class=\"mui-media-body\">");
					companyHtml.append("<h4>"+document.get("name")+"   【"+document.get("company_grade_name")+"】</h4>");
					
					if (null!=companyQuery.getLat()&&null!=companyQuery.getLng()) {
						double company_lat = Double.valueOf(document.get("lat"));
						double company_lng = Double.valueOf(document.get("lng"));
						double distance = MapDistance.getDistance(companyQuery.getLat(), companyQuery.getLng(), company_lat, company_lng);
						companyHtml.append("<p><i class=\"icon-map-marker\"></i>  距离您"+NumberUtil.reverDoubleToKM(distance)+"</p>");
					}else{
						companyHtml.append("<p></p>");
					}
					
					if(!StringUtils.isEmpty(document.get("rebast"))){
						companyHtml.append("<p class=\"list-comment-s\"><i class=\"icon-thumbs-up\"></i><span>"+DecimalUtil.parseFloat(document.get("rebast"), DecimalEnum.FLOAT)+"折</span></p>");
					}else{
						companyHtml.append("<p class=\"list-comment-s\"><i class=\"icon-thumbs-up\"></i><span>优惠价</span></p>");
					}
	                if (null!=ordersHotVO) {
						timeAgo.append(DateUtil.DateToString(ordersHotVO.getCreate_time(), DateStyle.YYYY_MM_DD_HH_MM_SS_EN));
						companyHtml.append("<p>最新预订<i class=\"icon-time\"></i><abbr class=\"timeago\" title=\""+timeAgo+"\">"+timeAgo+"</abbr></p>");
						timeAgo.setLength(0);
	                }else{
	                	companyHtml.append("<p></p>");
	                }
					companyHtml.append("</div>");
 					companyHtml.append("</a>");
					companyHtml.append("</li>");
	            }
			}else{
				companyHtml.append("暂无娱乐场所");
			}
			pageHtml.append(PaginationUtil.getPaginationHtml(pageNo,rowCount,pageSzie));
			request.setAttribute("pageHtml", pageHtml);
			request.setAttribute("rowCount", rowCount);
			request.setAttribute("companyHtml", companyHtml);
			request.setAttribute("companyQuery", companyQuery);
		} catch (Exception e) {
			new YuleException("查询企业列表分页出现异常【searchCompanyList】",e);
			return "/index";
		}finally{
			if(null!=reader){
				reader.close();
			}
		}
		return "/search/list";
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
		String area_city_id = getAreaCookieValue();
		companyQuery.setArea_city_id(area_city_id);
		AreaCity areaCity = (AreaCity) JSONObject.toBean(JSONObject.fromObject(JedisUtil.getInstance().get(RedisConst.AREA_CITY_INFO+area_city_id)),AreaCity.class);
		companyQuery.setArea_city_name(areaCity.getName());
		try {
			BooleanQuery booleanQuery=new BooleanQuery();
			if(!StringUtils.isEmpty(companyQuery.getName())){
				Term term=new Term("name","*"+companyQuery.getName()+"*");
				WildcardQuery wildcardquery=new WildcardQuery(term);
				booleanQuery.add(wildcardquery, BooleanClause.Occur.MUST);
			}
			
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
			Sort sort = new Sort();
			if (null!=companyQuery.getOrder()) {
				if (companyQuery.getOrder()==OrderConst.ORDER_BY_POINT) {
					SortField sortFieldPoint= new SortField("company_comment_point",Type.DOUBLE,true);
					sort.setSort(sortFieldPoint);
				}
				if (companyQuery.getOrder()==OrderConst.ORDER_BY_GRADE) {
					SortField sortFieldGrade =new SortField("company_grade_id",Type.STRING_VAL,true);
					sort.setSort(sortFieldGrade);
					
				}
			}
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
				OrdersHotVO ordersHotVO = null;
				StringBuffer timeAgo = new StringBuffer();
				Document document = null;
	            for (int i = start; i < end; i++) {
	            	document = searcher.doc(scoreDocs[i].doc);;
	            	ordersHotVO = ordersMongoImpl.findOrdersHotVO(document.get("id"));
	            	companyHtml.append("<li class=\"mui-table-view-cell mui-media\">");
        			companyHtml.append("<a href=\"/companyDetails/findCompanyDetails.do?id="+document.get("id")+"\" class=\"mui-navigate-right\">");
        			companyHtml.append("<img class=\"mui-media-object mui-pull-left\" src=\""+FileUploadConst.COMPANY_IMAGE_PATH+document.get("id")+"/"+FileUploadConst.PX_150_150+FileUploadConst.COMPANY_FACE+FileUploadConst.IMAGE_TYPE+"\">");
					companyHtml.append("<div class=\"mui-media-body\">");
					companyHtml.append("<h4>"+document.get("name")+"   【"+document.get("company_grade_name")+"】</h4>");
					if (null!=companyQuery.getLat()&&null!=companyQuery.getLng()) {
						double company_lat = Double.valueOf(document.get("lat"));
						double company_lng = Double.valueOf(document.get("lng"));
						double distance = MapDistance.getDistance(companyQuery.getLat(), companyQuery.getLng(), company_lat, company_lng);
						companyHtml.append("<p><i class=\"icon-map-marker\"></i>  距离您"+NumberUtil.reverDoubleToKM(distance)+"</p>");
					}else{
						companyHtml.append("<p></p>");
					}
					if(!StringUtils.isEmpty(document.get("rebast"))){
						companyHtml.append("<p class=\"list-comment-s\"><i class=\"icon-thumbs-up\"></i><span>"+DecimalUtil.parseFloat(document.get("rebast"), DecimalEnum.FLOAT)+"折</span></p>");
					}else{
						companyHtml.append("<p class=\"list-comment-s\"><i class=\"icon-thumbs-up\"></i><span>优惠价</span></p>");
					}
	                if (null!=ordersHotVO) {
						timeAgo.append(DateUtil.DateToString(ordersHotVO.getCreate_time(), DateStyle.YYYY_MM_DD_HH_MM_SS_EN));
						companyHtml.append("<p>最新预订<i class=\"icon-time\"></i><abbr class=\"timeago\" title=\""+timeAgo+"\">"+timeAgo+"</abbr></p>");
						timeAgo.setLength(0);
	                }else{
	                	companyHtml.append("<p></p>");
	                }
					companyHtml.append("</div>");
					companyHtml.append("</a>");
					companyHtml.append("</li>");

	            }
	            pageHtml.append(PaginationUtil.getPaginationHtml(pageNo,rowCount,pageSzie));
			}else{
				companyHtml.append("暂无娱乐场所");
			}
			obj.put("status", true);
			obj.put("rowCount", rowCount);
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
