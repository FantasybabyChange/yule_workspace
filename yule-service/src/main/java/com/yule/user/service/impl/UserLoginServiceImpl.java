package com.yule.user.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yule.exception.YuleException;
import com.yule.pojo.UserLogin;
import com.yule.user.dao.IUserLoginDao;
import com.yule.user.service.IUserLoginService;
import com.yule.util.EncryptUtil;

@Service("userLoginServiceImpl")
public class UserLoginServiceImpl implements IUserLoginService {

	@Autowired
	private IUserLoginDao userLoginDao;

	public boolean updateUserLogin(UserLogin userLogin) throws YuleException {
		boolean flag=false;
		try {
			if (null!=userLogin.getPassword()) {
				userLogin.setPassword(EncryptUtil.encryptToMD5(userLogin.getPassword()));
			}
			this.userLoginDao.updateUserLogin(userLogin);
			flag=true;
		} catch (Exception e) {
	    	throw new YuleException(e);
	    }
		return flag;
	}

	public int findUserLoginCountByAccount(String account) throws YuleException {
		return this.userLoginDao.findUserLoginCountByAccount(account);
	}

	public int findUserLoginCount() throws YuleException {
		return this.userLoginDao.findUserLoginCount();
	}
	
}
