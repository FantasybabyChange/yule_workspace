package com.yule.cached;

import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

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
import com.yule.util.CompanyCategoryVOUtil;
import com.yule.util.CustomBeanFactory;
import com.yule.vo.CompanyCategoryCountVO;

public class AreaInit {
	
	public static void initArea() throws Exception{
		IAreaProvinceService provinceServiceImpl = (IAreaProvinceService)CustomBeanFactory.getContext(CustomBeanConst.ADMIN_SERVICE_PATHS).getBean("areaProvinceServiceImpl");
		List<AreaProvince> lists = provinceServiceImpl.findAreaProvinceList();
		if(null!=lists&&lists.size()>0){
//			ICompanyCategoryService companyCategoryServiceImpl = (ICompanyCategoryService)CustomBeanFactory.getContext(CustomBeanConst.ADMIN_SERVICE_PATHS).getBean("companyCategoryServiceImpl");
			IAreaCityService cityServiceImpl = (IAreaCityService)CustomBeanFactory.getContext(CustomBeanConst.ADMIN_SERVICE_PATHS).getBean("areaCityServiceImpl");
			IAreaCountyService countyServiceImpl = (IAreaCountyService)CustomBeanFactory.getContext(CustomBeanConst.ADMIN_SERVICE_PATHS).getBean("areaCountyServiceImpl");
			IAreaBusinessService areaBusinessServiceImpl = (IAreaBusinessService)CustomBeanFactory.getContext(CustomBeanConst.ADMIN_SERVICE_PATHS).getBean("areaBusinessServiceImpl");
			List<AreaCity> citys = null;
			List<AreaCounty> countys = null;
			List<AreaBusiness> businesss = null;
			List<CompanyCategoryCountVO> companyCategoryCountVOs = null;
			
			for(AreaProvince province:lists){
				citys = cityServiceImpl.findAreaCityList(province.getId());
				if(null!=citys&&citys.size()>0){
					for(AreaCity city :citys){
						countys = countyServiceImpl.findAreaCountyList(city.getId());
						if(null!=countys&&countys.size()>0){
							for(AreaCounty areaCounty:countys){
//								companyCategoryCountVOs = companyCategoryServiceImpl.findCompanyCategoryCountVOListByAreaCountyId(areaCounty.getId());
								companyCategoryCountVOs = CompanyCategoryVOUtil.getCompanyCategoryCountVOListByAreaCountyId(areaCounty.getId()); 
								if(null!=companyCategoryCountVOs&&companyCategoryCountVOs.size()>0){
									JedisUtil.getInstance().del(RedisConst.AREA_COUNTY_CATEGORY_COUNT+areaCounty.getId());
									JedisUtil.getInstance().set(RedisConst.AREA_COUNTY_CATEGORY_COUNT+areaCounty.getId(), JSONArray.fromObject(companyCategoryCountVOs).toString(),TimeConst.TWO_DAY);
									companyCategoryCountVOs.clear();
									companyCategoryCountVOs =null;
								}
							}
							JedisUtil.getInstance().del(RedisConst.AREA_COUNTY+city.getId());
							JedisUtil.getInstance().set(RedisConst.AREA_COUNTY+city.getId(), JSONArray.fromObject(countys).toString(),TimeConst.TWO_DAY);
							countys.clear();
							countys = null;
						}
						businesss = areaBusinessServiceImpl.findAreaBusinessList(city.getId());
						if(null!=businesss&&businesss.size()>0){
							for(AreaBusiness areaBusiness:businesss){
//								companyCategoryCountVOs = companyCategoryServiceImpl.findCompanyCategoryCountVOListByAreaBusinessId(areaBusiness.getId());
								companyCategoryCountVOs = CompanyCategoryVOUtil.getCompanyCategoryCountVOListByAreaBusinessId(areaBusiness.getId()); 
								if(null!=companyCategoryCountVOs&&companyCategoryCountVOs.size()>0){
									JedisUtil.getInstance().del(RedisConst.AREA_BUSINESS_CATEGORY_COUNT+areaBusiness.getId());
									JedisUtil.getInstance().set(RedisConst.AREA_BUSINESS_CATEGORY_COUNT+areaBusiness.getId(), JSONArray.fromObject(companyCategoryCountVOs).toString(),TimeConst.TWO_DAY);
									companyCategoryCountVOs.clear();
									companyCategoryCountVOs =null;
								}
							}
							JedisUtil.getInstance().del(RedisConst.AREA_BUSINESS+city.getId());
							JedisUtil.getInstance().set(RedisConst.AREA_BUSINESS+city.getId(), JSONArray.fromObject(businesss).toString(),TimeConst.TWO_DAY);
							businesss.clear();
							businesss = null;
						}
//						companyCategoryCountVOs = companyCategoryServiceImpl.findCompanyCategoryCountVOListByAreaCityId(city.getId());
						companyCategoryCountVOs = CompanyCategoryVOUtil.getCompanyCategoryCountVOListByAreaCityId(city.getId()); 
						if(null!=companyCategoryCountVOs&&companyCategoryCountVOs.size()>0){
							JedisUtil.getInstance().del(RedisConst.AREA_CITY_CATEGORY_COUNT+city.getId());
							JedisUtil.getInstance().set(RedisConst.AREA_CITY_CATEGORY_COUNT+city.getId(), JSONArray.fromObject(companyCategoryCountVOs).toString(),TimeConst.TWO_DAY);
							companyCategoryCountVOs.clear();
							companyCategoryCountVOs =null;
						}
						
						JedisUtil.getInstance().del(RedisConst.AREA_CITY_INFO+city.getId());
						JedisUtil.getInstance().set(RedisConst.AREA_CITY_INFO+city.getId(), JSONObject.fromObject(city).toString(),TimeConst.TWO_DAY);
					}
					JedisUtil.getInstance().del(RedisConst.AREA_CITY+province.getId());
					JedisUtil.getInstance().set(RedisConst.AREA_CITY+province.getId(), JSONArray.fromObject(citys).toString(),TimeConst.TWO_DAY);
					citys.clear();
					citys=null;
				}
//				companyCategoryCountVOs = companyCategoryServiceImpl.findCompanyCategoryCountVOListByAreaProvinceId(province.getId());
				companyCategoryCountVOs = CompanyCategoryVOUtil.getCompanyCategoryCountVOListByAreaProvinceId(province.getId()); 
				if(null!=companyCategoryCountVOs&&companyCategoryCountVOs.size()>0){
					JedisUtil.getInstance().del(RedisConst.AREA_PROVINCE_CATEGORY_COUNT+province.getId());
					JedisUtil.getInstance().set(RedisConst.AREA_PROVINCE_CATEGORY_COUNT+province.getId(), JSONArray.fromObject(companyCategoryCountVOs).toString(),TimeConst.TWO_DAY);
					companyCategoryCountVOs.clear();
					companyCategoryCountVOs =null;
				}
			}
			JedisUtil.getInstance().del(RedisConst.AREA_PROVINCE);
			JedisUtil.getInstance().set(RedisConst.AREA_PROVINCE, JSONArray.fromObject(lists).toString(),TimeConst.TWO_DAY);
			lists.clear();
			lists = null;
			cityServiceImpl = null;
			countyServiceImpl = null;
			areaBusinessServiceImpl = null;
		}
		provinceServiceImpl = null;
	}
	
	public static void main(String[] args) {
		System.out.println(JedisUtil.getInstance().get(RedisConst.AREA_CITY_CATEGORY_COUNT+"610100"));
	}
	
}