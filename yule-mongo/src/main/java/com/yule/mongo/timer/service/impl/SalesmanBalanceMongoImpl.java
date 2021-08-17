package com.yule.mongo.timer.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

import com.yule.exception.YuleException;
import com.yule.mongo.pojo.SalesmanBalance;
import com.yule.mongo.timer.service.ISalesmanBalanceMongo;

@Repository("salesmanBalanceMongoImpl")
public class SalesmanBalanceMongoImpl implements ISalesmanBalanceMongo{
	
	@Autowired  
    private MongoTemplate mongoTemplate;  
	
	public boolean batchInsertSalesmanBalance(List<SalesmanBalance> salesmanBalance) throws YuleException {
		boolean flag = false;
		try {
			mongoTemplate.insert(salesmanBalance, SalesmanBalance.class);
			flag = true;
		} catch (Exception e) {
			throw new YuleException(e);
		}
		return flag;
	}


}
