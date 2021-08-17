package com.yule.mongo.timer.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.yule.exception.YuleException;
import com.yule.mongo.pojo.CompanyTask;
import com.yule.mongo.timer.service.ICompanyTaskMongo;

@Repository("companyTaskMongoImpl")
public class CompanyTaskMongoImpl implements ICompanyTaskMongo{

	@Autowired  
    private MongoTemplate mongoTemplate;  
	
	public boolean batchInsertCompanyTask(List<CompanyTask> companyTasks) throws YuleException {
		boolean flag = false;
		try{
			this.mongoTemplate.insertAll(companyTasks);
			flag = true;
		}catch(Exception e){
			throw new YuleException(e);
		}
		return flag;
	}

	public boolean deleteCompanyTask(String companyId) throws YuleException {
		boolean flag = false;
		Query query = new Query();  
		query.addCriteria(new Criteria("company_id").is(companyId));  
		try{
			this.mongoTemplate.remove(query, CompanyTask.class);
			flag = true;
		}catch(Exception e){
			throw new YuleException(e);
		}
		return flag;
	}

}
