package com.yule.admin.dao;

import com.yule.admin.vo.UserVO;
import com.yule.exception.YuleException;
import com.yule.pojo.User;


public interface IUserDao {

	public int updateUser(User User) throws YuleException;
	
	public int deleteUserById(User user);
	
	public UserVO findUserVOById(String id) throws YuleException;
}
