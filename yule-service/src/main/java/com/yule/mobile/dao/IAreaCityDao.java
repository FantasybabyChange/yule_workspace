package com.yule.mobile.dao;

import java.util.List;
import java.util.Map;

import com.yule.exception.YuleException;
import com.yule.mobile.vo.AreaCityVO;
import com.yule.pojo.AreaCity;

public interface IAreaCityDao {
	
	public List<AreaCity> findAreaCityList(Map<String,Object> params) throws YuleException;
	
	public List<AreaCityVO> findAreaCityByInitial(String initial) throws YuleException;
}
