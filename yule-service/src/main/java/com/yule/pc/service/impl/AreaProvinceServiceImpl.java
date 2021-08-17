package com.yule.pc.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yule.exception.YuleException;
import com.yule.pc.dao.IAreaCityDao;
import com.yule.pc.service.IAreaProvinceService;
import com.yule.pojo.AreaProvince;

@Service("areaProvinceServiceImpl")
public class AreaProvinceServiceImpl implements IAreaProvinceService {

	@Autowired
	private IAreaCityDao areaCitydao;

	public List<AreaProvince> findHotAreaCityList() throws YuleException {
		// TODO Auto-generated method stub
		return null;
	}

}
