package com.yule.mongo.user.service;

import com.yule.exception.YuleException;
import com.yule.mongo.pojo.Orders;
import com.yule.mongo.user.query.OrdersQuery;
import com.yule.vo.Page;

public interface IOrdersMongo {
	
	public Page<Orders> findOrdersPage(OrdersQuery ordersQuery,int pageSize,int pageNo) throws YuleException;

	public int findOrders(String userId) throws YuleException;
	
	public Orders findOrdersByOrderNum(String orderNum) throws YuleException;
	
	public boolean updateOrdersByOrderNum(Orders orders) throws YuleException;
	
}
