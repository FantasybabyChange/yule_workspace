package com.yule.action;

import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.yule.common.BaseAction;
import com.yule.constant.ErrorConst;
import com.yule.constant.JSONConst;
import com.yule.mongo.search.service.IOrdersMongo;
import com.yule.mongo.vo.CountVO;
import com.yule.pojo.AreaProvince;
import com.yule.redis.constant.RedisConst;
import com.yule.redis.util.JedisUtil;
import com.yule.vo.CompanyCategoryCountVO;

@Controller
@Scope("prototype")
@RequestMapping("/city")
public class CityAction extends BaseAction{

	@Autowired
	private IOrdersMongo ordersMongoImpl;
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/findCityCompany",method = RequestMethod.POST)
	public String findCityCompany(@RequestParam(value = "area_city_id", required = false)String area_city_id)throws Exception{
		JSONObject obj = new JSONObject();
		StringBuffer firstHtmls =new StringBuffer("");		
		StringBuffer lastHtmls =new StringBuffer("");
		String area_province_id = area_city_id.substring(0, 3)+"000";
		obj.put("status", ErrorConst.STATUS_ERROR);
		List<AreaProvince> areaProvinces = new ArrayList<AreaProvince>();
		if(JedisUtil.getInstance().exists(RedisConst.AREA_PROVINCE)){
			areaProvinces.addAll(JSONArray.toList(JSONArray.fromObject(JedisUtil.getInstance().get(RedisConst.AREA_PROVINCE)),new AreaProvince(),JSONConst.JSON_CONFIG));
		}
		String province_name  = null; 
		if (areaProvinces.size()>0) {
			for (AreaProvince areaProvince : areaProvinces) {
				if (area_province_id.equals(areaProvince.getId())) {
					province_name = areaProvince.getName();
					break;
				}
			}
			areaProvinces.clear();
		}
		List<CompanyCategoryCountVO> citycompanyCategoryCountVOs = new ArrayList<CompanyCategoryCountVO>();
		if(JedisUtil.getInstance().exists(RedisConst.AREA_CITY_CATEGORY_COUNT+area_city_id)){
			citycompanyCategoryCountVOs.addAll(JSONArray.toList(JSONArray.fromObject(JedisUtil.getInstance().get(RedisConst.AREA_CITY_CATEGORY_COUNT+area_city_id)),new CompanyCategoryCountVO(),JSONConst.JSON_CONFIG));
		}
		int count = 0;
		if (citycompanyCategoryCountVOs.size()>0) {
			for (CompanyCategoryCountVO companyCategoryCountVO : citycompanyCategoryCountVOs) {
				lastHtmls.append("<a href=\"javascript:;\"><span class=\"filter_count\">"+companyCategoryCountVO.getCompany_count()+"</span>    "+companyCategoryCountVO.getName()+"    </a>");
				count += companyCategoryCountVO.getCompany_count();
			}
			citycompanyCategoryCountVOs.clear();
		}
		firstHtmls.append("<h1>#{area_city_name}共有"+count+"处娱乐场所</h1>");
		firstHtmls.append("<p class=\"property_types_filters_links\">");
		firstHtmls.append(lastHtmls);
		firstHtmls.append("</p>");
		List<CompanyCategoryCountVO> provincecompanyCategoryCountVOs =  new ArrayList<CompanyCategoryCountVO>();;
		if(JedisUtil.getInstance().exists(RedisConst.AREA_PROVINCE_CATEGORY_COUNT+area_province_id)){
			provincecompanyCategoryCountVOs.addAll(JSONArray.toList(JSONArray.fromObject(JedisUtil.getInstance().get(RedisConst.AREA_PROVINCE_CATEGORY_COUNT+area_province_id)),new CompanyCategoryCountVO(),JSONConst.JSON_CONFIG));
		}
		int provinceCount =0 ;
		if (provincecompanyCategoryCountVOs.size()>0) {
			for (CompanyCategoryCountVO companyCategoryCountVO : provincecompanyCategoryCountVOs) {
				provinceCount += companyCategoryCountVO.getCompany_count();
			}
			provincecompanyCategoryCountVOs.clear();
		}
		CountVO countVO = this.ordersMongoImpl.findOrdersByCompanyAreaCityId(area_city_id);
		if (null!=countVO&&countVO.getCount()>0) {
			obj.put("cityOrdersHtml", countVO.getCount()+"位客人进行了预订");
		}else{
			obj.put("cityOrdersHtml", "该城市暂无预订");
		}
		obj.put("cityCompanyHtml", firstHtmls.toString());
		obj.put("cityCountHtml", "<p><a href=\"javascript:;\">#{area_city_name}</a> </p><p class=\"breadcrumb-tips\">"+count+"个娱乐场所</p>");
		obj.put("provinceCountHtml", "<p><a href=\"javascript:;\">"+province_name+"</a><span>→</span></p><p class=\"breadcrumb-tips\">"+provinceCount+"个娱乐场所</p>");
		obj.put("status", ErrorConst.STATUS_SUCCESS);
		outputResult(obj.toString());
		obj.clear();
		obj = null;
		return null;
	}
	
	
}
