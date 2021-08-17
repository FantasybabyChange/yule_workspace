package com.yule.admin.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yule.admin.dao.IAreaCountyDao;
import com.yule.admin.service.IAreaCountyService;
import com.yule.exception.YuleException;
import com.yule.pojo.AreaCounty;

@Service("areaCountyServiceImpl")
public class AreaCountyServiceImpl implements IAreaCountyService {

	@Autowired
	private IAreaCountyDao areaCountyDao;

	public List<AreaCounty> findAreaCountyList(String cityId) throws YuleException{
		return this.areaCountyDao.findAreaCountyList(cityId);
	}

	public boolean insertAreaCounty(AreaCounty areaCounty) throws YuleException {
		boolean flag = false;
		try {
			this.areaCountyDao.insertAreaCounty(areaCounty);
			flag = true;
		} catch (Exception e) {
	    	throw new YuleException(e);
	    }
		return flag;
	}

	public boolean updateAreaCounty(AreaCounty areaCounty) throws YuleException {
		boolean flag = false;
		try {
			this.areaCountyDao.updateAreaCounty(areaCounty);
			flag = true;
		} catch (Exception e) {
	    	throw new YuleException(e);
	    }
		return flag;
	}

	public boolean deleteAreaCountyById(String id) throws YuleException {
		boolean flag = false;
		try {
			this.areaCountyDao.deleteAreaCountyById(id);
			flag = true;
		} catch (Exception e) {
	    	throw new YuleException(e);
	    }
		return flag;
	}

	public int findAreaCountyCount() throws YuleException{
		return this.areaCountyDao.findAreaCountyCount();
	}
	
	public boolean deleteAreaCountyAll() throws YuleException {
		boolean flag = false;
		try{
			this.areaCountyDao.deleteAreaCountyAll();
			flag = true;
		} catch(Exception e) {
	    	throw new YuleException(e);
	    }
		return flag;
	}

	public boolean batchInsertAreaCounty(List<AreaCounty> areaCountys) throws YuleException{
		boolean flag = false;
		try{
			this.areaCountyDao.batchInsertAreaCounty(areaCountys);
			flag = true;
		} catch(Exception e) {
	    	throw new YuleException(e);
	    }
		return flag;
	}


}
