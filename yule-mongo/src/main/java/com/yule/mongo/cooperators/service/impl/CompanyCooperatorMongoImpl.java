package com.yule.mongo.cooperators.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

import com.yule.exception.YuleException;
import com.yule.mongo.cooperators.service.ICompanyCooperatorMongo;
import com.yule.mongo.pojo.CompanyCooperator;
import com.yule.util.DateUtil;

@Repository("companyCooperatorMongoImpl")
public class CompanyCooperatorMongoImpl implements ICompanyCooperatorMongo{
	@Autowired  
    private MongoTemplate mongoTemplate;  
	public boolean insertCompanyCooperator(CompanyCooperator companyCooperator)throws YuleException{
		boolean flag = false;
		try{
			if(null==companyCooperator.getCreate_time()){
				companyCooperator.setCreate_time(DateUtil.getCurrentDate());
			}
			this.mongoTemplate.save(companyCooperator);
			flag = true;
		}catch(Exception e){
			throw new YuleException(e);
		}
		return flag;
	}
}
