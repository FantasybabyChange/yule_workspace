package com.yule.mongo.cooperators.service;

import com.yule.exception.YuleException;
import com.yule.mongo.pojo.SalesmanCooperator;


public interface ISalesmanCooperatorMongo {
	
	public boolean insertSalesmanCooperator(SalesmanCooperator salesmanCooperator) throws YuleException;
}
