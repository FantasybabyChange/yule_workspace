package com.yule.user.service;

import com.yule.exception.YuleException;
import com.yule.pojo.User;
import com.yule.user.vo.UserVO;

public interface IUserService {

	public UserVO findUserVOById(String id) throws YuleException;
	
	public boolean updateUser(User user) throws YuleException;
	
}
