package com.yule.util;

import java.util.List;

import net.sf.json.JSONArray;

import com.yule.admin.service.IAreaBusinessService;
import com.yule.admin.service.IAreaCityService;
import com.yule.admin.service.IAreaCountyService;
import com.yule.admin.service.IAreaProvinceService;
import com.yule.constant.CustomBeanConst;
import com.yule.constant.TimeConst;
import com.yule.pojo.AreaBusiness;
import com.yule.pojo.AreaCity;
import com.yule.pojo.AreaCounty;
import com.yule.pojo.AreaProvince;
import com.yule.redis.constant.RedisConst;
import com.yule.redis.util.JedisUtil;

public class AreaUtil {
	
	public static void setAreaProvince() throws Exception{
		IAreaProvinceService provinceServiceImpl = (IAreaProvinceService)CustomBeanFactory.getContext(CustomBeanConst.ADMIN_SERVICE_PATHS).getBean("areaProvinceServiceImpl");
		List<AreaProvince> lists = provinceServiceImpl.findAreaProvinceList();
		if(null!=lists&&lists.size()>0){
			JedisUtil.getInstance().del(RedisConst.AREA_PROVINCE);
			JedisUtil.getInstance().set(RedisConst.AREA_PROVINCE, JSONArray.fromObject(lists).toString(),TimeConst.TWO_DAY);
			lists.clear();
			lists = null;
		}
		provinceServiceImpl = null;
	}
	
	public static void setAreaCity(String provinceId) throws Exception{
		IAreaCityService cityServiceImpl = (IAreaCityService)CustomBeanFactory.getContext(CustomBeanConst.ADMIN_SERVICE_PATHS).getBean("areaCityServiceImpl");
		List<AreaCity> lists = cityServiceImpl.findAreaCityList(provinceId);
		if(null!=lists&&lists.size()>0){
			String key = RedisConst.AREA_CITY+provinceId;
			JedisUtil.getInstance().del(key);
			JedisUtil.getInstance().set(key, JSONArray.fromObject(lists).toString(),TimeConst.TWO_DAY);
			lists.clear();
			lists = null;
		}
		cityServiceImpl = null;
	}
	
	public static void setAreaCounty(String cityId) throws Exception{
		IAreaCountyService countyServiceImpl = (IAreaCountyService)CustomBeanFactory.getContext(CustomBeanConst.ADMIN_SERVICE_PATHS).getBean("areaCountyServiceImpl");
		List<AreaCounty> lists = countyServiceImpl.findAreaCountyList(cityId);
		if(null!=lists&&lists.size()>0){
			String key = RedisConst.AREA_COUNTY+cityId;
			JedisUtil.getInstance().del(key);
			JedisUtil.getInstance().set(key, JSONArray.fromObject(lists).toString(),TimeConst.TWO_DAY);
			lists.clear();
			lists = null;
		}
		countyServiceImpl = null;
	}
	
	public static void setAreaBusiness(String cityId) throws Exception{
		IAreaBusinessService areaBusinessServiceImpl = (IAreaBusinessService)CustomBeanFactory.getContext(CustomBeanConst.ADMIN_SERVICE_PATHS).getBean("areaBusinessServiceImpl");
		List<AreaBusiness> lists = areaBusinessServiceImpl.findAreaBusinessList(cityId);
		if(null!=lists&&lists.size()>0){
			String key = RedisConst.AREA_BUSINESS+cityId;
			JedisUtil.getInstance().del(key);
			JedisUtil.getInstance().set(key, JSONArray.fromObject(lists).toString(),TimeConst.TWO_DAY);
			lists.clear();
			lists = null;
		}
		areaBusinessServiceImpl = null;
	}
	
}