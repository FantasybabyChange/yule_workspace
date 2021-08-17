package com.yule.mobile.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yule.exception.YuleException;
import com.yule.mobile.dao.IAreaCityDao;
import com.yule.mobile.service.IAreaCityService;
import com.yule.mobile.vo.AreaCityVO;
import com.yule.pojo.AreaCity;

@Service("areaCityServiceImpl")
public class AreaCityServiceImpl implements IAreaCityService {

	@Autowired
	private IAreaCityDao areaCitydao;

	public List<AreaCity> findAreaCityList(int pageSize, int pageNo) throws YuleException {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("pageStart", (pageNo - 1) * pageSize);
		params.put("pageEnd", pageSize);
		return this.areaCitydao.findAreaCityList(params);
	}

	public List<AreaCityVO> findAreaCityByInitial(String initial)throws YuleException {
		return this.areaCitydao.findAreaCityByInitial(initial);
	}

}
