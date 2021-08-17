package com.yule.user.dao;

import com.yule.exception.YuleException;
import com.yule.pojo.User;
import com.yule.user.vo.UserVO;

public interface IUserDao {

	public UserVO findUserVOById(String id) throws YuleException;
	
	public int updateUser(User user) throws YuleException;
	
}