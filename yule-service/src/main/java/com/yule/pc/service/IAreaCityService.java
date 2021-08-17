package com.yule.pc.service;

import java.util.List;

import com.yule.exception.YuleException;
import com.yule.pojo.AreaCity;

public interface IAreaCityService {

	public List<AreaCity> findAreaCityList(int pageSize,int pageNo) throws YuleException;

}
