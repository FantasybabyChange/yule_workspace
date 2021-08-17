package com.yule.mongo.timer.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

import com.yule.exception.YuleException;
import com.yule.mongo.pojo.CompanyBalance;
import com.yule.mongo.timer.service.ICompanyBalanceMongo;

@Repository("companyBalanceMongoImpl")
public class CompanyBalanceMongoImpl implements ICompanyBalanceMongo{
	@Autowired  
    private MongoTemplate mongoTemplate;  
	
	public boolean batchInsertCompanyBalance(List<CompanyBalance> companyBalances)throws YuleException {
		boolean flag = false;
		try {
			mongoTemplate.insert(companyBalances, CompanyBalance.class);
			flag = true;
		} catch (Exception e) {
			throw new YuleException(e);
		}
		return flag;
	}

}
