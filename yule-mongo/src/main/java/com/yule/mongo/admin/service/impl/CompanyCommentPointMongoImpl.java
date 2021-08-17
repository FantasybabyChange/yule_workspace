package com.yule.mongo.admin.service.impl;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.yule.exception.YuleException;
import com.yule.mongo.admin.service.ICompanyCommentPointMongo;
import com.yule.mongo.pojo.CompanyCommentPoint;

@Repository("companyCommentPointMongoImpl")
public  class CompanyCommentPointMongoImpl implements ICompanyCommentPointMongo{
	@Autowired  
    private MongoTemplate mongoTemplate;
	public List<CompanyCommentPoint> findCompanyCommentPoint(String order_num)throws YuleException {
		Query q = new Query();
		q.addCriteria(new Criteria("order_num").is((order_num)));
		return this.mongoTemplate.find(q, CompanyCommentPoint.class);
		}
	}
