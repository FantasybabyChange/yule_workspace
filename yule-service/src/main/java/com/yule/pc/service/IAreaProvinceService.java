package com.yule.pc.service;

import java.util.List;

import com.yule.exception.YuleException;
import com.yule.pojo.AreaProvince;

public interface IAreaProvinceService {

	public List<AreaProvince> findHotAreaCityList() throws YuleException;

}
