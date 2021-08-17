package com.yule.mongo.salesman.service;

import com.yule.exception.YuleException;
import com.yule.mongo.salesman.query.SalesmanBalanceQuery;
import com.yule.mongo.salesman.vo.SalesmanBalanceVO;
import com.yule.vo.Page;

public interface ISalesmanBalanceMongo {
	
	public SalesmanBalanceVO findNowMonthSalesmanBalanceVOByQuery(SalesmanBalanceQuery salesmanBalanceQuery)throws YuleException;

	public SalesmanBalanceVO findSalesmanBalanceVOByQuery(SalesmanBalanceQuery salesmanBalanceQuery)throws YuleException;

	public Page<SalesmanBalanceVO> findSalesmanBalancePageByQuery(SalesmanBalanceQuery salesmanBalanceQuery,int pageSize, int pageNo)throws YuleException; 

}
