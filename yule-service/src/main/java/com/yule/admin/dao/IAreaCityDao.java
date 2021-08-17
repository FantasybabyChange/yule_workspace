package com.yule.admin.dao;

import java.util.List;

import com.yule.exception.YuleException;
import com.yule.pojo.AreaCity;

public interface IAreaCityDao {
	public int insertAreaCity(AreaCity areaCity) throws YuleException;

	public int updateAreaCity(AreaCity areaCity) throws YuleException;

	public int deleteAreaCityById(String id) throws YuleException;

	public List<AreaCity> findAreaCityList(String provinceId) throws YuleException;
	
	public int findAreaCityCount() throws YuleException;
	
	public int deleteAreaCityAll() throws YuleException;
	
	public int batchInsertAreaCity(List<AreaCity> areaCitys) throws YuleException;
	
	
	
	
}
