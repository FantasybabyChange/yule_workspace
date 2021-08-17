package com.yule.admin.dao;

import java.util.List;

import com.yule.exception.YuleException;
import com.yule.pojo.AreaBusiness;

public interface IAreaBusinessDao {
	
	public int batchInsertAreaBusiness(List<AreaBusiness> areaBusinesss) throws YuleException;
	
	public int insertAreaBusiness(AreaBusiness areaBusiness) throws YuleException;

	public int updateAreaBusiness(AreaBusiness areaBusiness) throws YuleException;

	public int deleteAreaBusinessById(String id) throws YuleException;

	public List<AreaBusiness> findAreaBusinessList() throws YuleException;
	
	public List<AreaBusiness> findAreaBusinessList(String cityId) throws YuleException;

	public int findAreaBusinessCount() throws YuleException;
	
	public int deleteAreaBusinessAll() throws YuleException;
	
}
