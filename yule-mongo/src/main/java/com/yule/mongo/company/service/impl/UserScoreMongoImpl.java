package com.yule.mongo.company.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import com.yule.exception.YuleException;
import com.yule.mongo.company.service.IUserScoreMongo;
import com.yule.mongo.pojo.UserScore;
import com.yule.util.DateUtil;

@Service("userScoreMongoImpl")
public class UserScoreMongoImpl implements IUserScoreMongo{

	@Autowired  
    private MongoTemplate mongoTemplate;  
	
	public boolean insertUserScore(UserScore userScore) throws YuleException {
		boolean flag = false;
		try{
			if(null==userScore.getCreate_time()){
				userScore.setCreate_time(DateUtil.getCurrentDate());
			}
			this.mongoTemplate.save(userScore);
			flag = true;
		}catch(Exception e){
			throw new YuleException(e);
		}
		return flag;
	}

}
