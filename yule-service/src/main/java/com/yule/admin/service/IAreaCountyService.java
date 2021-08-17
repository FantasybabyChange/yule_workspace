package com.yule.admin.service;

import java.util.List;

import com.yule.exception.YuleException;
import com.yule.pojo.AreaCounty;

public interface IAreaCountyService {

	public boolean insertAreaCounty(AreaCounty areaCounty) throws YuleException;

	public boolean updateAreaCounty(AreaCounty areaCounty) throws YuleException;

	public boolean deleteAreaCountyById(String id) throws YuleException;

	public List<AreaCounty> findAreaCountyList(String cityId)throws YuleException;

	public int findAreaCountyCount()throws YuleException;
	
	public boolean deleteAreaCountyAll()throws YuleException;
	
	public boolean batchInsertAreaCounty(List<AreaCounty> areaCountys)throws YuleException;

}
