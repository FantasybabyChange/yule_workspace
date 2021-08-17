package com.yule.mongo.salesman.service;


import com.yule.exception.YuleException;
import com.yule.mongo.pojo.SalesmanCooperator;

public interface ISalesmanCooperatorMongo {
	public boolean insertSalesmanCooperator(SalesmanCooperator salesmanCooperator)throws YuleException;
}
