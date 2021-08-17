package com.yule.mongo.user.service.impl;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

import com.yule.exception.YuleException;
import com.yule.mongo.pojo.CompanyCommentPoint;
import com.yule.mongo.user.service.ICompanyCommentPointMongo;

@Repository("companyPointMongoImpl")
public  class CompanyCommentPointMongoImpl implements ICompanyCommentPointMongo{
	@Autowired  
    private MongoTemplate mongoTemplate;
	public boolean insertCompanyPoint(List<CompanyCommentPoint> companyPoint)throws YuleException{
		boolean flag = false;
		try {
			this.mongoTemplate.insertAll(companyPoint);
			flag = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}
}
