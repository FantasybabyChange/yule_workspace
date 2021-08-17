package com.yule.mongo.timer.service;

import java.util.List;

import com.yule.exception.YuleException;
import com.yule.mongo.pojo.SalesmanBalance;

public interface ISalesmanBalanceMongo {

	public boolean batchInsertSalesmanBalance(List<SalesmanBalance> salesmanBalance)throws YuleException;
	
}
