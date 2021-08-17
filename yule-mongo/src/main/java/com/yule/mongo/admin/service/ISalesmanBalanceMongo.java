package com.yule.mongo.admin.service;

import com.yule.exception.YuleException;
import com.yule.mongo.admin.query.SalesmanBalanceQuery;
import com.yule.mongo.admin.vo.SalesmanBalanceVO;
import com.yule.mongo.pojo.SalesmanBalance;
import com.yule.vo.Page;

public interface ISalesmanBalanceMongo {
	

	public Page<SalesmanBalanceVO> findSalesmanBalancePageByQuery(SalesmanBalanceQuery salesmanBalanceQuery,int pageSize, int pageNo)throws YuleException; 

	public boolean updateSalesmanBalance(SalesmanBalance salesmanBalance)throws YuleException;
}
