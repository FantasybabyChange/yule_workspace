package com.yule.mongo.user.service;

import com.yule.exception.YuleException;
import com.yule.mongo.pojo.UserFeedBack;

public interface IUserFeedBackMongo {
	
	public boolean insertUserFeedBack(UserFeedBack userFeedBack) throws YuleException;

}
