package com.yule.mongo.orders.service;

import com.yule.exception.YuleException;
import com.yule.mongo.pojo.UserScore;

public interface IUserScoreMongo {

	
	public boolean insertUserScore(UserScore userScore) throws YuleException;
	
	public int findUserScore(String id)throws YuleException;
}
