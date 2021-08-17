package com.yule.mongo.company.service;

import java.util.List;

import com.yule.exception.YuleException;
import com.yule.mongo.company.query.BalanceQuery;
import com.yule.mongo.company.vo.BalanceVo;
import com.yule.mongo.company.vo.SaleVo;
import com.yule.mongo.pojo.Orders;
import com.yule.mongo.query.OrdersQuery;
import com.yule.vo.Page;


public interface IOrdersMongo {
	
	public Page<Orders> findOrdersPage(OrdersQuery ordersQuery,int pageSize,int pageNo) throws YuleException;
	
	public Page<Orders> findOrdersPageByCompanyId(String companyId,int status,int pageSize,int pageNo) throws YuleException;
	
	public int findOrdersCount(String companyId,int status) throws YuleException;
	
	public boolean updateOrders(Orders order) throws YuleException;
	
	public boolean updateOrdersByNum(Orders orders) throws YuleException;
	
	public Orders findOrdersById(String id) throws YuleException;
	
	public List<SaleVo>  findOrdersToStatistics(OrdersQuery ordersQuery) throws YuleException;
	
	public Orders findOrdersByNum(String order_num) throws YuleException;
	//查询上一个月的结算
	public BalanceVo findLastMonthBalance(BalanceQuery balanceQuery)throws YuleException;
	
	public Page<BalanceVo> findHistoryBalance(BalanceQuery balanceQuery,int pageSize, int pageNo) throws YuleException ;
}
