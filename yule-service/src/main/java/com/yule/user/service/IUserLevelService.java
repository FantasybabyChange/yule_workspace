package com.yule.user.service;

import com.yule.exception.YuleException;
import com.yule.pojo.UserLevel;

public interface IUserLevelService {

	public UserLevel findUserLevelByUserId(String userId) throws YuleException;
	
}
