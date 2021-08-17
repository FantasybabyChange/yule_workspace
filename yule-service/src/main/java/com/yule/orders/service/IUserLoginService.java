package com.yule.orders.service;

import com.yule.exception.YuleException;
import com.yule.orders.vo.UserAreaVO;
import com.yule.pojo.UserLogin;

public interface IUserLoginService {
	
	public UserAreaVO findUserLoginVOById(String id) throws YuleException;
	
	public boolean updateUserLogin(UserLogin userLogin) throws YuleException ;
}
