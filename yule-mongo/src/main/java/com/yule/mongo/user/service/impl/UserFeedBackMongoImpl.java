package com.yule.mongo.user.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import com.yule.exception.YuleException;
import com.yule.mongo.pojo.UserFeedBack;
import com.yule.mongo.user.service.IUserFeedBackMongo;

@Service("userFeedBackMongoImpl")
public class UserFeedBackMongoImpl implements IUserFeedBackMongo{
	
	@Autowired  
    private MongoTemplate mongoTemplate;

	public boolean insertUserFeedBack(UserFeedBack userFeedBack) throws YuleException {
		boolean flag = false;
		try{
			mongoTemplate.save(userFeedBack);
			flag = true;
		}catch(Exception e){
			throw new YuleException(e);
		}
		return flag;
	}

}
