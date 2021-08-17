package com.yule.mongo.pc.service;


import java.util.List;

import com.yule.exception.YuleException;
import com.yule.mongo.pc.vo.OrdersHotAreaCityVO;
import com.yule.mongo.pc.vo.OrdersHotVO;
import com.yule.mongo.pojo.Orders;

public interface IOrdersMongo {
	
	public boolean insertOrders(Orders orders) throws YuleException;
	
	public Orders findOrdersNew(String companyId) throws YuleException;
	
	public OrdersHotVO findOrdersHotVO(String companyId) throws YuleException;
	
	public List<Orders> findOrdersList(String areaProvinceId,Integer pageSize,Integer pageNo) throws YuleException;
	
	public List<OrdersHotAreaCityVO> findOrdersHotAreaCityVOList(Integer pageSize,Integer pageNo) throws YuleException;
	
}
