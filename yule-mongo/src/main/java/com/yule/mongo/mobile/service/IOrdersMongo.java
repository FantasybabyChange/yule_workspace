package com.yule.mongo.mobile.service;


import java.util.List;

import com.yule.exception.YuleException;
import com.yule.mongo.mobile.vo.CompanyHotVO;
import com.yule.mongo.mobile.vo.OrdersHotAreaCityVO;
import com.yule.mongo.mobile.vo.OrdersHotVO;
import com.yule.mongo.vo.CountVO;

public interface IOrdersMongo {
	
	public OrdersHotVO findOrdersHotVO(String companyId) throws YuleException;
	
	public CountVO findOrdersByCompanyAreaCityId(String area_city_id) throws YuleException;
	
	public List<CompanyHotVO> findOrdersHotAreaCityVOList(Integer pageSize,Integer pageNo,String cityId) throws YuleException ;

	public List<OrdersHotAreaCityVO> findOrdersHotAreaCityVOList(Integer pageSize,Integer pageNo) throws YuleException;
}
