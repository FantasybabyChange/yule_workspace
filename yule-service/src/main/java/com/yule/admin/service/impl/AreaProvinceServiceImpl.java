package com.yule.admin.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yule.admin.dao.IAreaProvinceDao;
import com.yule.admin.service.IAreaProvinceService;
import com.yule.exception.YuleException;
import com.yule.pojo.AreaProvince;

@Service("areaProvinceServiceImpl")
public class AreaProvinceServiceImpl implements IAreaProvinceService {

	@Autowired
	private IAreaProvinceDao areaProvinceDao;

	public List<AreaProvince> findAreaProvinceList() throws YuleException {
		return this.areaProvinceDao.findAreaProvinceList();
	}

	public boolean insertAreaProvince(AreaProvince areaProvince) throws YuleException {
		boolean flag = false;
		try {
			this.areaProvinceDao.insertAreaProvince(areaProvince);
			flag = true;
		} catch(Exception e) {
	    	throw new YuleException(e);
	    }
		return flag;
	}

	public boolean updateAreaProvince(AreaProvince areaProvince) throws YuleException {
		boolean flag = false;
		try {
			this.areaProvinceDao.updateAreaProvince(areaProvince);
			flag = true;
		} catch(Exception e) {
	    	throw new YuleException(e);
	    }
		return flag;

	}

	public boolean deleteAreaProvinceById(String id) throws YuleException {
		boolean flag = false;
		try {
			this.areaProvinceDao.deleteAreaProvinceById(id);
			flag = true;
		} catch(Exception e) {
	    	throw new YuleException(e);
	    }
		return flag;

	}

	public int findAreaProvinceCount() throws YuleException {
		return this.areaProvinceDao.findAreaProvinceCount();
	}
	
	public boolean deleteAreaProvinceAll() throws YuleException{
		boolean flag = false;
		try{
			this.areaProvinceDao.deleteAreaProvinceAll();
			flag = true;
		} catch(Exception e) {
	    	throw new YuleException(e);
	    }
		return flag;
	}

	public boolean batchInsertAreaProvince(List<AreaProvince> areaProvinces) throws YuleException{
		boolean flag = false;
		try{
			this.areaProvinceDao.batchInsertAreaProvince(areaProvinces);
			flag = true;
		} catch(Exception e) {
	    	throw new YuleException(e);
	    }
		return flag;
	}


}
