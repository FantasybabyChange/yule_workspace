package com.yule.orders.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yule.exception.YuleException;
import com.yule.orders.dao.IUserLoginDao;
import com.yule.orders.service.IUserLoginService;
import com.yule.orders.vo.UserAreaVO;
import com.yule.pojo.UserLogin;

@Service("userLoginServiceImpl")
public class UserLoginServiceImpl implements IUserLoginService{

	@Autowired
	private IUserLoginDao userLoginDao;
	
	public UserAreaVO findUserLoginVOById(String id) throws YuleException {
		return this.userLoginDao.findUserLoginVOById(id);
	}

	public boolean updateUserLogin(UserLogin userLogin) throws YuleException {
		boolean flag=false;
		try {
			this.userLoginDao.updateUserLogin(userLogin);
			flag=true;
		} catch (Exception e) {
	    	throw new YuleException(e);
	    }
		return flag;
	}
}
