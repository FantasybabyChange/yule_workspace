package com.yule.orders.service;

import com.yule.exception.YuleException;

public interface IProductService {

	public int findProductstatusById(String id)throws YuleException;
	
}
