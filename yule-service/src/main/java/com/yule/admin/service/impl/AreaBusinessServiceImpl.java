package com.yule.admin.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yule.admin.dao.IAreaBusinessDao;
import com.yule.admin.service.IAreaBusinessService;
import com.yule.exception.YuleException;
import com.yule.pojo.AreaBusiness;

@Service("areaBusinessServiceImpl")
public class AreaBusinessServiceImpl implements IAreaBusinessService {
	@Autowired
	private IAreaBusinessDao areaBusinessDao;

	public boolean insertAreaBusiness(AreaBusiness areaBusiness) throws YuleException {
		boolean flag = false;
		try {
			this.areaBusinessDao.insertAreaBusiness(areaBusiness);
			flag = true;
		} catch (Exception e) {
	    	throw new YuleException(e);
	    }
		return flag;
	}

	public boolean updateAreaBusiness(AreaBusiness areaBusiness) throws YuleException {
		boolean flag = false;
		try {
			this.areaBusinessDao.updateAreaBusiness(areaBusiness);
			flag = true;
		} catch (Exception e) {
	    	throw new YuleException(e);
	    }
		return flag;

	}

	public boolean deleteAreaBusinessById(String id) throws YuleException {
		boolean flag = false;
		try {
			this.areaBusinessDao.deleteAreaBusinessById(id);
			flag = true;
		} catch (Exception e) {
	    	throw new YuleException(e);
	    }
		return flag;
	}

	public List<AreaBusiness> findAreaBusinessList() throws YuleException {
		return this.areaBusinessDao.findAreaBusinessList();
	}

	public int findAreaBusinessCount() throws YuleException {
		return this.areaBusinessDao.findAreaBusinessCount();
	}

	public boolean deleteAreaBusinessAll() throws YuleException{
		boolean flag = false;
		try{
			this.areaBusinessDao.deleteAreaBusinessAll();
			flag = true;
		} catch(Exception e) {
	    	throw new YuleException(e);
	    }
		return flag;
	}

	public List<AreaBusiness> findAreaBusinessList(String cityId) throws YuleException {
		return this.areaBusinessDao.findAreaBusinessList(cityId);
	}

	public boolean batchInsertAreaBusiness(List<AreaBusiness> areaBusinesss)
			throws YuleException {
		boolean flag = false;
		try{
			this.areaBusinessDao.batchInsertAreaBusiness(areaBusinesss);
			flag = true;
		} catch(Exception e) {
	    	throw new YuleException(e);
	    }
		return flag;
	}

}
