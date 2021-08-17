package com.yule.admin.service;

import java.util.List;

import com.yule.exception.YuleException;
import com.yule.pojo.AreaBusiness;

public interface IAreaBusinessService {
	
	public boolean insertAreaBusiness(AreaBusiness areaBusiness) throws YuleException;

	public boolean updateAreaBusiness(AreaBusiness areaBusiness) throws YuleException;

	public boolean deleteAreaBusinessById(String id) throws YuleException;
	
	public boolean batchInsertAreaBusiness(List<AreaBusiness> areaBusinesss)throws YuleException;

	public List<AreaBusiness> findAreaBusinessList(String cityId)throws YuleException;

	public List<AreaBusiness> findAreaBusinessList()throws YuleException;

	public int findAreaBusinessCount()throws YuleException;
	
	public boolean deleteAreaBusinessAll()throws YuleException;
	
}
