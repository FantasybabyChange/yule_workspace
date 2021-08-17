package com.yule.admin.dao;

import java.util.List;

import com.yule.exception.YuleException;
import com.yule.pojo.AreaCounty;


public interface IAreaCountyDao {

	public int insertAreaCounty(AreaCounty areaCounty) throws YuleException;
	
	public int updateAreaCounty(AreaCounty areaCounty) throws YuleException;
	
	public int deleteAreaCountyById(String id) throws YuleException;

	public List<AreaCounty> findAreaCountyList(String cityId) throws YuleException;
	
	public int findAreaCountyCount() throws YuleException;
	
	public int deleteAreaCountyAll() throws YuleException;
	
	public int batchInsertAreaCounty(List<AreaCounty> areaCountys) throws YuleException;

}
