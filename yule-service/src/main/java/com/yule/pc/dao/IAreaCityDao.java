package com.yule.pc.dao;

import java.util.List;
import java.util.Map;

import com.yule.exception.YuleException;
import com.yule.pojo.AreaCity;

public interface IAreaCityDao {
	
	public List<AreaCity> findAreaCityList(Map<String,Object> params) throws YuleException;
	
}
