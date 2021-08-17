package com.yule.company.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yule.company.dao.IUserDao;
import com.yule.company.service.IUserService;
import com.yule.company.vo.UserVO;
import com.yule.exception.YuleException;
import com.yule.pojo.User;

@Service("userServiceImpl")
public class UserServiceImpl implements IUserService{

	@Autowired
	private IUserDao userDao;
	
	public boolean updateUserLevel(User user) throws YuleException {
		boolean flag = false;
		try {
			this.userDao.updateUserLevel(user);
			flag =true;
		} catch (Exception e) {
			throw new YuleException(e);
		}
		return flag;
	}
	
	public UserVO findUserVOById(String id) throws YuleException{
		return this.userDao.findUserVOById(id);
	}

}
