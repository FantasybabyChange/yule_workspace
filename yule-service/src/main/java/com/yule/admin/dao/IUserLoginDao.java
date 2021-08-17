package com.yule.admin.dao;

import java.util.List;
import java.util.Map;

import com.yule.exception.YuleException;
import com.yule.pojo.UserLogin;

public interface IUserLoginDao {
	
	
	public int deleteUserLoginById(UserLogin userLogin) throws YuleException;
	
	public int updateUserLogin(UserLogin userLogin) throws YuleException;
	
	public UserLogin findUserLoginById(String id) throws YuleException;

	public List<UserLogin> findUserLoginList(Map<String, Object> params) throws YuleException;
	
	public int findUserLoginCount(Map<String, Object> params) throws YuleException;
	
}
