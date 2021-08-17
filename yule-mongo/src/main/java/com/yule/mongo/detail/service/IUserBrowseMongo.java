package com.yule.mongo.detail.service;

import com.yule.exception.YuleException;
import com.yule.mongo.pojo.UserBrowse;

public interface IUserBrowseMongo {
	public boolean insertUserBrowse(UserBrowse userBrowse)throws YuleException;
}
