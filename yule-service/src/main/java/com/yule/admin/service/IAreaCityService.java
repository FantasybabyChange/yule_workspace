package com.yule.admin.service;

import java.util.List;

import com.yule.exception.YuleException;
import com.yule.pojo.AreaCity;

public interface IAreaCityService {
	public boolean insertAreaCity(AreaCity areaCity) throws YuleException;

	public boolean updateAreaCity(AreaCity areaCity) throws YuleException;

	public boolean deleteAreaCityById(String id) throws YuleException;

	public List<AreaCity> findAreaCityList(String provinceId)throws YuleException;
	
	public int findAreaCityCount()throws YuleException;
	
	public boolean deleteAreaCityAll()throws YuleException;
	
	public boolean batchInsertAreaCity(List<AreaCity> areaCitys)throws YuleException;
}
