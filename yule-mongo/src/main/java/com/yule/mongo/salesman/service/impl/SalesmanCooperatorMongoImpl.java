package com.yule.mongo.salesman.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

import com.yule.exception.YuleException;
import com.yule.mongo.pojo.SalesmanCooperator;
import com.yule.mongo.salesman.service.ISalesmanCooperatorMongo;
import com.yule.util.DateUtil;

@Repository("salesmanCooperatorMongoImpl")
public class SalesmanCooperatorMongoImpl implements ISalesmanCooperatorMongo{

	@Autowired  
    private MongoTemplate mongoTemplate;  
	public boolean insertSalesmanCooperator(SalesmanCooperator salesmanCooperator)throws YuleException{
		boolean flag = false;
		try{
			if(null==salesmanCooperator.getCreate_time()){
				salesmanCooperator.setCreate_time(DateUtil.getCurrentDate());
			}
			this.mongoTemplate.save(salesmanCooperator);
			flag = true;
		}catch(Exception e){
			throw new YuleException(e);
		}
		return flag;
	}
}
