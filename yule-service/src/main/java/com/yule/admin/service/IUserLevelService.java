package com.yule.admin.service;

import java.util.List;

import com.yule.admin.param.InsertUserLevelParam;
import com.yule.exception.YuleException;
import com.yule.pojo.UserLevel;
public interface IUserLevelService {

	public boolean updateUserLevel(UserLevel userLevel) throws YuleException;
	
	public boolean insertUserLevel(UserLevel userLevel) throws YuleException;

	public boolean deleteUserLevelAll() throws YuleException;
	
	public boolean deleteUserLevelById(String id) throws YuleException;
	
	public List<UserLevel> findUserLevelList() throws YuleException;
	
	public boolean batchInsertLevel(List<UserLevel> userLevels)throws YuleException;
	/**
	 * 批量新增并更新
	 */
	public boolean batchInsertAndUpdateUserLevel(InsertUserLevelParam insertUserLevelParam) throws YuleException;
	
	
	
}
