package com.yule.data;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.yule.admin.service.IAreaBusinessService;
import com.yule.admin.service.IAreaCityService;
import com.yule.admin.service.IAreaCountyService;
import com.yule.admin.service.IAreaProvinceService;
import com.yule.constant.ConfigConst;
import com.yule.constant.CustomBeanConst;
import com.yule.pojo.AreaBusiness;
import com.yule.pojo.AreaCity;
import com.yule.pojo.AreaCounty;
import com.yule.pojo.AreaProvince;
import com.yule.redis.constant.RedisConst;
import com.yule.redis.util.JedisUtil;
import com.yule.util.CustomBeanFactory;
import com.yule.util.PinyinUtil;

public class AreaInit {
	
	@SuppressWarnings("unchecked")
	public static void initArea() throws Exception{
		IAreaProvinceService provinceServiceImpl = (IAreaProvinceService)CustomBeanFactory.getContext(CustomBeanConst.ADMIN_SERVICE_PATHS).getBean("areaProvinceServiceImpl");
		IAreaCityService cityServiceImpl = (IAreaCityService)CustomBeanFactory.getContext(CustomBeanConst.ADMIN_SERVICE_PATHS).getBean("areaCityServiceImpl");
		IAreaCountyService countyServiceImpl = (IAreaCountyService)CustomBeanFactory.getContext(CustomBeanConst.ADMIN_SERVICE_PATHS).getBean("areaCountyServiceImpl");
		IAreaBusinessService areaBusinessServiceImpl = (IAreaBusinessService)CustomBeanFactory.getContext(CustomBeanConst.ADMIN_SERVICE_PATHS).getBean("areaBusinessServiceImpl");
		areaBusinessServiceImpl.deleteAreaBusinessAll();
		countyServiceImpl.deleteAreaCountyAll();
		cityServiceImpl.deleteAreaCityAll();
		provinceServiceImpl.deleteAreaProvinceAll();
		SAXReader sr = new SAXReader();
		InputStream in = AreaInit.class.getClassLoader().getResourceAsStream(ConfigConst.AREA);
		Document doc = sr.read(in);
		Element root = doc.getRootElement();
		AreaProvince provincevo = new AreaProvince();
		AreaCity cityvo = new AreaCity();
		AreaCounty countyvo = new AreaCounty();
		AreaBusiness areaBusinessvo = new AreaBusiness();
		List<Element> provinces = root.elements("province");
	
		if(null!=provinces&&provinces.size()>=0){
			List<AreaProvince> areaProvinces = new ArrayList<AreaProvince>();
			List<AreaCity> areaCitys = new ArrayList<AreaCity>();
			List<AreaCounty> areaCountys = new ArrayList<AreaCounty>();
			List<AreaBusiness> areaBusinesss = new ArrayList<AreaBusiness>();
			for(Element province:provinces){
				provincevo = new AreaProvince();
				provincevo.setId(province.attributeValue("id"));
				provincevo.setName(province.attributeValue("name"));
				areaProvinces.add(provincevo);
				List<Element> citys = province.elements("city");
				if(null!=citys&&citys.size()>=0){
					for(Element city:citys){
						cityvo = new AreaCity();
						cityvo.setId(city.attributeValue("id"));
						cityvo.setName(city.attributeValue("name"));
						cityvo.setArea_province_id(province.attributeValue("id"));
						cityvo.setPinyin(PinyinUtil.getPinYin(city.attributeValue("name")));
						areaCitys.add(cityvo);
						List<Element> countys = city.elements("county");
						if(null!=countys&&countys.size()>=0){
							for(Element county:countys){
								countyvo = new AreaCounty();
								countyvo.setId(county.attributeValue("id"));
								countyvo.setName(county.attributeValue("name"));
								countyvo.setArea_city_id(city.attributeValue("id"));
								areaCountys.add(countyvo);
							}
							countys.clear();
							countys = null;
						}
						List<Element> businesss = city.elements("business");
						if(null!=businesss&&businesss.size()>=0){
							for(Element business:businesss){
								areaBusinessvo = new AreaBusiness();
								areaBusinessvo.setId(business.attributeValue("id"));
								areaBusinessvo.setArea_city_id(city.attributeValue("id"));
								areaBusinessvo.setName(business.attributeValue("name"));
								areaBusinesss.add(areaBusinessvo);
							}
							businesss.clear();
							businesss = null;
						}
					}
					citys.clear();
					citys = null;
				}
			}
			provinces.clear();
			provinces = null;
			provinceServiceImpl.batchInsertAreaProvince(areaProvinces);
			areaProvinces.clear();
			areaProvinces = null;
			cityServiceImpl.batchInsertAreaCity(areaCitys);
			areaCitys.clear();
			areaCitys = null;
			countyServiceImpl.batchInsertAreaCounty(areaCountys);
			areaCountys.clear();
			areaCountys = null;
			areaBusinessServiceImpl.batchInsertAreaBusiness(areaBusinesss);
			areaBusinesss.clear();
			areaBusinesss = null;
		}
		in.close();
		doc.clearContent();
		
		provinceServiceImpl = null;
		cityServiceImpl = null;
		countyServiceImpl = null;
		areaBusinessServiceImpl = null;
	}
	
	public static void cleanArea(){
		JedisUtil.getInstance().del(RedisConst.AREA_PROVINCE);
	}
	
}