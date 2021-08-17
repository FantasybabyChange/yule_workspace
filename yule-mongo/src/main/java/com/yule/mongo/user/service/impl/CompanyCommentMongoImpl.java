package com.yule.mongo.user.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

import com.yule.exception.YuleException;
import com.yule.mongo.pojo.CompanyComment;
import com.yule.mongo.user.service.ICompanyCommentMongo;
import com.yule.util.DateUtil;

@Repository("companyCommentMongoImpl")
public class CompanyCommentMongoImpl implements ICompanyCommentMongo{
	@Autowired  
    private MongoTemplate mongoTemplate;  
	public boolean insertCompanyComment(CompanyComment companyComment) throws YuleException {
		boolean flag = false;
		try{
			if(null==companyComment.getCreate_time()){
				companyComment.setCreate_time(DateUtil.getCurrentDate());
			}
			mongoTemplate.insert(companyComment);
			flag = true;
		}catch(Exception e){
			throw new YuleException(e);
		}
		return flag;
	}


}
