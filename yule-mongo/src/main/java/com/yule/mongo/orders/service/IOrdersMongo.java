package com.yule.mongo.orders.service;

import com.yule.exception.YuleException;
import com.yule.mongo.pojo.Orders;


public interface IOrdersMongo {
	
	
	public boolean insertOrders(Orders order) throws YuleException;
	
}
