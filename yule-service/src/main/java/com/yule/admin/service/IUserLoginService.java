package com.yule.admin.service;

import com.yule.admin.query.UserLoginQuery;
import com.yule.exception.YuleException;
import com.yule.pojo.UserLogin;
import com.yule.vo.Page;

public interface IUserLoginService {
	
	public boolean updateUserLogin(UserLogin userLogin) throws YuleException ;

	public Page<UserLogin> findUserLoginPage(UserLoginQuery userLoginQuery,int pageSize, int pageNo)throws YuleException;
	
	
	public UserLogin findUserLoginById(String id) throws YuleException;
	
	public boolean deleteUserLoginById(UserLogin userLogin) throws YuleException;
}                 
