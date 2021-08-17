package com.yule.admin.service;

import com.yule.admin.vo.UserVO;
import com.yule.exception.YuleException;
import com.yule.pojo.User;

public interface IUserService {

	public boolean updateUser(User User) throws YuleException;

	public UserVO findUserVOById(String id) throws YuleException;
}
