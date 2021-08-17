package com.yule.action;

import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONArray;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.yule.common.BaseAction;
import com.yule.constant.CodeConst;
import com.yule.constant.FileUploadConst;
import com.yule.constant.HtmlTemplatePathConst;
import com.yule.constant.IndexMemcachedConst;
import com.yule.constant.JSONConst;
import com.yule.constant.PageConst;
import com.yule.memcached.constant.MemcachedConst;
import com.yule.memcached.util.MemcachedUtil;
import com.yule.mongo.pc.service.IOrdersMongo;
import com.yule.mongo.pojo.Orders;
import com.yule.pc.service.IAreaCityService;
import com.yule.pojo.AreaCity;
import com.yule.pojo.AreaProvince;
import com.yule.redis.constant.RedisConst;
import com.yule.redis.util.JedisUtil;
import com.yule.util.AreaUtil;
import com.yule.util.FileContentUtil;
import com.yule.util.SearchCriteriaUtil;
import com.yule.vo.CompanyCategoryCountVO;
import com.yule.vo.CompanyCategoryVO;

@Controller
@Scope("prototype")
@RequestMapping("/html")
public class HtmlAction extends BaseAction{
	
	@Autowired
	private IOrdersMongo ordersMongoImpl;
	
	@Autowired
	private IAreaCityService areaCityServiceImpl;

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/createIndexHtml",method = RequestMethod.GET)
	public String index() throws Exception {
		String content = FileContentUtil.readFileByLines(HtmlTemplatePathConst.INDEX_HTML);
		List<AreaProvince> areaProvinces = new ArrayList<AreaProvince>();
		if(JedisUtil.getInstance().exists(RedisConst.AREA_PROVINCE)){
			areaProvinces.addAll(JSONArray.toList(JSONArray.fromObject(JedisUtil.getInstance().get(RedisConst.AREA_PROVINCE)),new AreaProvince(),JSONConst.JSON_CONFIG));
		}
		if(areaProvinces.size()>0){
			String contents = null;
			for(AreaProvince areaProvince:areaProvinces){
				contents = content;
				MemcachedUtil memcachedUtil = MemcachedUtil.getInstance();
				memcachedUtil.delete(MemcachedConst.INDEX_NAV_CITY);
				if(!memcachedUtil.keyExists(MemcachedConst.INDEX_NAV_CITY)){
					AreaUtil.initAreaCityNav();
				}
				String areaProvinceId = areaProvince.getId();
				String navHot = AreaUtil.initAreaCityHotNav(areaProvinceId);
				StringBuffer htmls = new StringBuffer();
				if(!StringUtils.isEmpty(navHot)){
					htmls.append("<div class=\"destination-section domestic-destination has-no-tab\">");
					htmls.append("<div class=\"tab-header\">");
					htmls.append("精选城市");
					htmls.append("<div class=\"destination-tab-clear\"></div>");
			        htmls.append("</div>");
					htmls.append("<div class=\"tab-content clearfix\">");
					htmls.append("<div class=\"tab-panel tab-panel-active\">");
					htmls.append("<ul class=\"destination-area\">");
					htmls.append(navHot.toString());
					htmls.append("</ul>");
					htmls.append("</div>");
					htmls.append("</div>");
					htmls.append("</div>");
				}
				htmls.append("<div class=\"destination-section abroad-destination\">");
				htmls.append("<div class=\"tab-header\">");
				htmls.append("热门城市");
				htmls.append("</div>");
				htmls.append("<ul class=\"tab-nav\">");
				htmls.append("<li class=\"tab-nav-item tab-nav-item-active\"><a href=\"javascript:;\">热门</a></li>");
				htmls.append("<li class=\"tab-nav-item \"><a href=\"javascript:;\">ABCD</a></li>");
				htmls.append("<li class=\"tab-nav-item \"><a href=\"javascript:;\">EGHJ</a></li>");
				htmls.append("<li class=\"tab-nav-item \"><a href=\"javascript:;\">KLMNP</a></li>");
				htmls.append("<li class=\"tab-nav-item \"><a href=\"javascript:;\">QRSTW</a></li>");
				htmls.append("<li class=\"tab-nav-item \"><a href=\"javascript:;\">XYZ</a></li>");
				htmls.append("</ul>");
				htmls.append("<div class=\"tab-content pane-wrapper\">");
				htmls.append(memcachedUtil.get(MemcachedConst.INDEX_NAV_CITY));
				htmls.append("</div>");
				htmls.append("</div>");
				contents= contents.replace("#{areaCityNav}", htmls.toString());
				htmls.setLength(0);
				
				
				memcachedUtil.delete(IndexMemcachedConst.INDEX_HOT_CITY);
				if(!memcachedUtil.keyExists(IndexMemcachedConst.INDEX_HOT_CITY)){
					AreaUtil.initAreaCityHot();
				}
				htmls.append(memcachedUtil.get(IndexMemcachedConst.INDEX_HOT_CITY).toString());
				contents = contents.replace("#{areaCityHot}", htmls.toString());
				htmls.setLength(0);
				htmls.append(SearchCriteriaUtil.getCompanyGrade());
				htmls.append(SearchCriteriaUtil.getCompanyCategory());
				htmls.append(SearchCriteriaUtil.getCompanyPointCategory());
				htmls.append("<div class=\"clearit\"></div>");
				contents = contents.replace("#{searchCriteria}", htmls.toString());
				htmls.setLength(0);
				List<Orders> orderss = ordersMongoImpl.findOrdersList(areaProvinceId,PageConst.PAGE_SIZE_TEN, PageConst.PAGE_NO_DEFAULT);
				if(null!=orderss&&orderss.size()>0){
					for(Orders orders : orderss){
						htmls.append("<li class=\"book\">");
						htmls.append("<div class=\"activity_box\">");
						htmls.append("<div class=\"image\">");
						htmls.append("<img src=\"http://images.yuleing.com/loading/loading.jpg\" data-original=\""+FileUploadConst.COMPANY_IMAGE_PATH+orders.getCompany_id()+"/"+FileUploadConst.PX_50_50+FileUploadConst.COMPANY_FACE+FileUploadConst.IMAGE_TYPE+"\">");
						htmls.append("</div>");
						htmls.append("<p class=\"booker_from\">1位来自"+orders.getUser_area_city_name()+"的客人</p>刚刚预订了<a target=\"_blank\" href=\"http://detail.yuleing.com/company/findCompanyDetails.do?id="+orders.getCompany_id()+"\">"+orders.getCompany_name()+"</a>");
						htmls.append("</div>");
						htmls.append("</li>");
					}
				}
				if(htmls.length()>0){
					String html = "<ul class=\"\" id=\"activity_items\">"+htmls.toString()+"</ul>";
					contents = contents.replace("#{orders}", html);
				}else{
					contents = contents.replace("#{orders}", "");
				}
				
				htmls.setLength(0);
				List<CompanyCategoryVO> companyCategoryVOs = new ArrayList<CompanyCategoryVO>();
				if(JedisUtil.getInstance().exists(RedisConst.COMPANY_CATEGORY)){
					companyCategoryVOs.addAll(JSONArray.toList(JSONArray.fromObject(JedisUtil.getInstance().get(RedisConst.COMPANY_CATEGORY)),new CompanyCategoryVO(),JSONConst.JSON_CONFIG));
				}
				List<AreaCity> areaCitys = new ArrayList<AreaCity>();
				if(JedisUtil.getInstance().exists(RedisConst.AREA_CITY+areaProvinceId)){
					areaCitys.addAll(JSONArray.toList(JSONArray.fromObject(JedisUtil.getInstance().get(RedisConst.AREA_CITY+areaProvinceId)),new AreaCity(),JSONConst.JSON_CONFIG));
				}
				StringBuffer companyCategoryCountHtmls = new StringBuffer("");
				List<CompanyCategoryCountVO> companyCategoryCountVOs = new ArrayList<CompanyCategoryCountVO>();
				int k=1;
				if(areaCitys.size()>0){
					for(AreaCity areaCity:areaCitys){
						if(k==PageConst.PAGE_SIZE_FIVE){
							break;
						}
						k++;
						int companyHotCount = 0;
						if(JedisUtil.getInstance().exists(RedisConst.AREA_CITY_CATEGORY_COUNT+areaCity.getId())){
							companyCategoryCountVOs.addAll(JSONArray.toList(JSONArray.fromObject(JedisUtil.getInstance().get(RedisConst.AREA_CITY_CATEGORY_COUNT+areaCity.getId())),new CompanyCategoryCountVO(),JSONConst.JSON_CONFIG));
						}
						if(companyCategoryCountVOs.size()>0){
							companyCategoryCountHtmls.append("<ul class=\"clearfix text-center\">");
							for(CompanyCategoryCountVO companyCategoryCountVO:companyCategoryCountVOs){
								companyCategoryCountHtmls.append("<li class=\"first\">");
								companyCategoryCountHtmls.append("<a target=\"blank\" href=\"http://search.yuleing.com/search/searchCompany.do?area_city_id="+areaCity.getId()+"&area_city_name="+areaCity.getName()+"&company_category="+companyCategoryCountVO.getId()+"\">"+companyCategoryCountVO.getCompany_count()+"家"+companyCategoryCountVO.getName()+"</a>");
								companyCategoryCountHtmls.append("</li>");
								companyHotCount +=companyCategoryCountVO.getCompany_count();
							}
							companyCategoryCountVOs.clear();
							companyCategoryCountVOs = null;
							companyCategoryCountHtmls.append("</ul>");
						}
						htmls.append("<div class=\"recommendation-part\">");
						htmls.append("<div class=\"list-title mt40\">位于"+areaCity.getName()+"地区的娱乐休闲场所</div>");
						htmls.append("<div class=\"recommendation-city\" style=\"background-image: url(http://images.yuleing.com/area/city/"+areaCity.getId()+"/600_200city.jpg)\">");
						htmls.append("<div class=\"pr2_bg\">");
						htmls.append("<p>"+areaCity.getName()+"</p>");
						htmls.append("<div class=\"pc_count\">");
						htmls.append("<em style=\"display: block;font-style: normal\">"+companyHotCount+"个娱乐场所</em>");
						htmls.append("</div>");
						htmls.append("</div>");
						htmls.append("</div>");
						htmls.append("<div class=\"postcard_footer_container\">");
						htmls.append("<div class=\"postcard_footer\">");
						htmls.append("<div class=\"property_counts\">");
						htmls.append(companyCategoryCountHtmls);
						companyCategoryCountHtmls.setLength(0);
						htmls.append("</div>");
						htmls.append("</div>");
						htmls.append("</div>");
			//			htmls.append("<div class=\"recommendation-list-view\">31位客人正在浏览这个目的地</div>");
						htmls.append("<ul class=\"postcard-nav consolidated-tabs\">");
						int i = 0;
						for(CompanyCategoryVO companyCategoryVO:companyCategoryVOs){
							if(i==0){
								htmls.append("<li class=\"selected\" data-city=\""+areaCity.getId()+"\" data-category=\""+companyCategoryVO.getId()+"\">"+companyCategoryVO.getName()+"</li>");
							}else{
								htmls.append("<li data-city=\""+areaCity.getId()+"\" data-category=\""+companyCategoryVO.getId()+"\" ><a href=\"javascript:;\">"+companyCategoryVO.getName()+"</a></li>");
							}
							i++;
						}
			//			htmls.append("<li class=\"see_all_link\"><a href=\"\" title=\"搜索位于北京的酒店, 中国\">查看所有住宿 »</a></li>");
						htmls.append("</ul>");
						htmls.append("<ul class=\"recommendation-list\">");
//						htmls.append(CompanyUtil.companyHot(areaCity.getId(), companyCategoryVOs.get(0).getId()));
						htmls.append("</ul>");
						htmls.append("<p class=\"text-right view-more-wapper\"></p>");
						htmls.append("<a id=\"moreView\" target=\"blank\" href=\"javascript:;\" data-href=\"http://search.yuleing.com/search/searchCompany.do?area_city_id="+areaCity.getId()+"&area_city_name="+areaCity.getName()+"&company_category=\" class=\"view-more\" >查看更多信息 »</a>");
						htmls.append("</div>");
					}
					areaCitys.clear();
				}
				companyCategoryVOs.clear();
				contents = contents.replace("#{currentAreaHot}", htmls.toString());
				htmls.setLength(0);
				Writer out = null;
				try{
					String path = request.getSession().getServletContext().getRealPath("/html/"+areaProvinceId+".html");
					out = new OutputStreamWriter(new FileOutputStream(path),CodeConst.UTF_8);
					out.write(contents);
				}finally{
					if(out!=null){
						out.flush();
						out.close();
					}
				}
			}
			areaProvinces.clear();
			System.gc();
		}
		return null;
	}
	
}