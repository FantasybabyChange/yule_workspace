package com.yule.user.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yule.exception.YuleException;
import com.yule.pojo.User;
import com.yule.user.dao.IUserDao;
import com.yule.user.service.IUserService;
import com.yule.user.vo.UserVO;

@Service("userServiceImpl")
public class UserServiceImpl implements IUserService {

	@Autowired
	private IUserDao userDao;

	public UserVO findUserVOById(String id) throws YuleException {
		return userDao.findUserVOById(id);
	}

	public boolean updateUser(User user) throws YuleException {
		boolean flag = false;
		try{
			userDao.updateUser(user);
			flag = true;
		} catch (Exception e) {
	    	throw new YuleException(e);
	    }
		return flag;
	}

}
