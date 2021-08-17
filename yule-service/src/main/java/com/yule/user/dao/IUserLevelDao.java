package com.yule.user.dao;

import com.yule.exception.YuleException;
import com.yule.pojo.UserLevel;

public interface IUserLevelDao {

	public UserLevel findUserLevelByUserId(String userId) throws YuleException;
	
}