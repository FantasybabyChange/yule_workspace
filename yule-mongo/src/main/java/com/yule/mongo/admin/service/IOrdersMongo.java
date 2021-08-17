package com.yule.mongo.admin.service;

import com.yule.exception.YuleException;
import com.yule.mongo.pojo.Orders;
import com.yule.mongo.query.OrdersQuery;
import com.yule.vo.Page;


public interface IOrdersMongo {
	
	public Page<Orders> findOrdersPage(OrdersQuery ordersQuery,int pageSize,int pageNo) throws YuleException;
	
	public boolean updateOrders(Orders order) throws YuleException;
	
	public Orders findOrdersById(String id) throws YuleException;
	
}
