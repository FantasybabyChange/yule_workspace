package com.yule.admin.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yule.admin.dao.IAreaCityDao;
import com.yule.admin.service.IAreaCityService;
import com.yule.exception.YuleException;
import com.yule.pojo.AreaCity;

@Service("areaCityServiceImpl")
public class AreaCityServiceImpl implements IAreaCityService {

	@Autowired
	private IAreaCityDao areaCitydao;

	public boolean insertAreaCity(AreaCity areaCity) throws YuleException {
		boolean flag = false;
		try {
			this.areaCitydao.insertAreaCity(areaCity);
			flag = true;
		} catch (Exception e) {
	    	throw new YuleException(e);
	    }
		return flag;
	}

	public boolean updateAreaCity(AreaCity areaCity) throws YuleException {
		boolean flag = false;
		try {
			this.areaCitydao.updateAreaCity(areaCity);
			flag = true;
		} catch (Exception e) {
	    	throw new YuleException(e);
	    }
		return flag;

	}

	public boolean deleteAreaCityById(String id) throws YuleException {
		boolean flag = false;
		try {
			this.areaCitydao.deleteAreaCityById(id);
			flag = true;
		} catch (Exception e) {
	    	throw new YuleException(e);
	    }
		return flag;
	}

	public List<AreaCity> findAreaCityList(String provinceId) throws YuleException{
		return this.areaCitydao.findAreaCityList(provinceId);
	}

	public int findAreaCityCount()throws YuleException{
		return this.areaCitydao.findAreaCityCount();
	}

	public boolean deleteAreaCityAll() throws YuleException{
		boolean flag = false;
		try{
			this.areaCitydao.deleteAreaCityAll();
			flag = true;
		} catch(Exception e) {
	    	throw new YuleException(e);
	    }
		return flag;
	}

	public boolean batchInsertAreaCity(List<AreaCity> areaCitys)throws YuleException{
		boolean flag = false;
		try{
			this.areaCitydao.batchInsertAreaCity(areaCitys);
			flag = true;
		} catch(Exception e) {
	    	throw new YuleException(e);
	    }
		return flag;
	}

}
