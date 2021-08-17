package com.yule.mongo.salesman.service;

import java.util.List;

import com.yule.exception.YuleException;
import com.yule.mongo.pojo.Orders;
import com.yule.mongo.query.OrdersQuery;
import com.yule.mongo.salesman.vo.SaleVo;
import com.yule.vo.Page;


public interface IOrdersMongo {
	
	public List<SaleVo>  findOrdersToStatistics(OrdersQuery ordersQuery) throws YuleException;
	
	public Page<Orders> findOrdersByCompany(OrdersQuery ordersQuery,int pageSize,int pageNo) throws YuleException;

}
