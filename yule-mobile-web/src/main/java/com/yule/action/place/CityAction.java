package com.yule.action.place;

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
import com.yule.constant.ActionReturnConst;
import com.yule.constant.JSONConst;
import com.yule.constant.MobileMemcachedConst;
import com.yule.memcached.util.MemcachedUtil;
import com.yule.mobile.service.IAreaCityService;
import com.yule.pojo.AreaCity;
import com.yule.pojo.AreaProvince;
import com.yule.redis.constant.RedisConst;
import com.yule.redis.util.JedisUtil;
import com.yule.util.AreaUtil;
import com.yule.vo.CompanyCategoryCountVO;

@Controller
@Scope("prototype")
@RequestMapping("/city")
public class CityAction extends BaseAction{

	@Autowired
	private IAreaCityService areaCityServiceImpl;
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/findCityCompany",method = RequestMethod.POST)
	public String findCityCompany(@RequestParam(value = "area_city_id", required = false)String area_city_id)throws Exception{
		JSONObject obj = new JSONObject();
		List<CompanyCategoryCountVO> citycompanyCategoryCountVOs = new ArrayList<CompanyCategoryCountVO>();
		if(JedisUtil.getInstance().exists(RedisConst.AREA_CITY_CATEGORY_COUNT+area_city_id)){
			citycompanyCategoryCountVOs.addAll(JSONArray.toList(JSONArray.fromObject(JedisUtil.getInstance().get(RedisConst.AREA_CITY_CATEGORY_COUNT+area_city_id)),new CompanyCategoryCountVO(),JSONConst.JSON_CONFIG));
		}
		int count = 0;
		if (citycompanyCategoryCountVOs.size()>0) {
			for (CompanyCategoryCountVO companyCategoryCountVO : citycompanyCategoryCountVOs) {
				count += companyCategoryCountVO.getCompany_count();
			}
			citycompanyCategoryCountVOs.clear();
		}
		obj.put("count", count);
		outputResult(obj.toString());
		obj.clear();
		obj = null;
		return null;
	}
	
	@RequestMapping(value = "/findCity",method = RequestMethod.GET)
	public String findCity()throws Exception{

		MemcachedUtil memcachedUtil = MemcachedUtil.getInstance();
		memcachedUtil.delete(MobileMemcachedConst.MOBILE_HOT_CITY);
		if(!memcachedUtil.keyExists(MobileMemcachedConst.MOBILE_HOT_CITY)){
			AreaUtil.initAreaCityHot();
		}
		if(!memcachedUtil.keyExists(MobileMemcachedConst.MOBILE_INDEX_CITY)){
			AreaUtil.initAreaCityNav();
		}
		request.setAttribute("areaCityHot", memcachedUtil.get(MobileMemcachedConst.MOBILE_HOT_CITY));
		request.setAttribute("html",memcachedUtil.get(MobileMemcachedConst.MOBILE_INDEX_CITY));
		return "/place/index";
		
	}
	
	@RequestMapping(value = "/findOtherCity",method = RequestMethod.GET)
	public String findOtherCity(@RequestParam(value = "initial", required = false)String initial)throws Exception{
		StringBuffer html =new StringBuffer();
		if(!JedisUtil.getInstance().exists(RedisConst.AREA_BUSINESS+initial)){
			AreaUtil.initAreaCityInitial(initial);
		}
		html.append(JedisUtil.getInstance().get(RedisConst.AREA_BUSINESS+initial));
		request.setAttribute("html", html);
		request.setAttribute("initial", initial);
		return "/place/index-other";
		
	}
	
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/findCityByName",method = RequestMethod.GET)
	public String findCityByName(@RequestParam(value = "area_city_name", required = false)String area_city_name,@RequestParam(value = "area_province_name", required = false)String area_province_name)throws Exception{
		List<AreaProvince> areaProvinces = new ArrayList<AreaProvince>();
		if(JedisUtil.getInstance().exists(RedisConst.AREA_PROVINCE)){
			areaProvinces.addAll(JSONArray.toList(JSONArray.fromObject(JedisUtil.getInstance().get(RedisConst.AREA_PROVINCE)),new AreaProvince(),JSONConst.JSON_CONFIG));
		}
		String area_province_id  = null; 
		if (areaProvinces.size()>0) {
			for (AreaProvince areaProvince : areaProvinces) {
				if (-1!=area_province_name.indexOf(areaProvince.getName())) {
					area_province_id = areaProvince.getId();
					break;
				}
			}
			areaProvinces.clear();
		}
		areaProvinces =null;
		List<AreaCity> areaCitys = new ArrayList<AreaCity>();
		if(JedisUtil.getInstance().exists(RedisConst.AREA_CITY+area_province_id)){
			areaCitys.addAll(JSONArray.toList(JSONArray.fromObject(JedisUtil.getInstance().get(RedisConst.AREA_CITY+area_province_id)),new AreaCity(),JSONConst.JSON_CONFIG));
		}
		String area_city_id =null;
		if(areaCitys.size()>0){
			for (AreaCity areaCity : areaCitys) {
				if (-1!=area_city_name.indexOf(areaCity.getName())) {
					area_city_id =areaCity.getId();
				}
			}
			areaCitys.clear();
		}
		areaCitys = null;
		return ActionReturnConst.REDIRECT+"/index.do?cityId="+area_city_id+"&cityName="+area_city_name;
		
	}

}
