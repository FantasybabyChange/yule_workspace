package com.yule.login.dao;

import com.yule.exception.YuleException;
import com.yule.login.vo.UserLoginInfoVO;
import com.yule.pojo.UserLogin;
import com.yule.vo.UserLoginVO;

public interface IUserLoginDao {

	public UserLoginVO findUserLoginVOByAccount(String account) throws YuleException;
	
	public UserLoginInfoVO findUserLoginInfoVOByAccount(String account) throws YuleException;
	
	public UserLoginVO findUserLoginVOById(String id) throws YuleException;
	
	public int findUserLoginCountByAccount(String account) throws YuleException;
	
	public int insertUserLogin(UserLogin userLogin) throws YuleException;
	
	public int updateUserLogin(UserLogin userLogin) throws YuleException;
	
}
