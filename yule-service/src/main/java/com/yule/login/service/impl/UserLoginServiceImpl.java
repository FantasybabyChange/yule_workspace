package com.yule.login.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yule.exception.YuleException;
import com.yule.login.dao.IUserLoginDao;
import com.yule.login.service.IUserLoginService;
import com.yule.login.vo.UserLoginInfoVO;
import com.yule.pojo.UserLogin;
import com.yule.util.EncryptUtil;
import com.yule.util.IDUtil;
import com.yule.vo.UserLoginVO;

@Service("userLoginServiceImpl")
public class UserLoginServiceImpl implements IUserLoginService {

	@Autowired
	private IUserLoginDao userLoginDao;

	public UserLoginVO findUserLoginVOByAccount(String account) throws YuleException {
		return this.userLoginDao.findUserLoginVOByAccount(account);
	}
	
	public int findUserLoginCountByAccount(String account) throws YuleException {
		return this.userLoginDao.findUserLoginCountByAccount(account);
	}

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

	public boolean insertUserLogin(UserLogin userLogin) throws YuleException {
		boolean flag=false;
		try {
			if (null!=userLogin.getPassword()) {
				userLogin.setPassword(EncryptUtil.encryptToMD5(userLogin.getPassword()));
			}
			userLogin.setId(IDUtil.getID());
			this.userLoginDao.insertUserLogin(userLogin);
			flag=true;
		} catch (Exception e) {
	    	throw new YuleException(e);
	    }
		return flag;
	}

	public UserLoginInfoVO findUserLoginInfoVOByAccount(String account) throws YuleException {
		return this.userLoginDao.findUserLoginInfoVOByAccount(account);
	}

	public UserLoginVO findUserLoginVOById(String id) throws YuleException {
		return this.userLoginDao.findUserLoginVOById(id);
	}

}
