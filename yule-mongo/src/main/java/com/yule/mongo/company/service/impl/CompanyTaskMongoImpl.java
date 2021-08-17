package com.yule.mongo.company.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.yule.exception.YuleException;
import com.yule.mongo.company.service.ICompanyTaskMongo;
import com.yule.mongo.pojo.CompanyTask;
import com.yule.util.ConvertUtil;
import com.yule.vo.Page;
@Repository("companyTaskMongoImpl")
public class CompanyTaskMongoImpl implements ICompanyTaskMongo {

	@Autowired  
    private MongoTemplate mongoTemplate;
	
	public Page<CompanyTask> findCompanyTaskPage(String companyId,int pageSize, int pageNo) throws YuleException {
		Query query = new Query();
		query.addCriteria(new Criteria("company_id").is(companyId));
		query.skip((pageNo-1)*pageSize).limit(pageSize); 
		Page<CompanyTask> page = new Page<CompanyTask>();
		page.setDatas(this.mongoTemplate.find(query, CompanyTask.class));
		page.setPageNo(pageNo);
		page.setRowCount(ConvertUtil.longToInteger(this.mongoTemplate.count(query, CompanyTask.class)));
		return page;
	
	}
	public int findCompanyTaskCount(String companyId) throws YuleException {
		Query query = new Query();
		query.addCriteria(new Criteria("company_id").is(companyId));
		return ConvertUtil.longToInteger(this.mongoTemplate.count(query, CompanyTask.class));
	}
	}
