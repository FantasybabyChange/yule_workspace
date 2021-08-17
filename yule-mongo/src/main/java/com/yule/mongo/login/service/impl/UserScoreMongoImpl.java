package com.yule.mongo.login.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import com.yule.exception.YuleException;
import com.yule.mongo.login.service.IUserScoreMongo;
import com.yule.mongo.pojo.UserScore;

@Service("userScoreMongoImpl")
public class UserScoreMongoImpl implements IUserScoreMongo{
	
	@Autowired  
    private MongoTemplate mongoTemplate;

	public boolean insertUserScore(UserScore userScore) throws YuleException{
		boolean flag = false;
		try{
			mongoTemplate.save(userScore);
			flag = true;
		}catch(Exception e){
			throw new YuleException(e);
		}
		return flag;
	}

}
