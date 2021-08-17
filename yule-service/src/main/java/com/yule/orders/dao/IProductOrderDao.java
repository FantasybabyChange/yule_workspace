package com.yule.orders.dao;

import com.yule.exception.YuleException;



public interface IProductOrderDao {

	public int findProductstatusById(String id) throws YuleException;
	
}
