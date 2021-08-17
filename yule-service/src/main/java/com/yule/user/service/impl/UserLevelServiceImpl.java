package com.yule.user.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yule.exception.YuleException;
import com.yule.pojo.UserLevel;
import com.yule.user.dao.IUserLevelDao;
import com.yule.user.service.IUserLevelService;

@Service("userLevelServiceImpl")
public class UserLevelServiceImpl implements IUserLevelService {

	@Autowired
	private IUserLevelDao userLevelDaoImpl;

	public UserLevel findUserLevelByUserId(String userId) throws YuleException {
		return this.userLevelDaoImpl.findUserLevelByUserId(userId);
	}

}
