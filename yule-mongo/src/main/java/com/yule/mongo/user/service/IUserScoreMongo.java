package com.yule.mongo.user.service;

import com.yule.exception.YuleException;
import com.yule.mongo.pojo.UserScore;
import com.yule.mongo.user.query.UserScoreQuery;
import com.yule.vo.Page;

public interface IUserScoreMongo {
	
	public Page<UserScore> findUserScorePage(UserScoreQuery userScoreQuery,int pageSize,int pageNo) throws YuleException;
	
	public int findUserScore(String userId) throws YuleException;
	
	public boolean insertUserScore(UserScore userScore)throws YuleException;

}
