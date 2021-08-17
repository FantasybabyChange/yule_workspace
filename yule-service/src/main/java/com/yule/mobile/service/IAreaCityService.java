package com.yule.mobile.service;

import java.util.List;

import com.yule.exception.YuleException;
import com.yule.mobile.vo.AreaCityVO;
import com.yule.pojo.AreaCity;

public interface IAreaCityService {

	public List<AreaCity> findAreaCityList(int pageSize,int pageNo) throws YuleException;

	public List<AreaCityVO> findAreaCityByInitial(String initial) throws YuleException;

}
