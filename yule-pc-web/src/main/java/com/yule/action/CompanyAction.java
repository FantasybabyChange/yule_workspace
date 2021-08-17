package com.yule.action;

import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONArray;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.yule.common.BaseAction;
import com.yule.constant.JSONConst;
import com.yule.constant.PageConst;
import com.yule.pc.service.IAreaCityService;
import com.yule.pojo.AreaCity;
import com.yule.redis.constant.RedisConst;
import com.yule.redis.util.JedisUtil;
import com.yule.util.CompanyUtil;
import com.yule.vo.CompanyCategoryCountVO;
import com.yule.vo.CompanyCategoryVO;

@Controller
@Scope("prototype")
public class CompanyAction extends BaseAction{
	
	@Autowired
	private IAreaCityService areaCityServiceImpl;
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/findAreaCompanyHot",method = RequestMethod.POST)
	public String findCurrentAreaHot() throws Exception {
//		String areaCityName = IPAddressUtil.getCity(request.getRemoteAddr());
		String areaProvinceId = getAreaCookieValue();
		areaProvinceId = areaProvinceId.substring(0,3)+"000";
		StringBuffer htmls = new StringBuffer();
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
				htmls.append(CompanyUtil.companyHot(areaCity.getId(), companyCategoryVOs.get(0).getId()));
				htmls.append("</ul>");
				htmls.append("<p class=\"text-right view-more-wapper\">");
				htmls.append("<a id=\"moreView\" target=\"blank\" href=\"javascript:;\" data-href=\"http://search.yuleing.com/search/searchCompany.do?area_city_id="+areaCity.getId()+"&area_city_name="+areaCity.getName()+"&company_category=\" class=\"view-more\" >查看更多信息 »</a>");
				htmls.append("</p>");
				htmls.append("</div>");
			}
			areaCitys.clear();
		}
		companyCategoryVOs.clear();
		outputResult(htmls.toString());
		htmls.setLength(0);
		htmls = null;
		return null;
	}
	
	@RequestMapping(value = "/findCompanyHot",method = RequestMethod.POST)
	public String findCompanyHot(@RequestParam(value = "areaCityId", required = false) String areaCityId,@RequestParam(value = "companyCategoryId", required = false) String companyCategoryId) throws Exception{
		outputResult(CompanyUtil.companyHot(areaCityId, companyCategoryId));
		return null;
	}
	
}