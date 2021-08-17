package com.yule.user.dao;

import com.yule.exception.YuleException;
import com.yule.pojo.UserLogin;

public interface IUserLoginDao {

	public int updateUserLogin(UserLogin userLogin) throws YuleException;
	
	public int findUserLoginCountByAccount(String account) throws YuleException;
	
	public int findUserLoginCount() throws YuleException;
	
}
