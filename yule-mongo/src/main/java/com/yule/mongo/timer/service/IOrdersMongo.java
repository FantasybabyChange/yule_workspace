package com.yule.mongo.timer.service;

import com.yule.exception.YuleException;
import com.yule.mongo.timer.vo.CompanyOrdersBalanceVO;


public interface IOrdersMongo {
	
	public long findOrdersCount(String companyId)  throws YuleException ;
	
	public CompanyOrdersBalanceVO findLastMonthOrdersByCompanyId(String companyId,String month) throws YuleException ;
	
	public CompanyOrdersBalanceVO findOrdersByCompanyId(String companyId,String startTime,String endTime) throws YuleException;
	
}
