package com.yule.mongo.login.service;

import com.yule.exception.YuleException;
import com.yule.mongo.pojo.UserScore;

public interface IUserScoreMongo {
	
	public boolean insertUserScore(UserScore userScore) throws YuleException;

}
