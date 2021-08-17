package com.yule.mongo.detail.service;



import com.yule.exception.YuleException;
import com.yule.mongo.vo.CountVO;

public interface IOrdersMongo {
	
	public CountVO findOrdersByCompanyAreaCityId(String company_id) throws YuleException;
}
