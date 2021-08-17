package com.yule.company.service;

import com.yule.company.vo.UserVO;
import com.yule.exception.YuleException;
import com.yule.pojo.User;

public interface IUserService {

	public boolean updateUserLevel(User user) throws YuleException;
	
	public UserVO findUserVOById(String id) throws YuleException;
}
