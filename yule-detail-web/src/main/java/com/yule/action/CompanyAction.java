package com.yule.action;


import java.io.File;
import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.Term;
import org.apache.lucene.search.BooleanClause;
import org.apache.lucene.search.BooleanQuery;
import org.apache.lucene.search.IndexSearcher;
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
import com.yule.constant.DoMainConst;
import com.yule.constant.ErrorConst;
import com.yule.constant.FileUploadConst;
import com.yule.constant.JSONConst;
import com.yule.constant.LuceneConst;
import com.yule.constant.PageConst;
import com.yule.constant.PayConst;
import com.yule.detail.query.CompanyDistanceQuery;
import com.yule.detail.service.ICompanyService;
import com.yule.detail.vo.CompanyDistanceVO;
import com.yule.detail.vo.CompanyVO;
import com.yule.exception.YuleException;
import com.yule.mongo.detail.service.IOrdersMongo;
import com.yule.mongo.vo.CountVO;
import com.yule.redis.constant.RedisConst;
import com.yule.redis.util.JedisUtil;
import com.yule.runnable.UserBrowseRunnable;
import com.yule.search.query.CompanySearchQuery;
import com.yule.util.NumberUtil;
import com.yule.vo.CompanyCategoryCountVO;
import com.yule.vo.UserLoginVO;
@Controller
@Scope("prototype")
@RequestMapping("/company")
public class CompanyAction extends BaseAction {
	
	@Autowired
	private ICompanyService companyServiceImpl;
	@Autowired
	private IOrdersMongo ordersMongoImpl;
	
	@RequestMapping(value="/findCompanyDetails",method=RequestMethod.GET)
	public String findCompanyDetails(@RequestParam(value="id",required=false)String id)throws Exception{
		try {
			CompanyVO companyVO = this.companyServiceImpl.findCompanyVOById(id);
			if (companyVO != null) {
				if(companyVO.getPay_type() != null){
					companyVO.setPay_type_name(PayConst.PAY_TYPE[companyVO.getPay_type()]);	
				}else{
					companyVO.setPay_type_name("暂无支付类型");
				}
				request.setAttribute("companyVO", companyVO);
				UserLoginVO userLoginVO = getUserLoginVO();
				if(userLoginVO != null){
					String user_id=userLoginVO.getId();
					UserBrowseRunnable userBrowseRunnable = new UserBrowseRunnable(user_id, companyVO);
					Thread t = new Thread(userBrowseRunnable);
					t.start();
				}
				return "index";
			}else{
				return "redirect:"+DoMainConst.PC_URL;
				
			}
		} catch (Exception e) {
			new YuleException("查询企业详细信息【findCompanyDetails】出现异常",e);
			throw e;
		}
	}
	
