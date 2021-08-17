package com.yule.admin.dao;

import java.util.List;

import com.yule.exception.YuleException;
import com.yule.pojo.UserLevel;

public interface IUserLevelDao {
	
	public int updateUserLevel(UserLevel userLevel) throws YuleException;
	
	public int insertUserLevel(UserLevel userLevel) throws YuleException;

	public int deleteUserLevelAll() throws YuleException;
	
	public int deleteUserLevelById(String id) throws YuleException;
	
	public List<UserLevel> findUserLevelList() throws YuleException;

	public int batchInsertUserLevel(List<UserLevel> userLevels)throws YuleException;

	public int batchUpdateUserLevel(List<UserLevel> userLevels)throws YuleException;
	
	
}
