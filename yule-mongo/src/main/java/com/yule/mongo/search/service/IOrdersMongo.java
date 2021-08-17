package com.yule.mongo.search.service;


import com.yule.exception.YuleException;
import com.yule.mongo.search.vo.OrdersHotVO;
import com.yule.mongo.vo.CountVO;

public interface IOrdersMongo {
	
	public OrdersHotVO findOrdersHotVO(String companyId) throws YuleException;
	
	public CountVO findOrdersByCompanyAreaCityId(String area_city_id) throws YuleException;
}
