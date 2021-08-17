package com.yule.mongo.admin.service;


import com.yule.exception.YuleException;
import com.yule.mongo.admin.query.SalesmanCooperatorQuery;
import com.yule.mongo.pojo.SalesmanCooperator;
import com.yule.vo.Page;

public interface ISalesmanCooperatorMongo {
	public Page<SalesmanCooperator> findSalesmanCooperatorPage(SalesmanCooperatorQuery salesmanCooperatorQuery, int pageSize,int pageNo) throws YuleException;
	
	public boolean updateSalesmanCooperator(SalesmanCooperator salesmanCooperator) throws YuleException ;
}