	@RequestMapping(value="/findOtherCompany",method=RequestMethod.POST)
	public String findOtherCompany(@RequestParam(value="id",required=false)String id,@RequestParam(value="city_id",required=false)String city_id)throws Exception{
		JSONObject object = new JSONObject();
		object.put("status", ErrorConst.STATUS_ERROR);
		try {
			IndexReader reader = DirectoryReader.open(FSDirectory.open(new File(LuceneConst.INDEX_SEARCH_COMPANY_FILE_PATH)));
			IndexSearcher searcher = new IndexSearcher(reader);
			BooleanQuery booleanQuery=new BooleanQuery();
			if (!StringUtils.isEmpty(city_id)) {
				TermQuery query=new TermQuery(new Term("area_city_id", city_id));
				booleanQuery.add(query, BooleanClause.Occur.MUST);
			}
			if (!StringUtils.isEmpty(id)) {
				TermQuery query=new TermQuery(new Term("id", id));
				booleanQuery.add(query, BooleanClause.Occur.MUST_NOT);
			}
		Sort sort = new Sort(new SortField("create_time",Type.SCORE,true));
		int end = PageConst.PAGE_SIZE_THREE*PageConst.PAGE_NO_DEFAULT;
		TopDocs topDocs=searcher.search(booleanQuery,reader.numDocs(),sort);
		ScoreDoc[] scoreDocs = topDocs.scoreDocs;
			 StringBuffer otherCompanyHTMLs = new StringBuffer();
				if(null!=scoreDocs&&scoreDocs.length>0){
					int start=0;  
					if(end > scoreDocs.length){
						end = scoreDocs.length;
					}
					Document document = null;
					StringBuffer companyId = new StringBuffer(); 
		            for (int i = start; i < end; i++) {
			            document = searcher.doc(scoreDocs[i].doc);
			            companyId.append(document.get("id"));
						otherCompanyHTMLs.append(" <div class=\"nha_promote_type_search\">");
						otherCompanyHTMLs.append(" <a href=\""+DoMainConst.DETAIL_URL+"/company/findCompanyDetails.do?id="+companyId+"\" class=\"nha_filter_photo\">");
						otherCompanyHTMLs.append("<img width=\"200\" height=\"150\" src=\""+FileUploadConst.COMPANY_IMAGE_PATH+companyId+"/"+FileUploadConst.PX_200_150+FileUploadConst.COMPANY_FACE+FileUploadConst.IMAGE_TYPE+"\">");
						otherCompanyHTMLs.append( "</a>"+document.get("name")+" </div>");
						companyId.setLength(0);
					}
				}
			 object.put("otherCompanyHTMLs", otherCompanyHTMLs.toString());
			 otherCompanyHTMLs.setLength(0);
			 object.put("status", ErrorConst.STATUS_SUCCESS);
		} catch (Exception e) {
			new YuleException("查询其他企业[findOtherCompany]出现异常",e);
			throw e;
		}finally{
			outputResult(object.toString());
		}
		return null;
	}
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/findCompanyCount",method=RequestMethod.POST)
	public String findCompanyCount(@RequestParam(value="country_id",required=false)String country_id,
			@RequestParam(value="city_id",required=false)String city_id,
			@RequestParam(value="province_id",required=false)String province_id)throws Exception{
		JSONObject object = new JSONObject();
		object.put("status", ErrorConst.STATUS_ERROR);
		try {
			int provinceCount = 0;
			int cityCount = 0;
			int countyCount = 0;
			List<CompanyCategoryCountVO> companyProvinceCount = new ArrayList<CompanyCategoryCountVO>();
			if(JedisUtil.getInstance().exists(RedisConst.AREA_PROVINCE_CATEGORY_COUNT+province_id)){
				companyProvinceCount.addAll(JSONArray.toList(JSONArray.fromObject(JedisUtil.getInstance().get(RedisConst.AREA_PROVINCE_CATEGORY_COUNT+province_id)),new CompanyCategoryCountVO(),JSONConst.JSON_CONFIG));
			}
			for (CompanyCategoryCountVO companyCategoryCountVO : companyProvinceCount) {
				provinceCount += companyCategoryCountVO.getCompany_count();
			}
			companyProvinceCount.clear();
			List<CompanyCategoryCountVO> companyCityCount = new ArrayList<CompanyCategoryCountVO>();
			if(JedisUtil.getInstance().exists(RedisConst.AREA_CITY_CATEGORY_COUNT+city_id)){
				companyCityCount.addAll(JSONArray.toList(JSONArray.fromObject(JedisUtil.getInstance().get(RedisConst.AREA_CITY_CATEGORY_COUNT+city_id)),new CompanyCategoryCountVO(),JSONConst.JSON_CONFIG));
			}
			for (CompanyCategoryCountVO companyCategoryCountVO : companyCityCount) {
				cityCount += companyCategoryCountVO.getCompany_count();
			}
			companyCityCount.clear();
			List<CompanyCategoryCountVO> companyCountyCount = new ArrayList<CompanyCategoryCountVO>();
			if(JedisUtil.getInstance().exists(RedisConst.AREA_COUNTY_CATEGORY_COUNT+country_id)){
				companyCountyCount.addAll(JSONArray.toList(JSONArray.fromObject(JedisUtil.getInstance().get(RedisConst.AREA_COUNTY_CATEGORY_COUNT+country_id)),new CompanyCategoryCountVO(),JSONConst.JSON_CONFIG));
			}
			for (CompanyCategoryCountVO companyCategoryCountVO : companyCountyCount) {
				countyCount += companyCategoryCountVO.getCompany_count();
			}
			companyCountyCount.clear();
			 object.put("provinceCount", provinceCount);
			 object.put("cityCount", cityCount);
			 object.put("countryCount", countyCount);
			 object.put("status", ErrorConst.STATUS_SUCCESS);
		} catch (Exception e) {
			new YuleException("查询企业优惠【findCompanyFavorable】出现异常",e);
			throw e;
		}finally{
			outputResult(object.toString());
		}
		return null;
	}
	@RequestMapping(value="/findcompanyDistance",method=RequestMethod.POST)
	public String findcompanyDistance(@ModelAttribute(value="companyDistanceQuery")CompanyDistanceQuery companyDistanceQuery)throws Exception{
		JSONObject object = new JSONObject();
		object.put("status", ErrorConst.STATUS_ERROR);
		try {
			StringBuffer companyDistanceHtml = new StringBuffer();
			companyDistanceHtml.append("<h3>附近没有娱乐场所</h3>");
			if (!StringUtils.isEmpty(companyDistanceQuery.getLat()) && !StringUtils.isEmpty(companyDistanceQuery.getLat())) {
				List<CompanyDistanceVO> companyDistances = this.companyServiceImpl.findCompanyDistanceVO(companyDistanceQuery, PageConst.PAGE_SIZE_FIVE, PageConst.PAGE_NO_DEFAULT);
				if (companyDistances != null && companyDistances.size() > 0) {
					companyDistanceHtml.setLength(0);
					companyDistanceHtml.append("<h3>附近的娱乐场所</h3>");
					companyDistanceHtml.append("<ul class=\"history-list-2\">");
					for (CompanyDistanceVO companyDistanceVO : companyDistances) {
						companyDistanceHtml.append("<li class=\"history-list-item history-list-item-list clearfix\">");	
						companyDistanceHtml.append("<a href=\"/company/findCompanyDetails.do?id="+companyDistanceVO.getId()+"\">");
						companyDistanceHtml.append("<img src=\""+FileUploadConst.COMPANY_IMAGE_PATH+companyDistanceVO.getId()+"/"+FileUploadConst.PX_50_50+FileUploadConst.COMPANY_FACE+FileUploadConst.IMAGE_TYPE+"\"  width=\"30\" height=\"30\">");
						companyDistanceHtml.append("</a>");
						companyDistanceHtml.append("<p>"+companyDistanceVO.getName()+"</i></p>");    
						companyDistanceHtml.append("<span class=\"lastbooking\">直线距离约"+NumberUtil.reverDoubleToKM(companyDistanceVO.getDistance())+"</span>");
						companyDistanceHtml.append("</li>");
					}
					companyDistanceHtml.append("<ul>");
				}
			}
			object.put("companyDistanceHtml", companyDistanceHtml.toString());
			companyDistanceHtml.setLength(0);
			object.put("status", ErrorConst.STATUS_SUCCESS);
		} catch (Exception e) {
			new YuleException("查询企业详细信息【findCompanyDetails】出现异常",e);
			throw e;
		}finally{
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
			TopDocs topDocs=searcher.search(booleanQuery,reader.numDocs());
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
			new YuleException("查询企业列表分页出现异常【searchCompanyList】", e);
			e.printStackTrace();
		}finally{
			reader.close();
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
					String companyAddress = doc.get("address_detail");
					String companyName = doc.get("name");
					object.put("image_path", image_path);
					object.put("companyAddress", companyAddress);
					object.put("companyName", companyName);
				}
			}
			object.put("status", ErrorConst.STATUS_SUCCESS);
		} catch (Exception e) {
			new YuleException("查询企业列表分页出现异常【searchCompanyList】", e);
			e.printStackTrace();
		}finally{
			reader.close();
			outputResult(object.toString());
		}
		return null;
	}
	@RequestMapping(value = "/searchCompanyOrderCount",method = RequestMethod.POST)
	public String searchCompanyOrderCount(@RequestParam(value="companyId",required=false)String companyId) throws Exception {
		JSONObject object = new JSONObject();
		object.put("status", ErrorConst.STATUS_ERROR);
		try {
			CountVO countVO = this.ordersMongoImpl.findOrdersByCompanyAreaCityId(companyId);
			if (null!=countVO&&countVO.getCount()>0) {
				object.put("companyOrdersHtml", countVO.getCount()+"位客人进行了预订");
			}else{
				object.put("companyOrdersHtml", "该企业暂无预订");
			}
			object.put("status", ErrorConst.STATUS_SUCCESS);
		} catch (Exception e) {
			new YuleException("查询企业列表分页出现异常【searchCompanyList】", e);
			e.printStackTrace();
		}finally{
			outputResult(object.toString());
		}
		return null;
	}
}
