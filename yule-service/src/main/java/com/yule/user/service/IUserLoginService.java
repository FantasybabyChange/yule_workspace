package com.yule.user.service;

import com.yule.exception.YuleException;
import com.yule.pojo.UserLogin;

public interface IUserLoginService {

	public boolean updateUserLogin(UserLogin userLogin) throws YuleException;
	
	public int findUserLoginCountByAccount(String account) throws YuleException;
	
	public int findUserLoginCount() throws YuleException;
	
}
