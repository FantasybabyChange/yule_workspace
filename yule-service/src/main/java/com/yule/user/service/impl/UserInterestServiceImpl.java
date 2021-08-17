package com.yule.user.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yule.exception.YuleException;
import com.yule.pojo.UserInterest;
import com.yule.user.dao.IUserInterestDao;
import com.yule.user.service.IUserInterestService;
import com.yule.user.vo.UserInterestVO;

@Service("userInterestServiceImpl")
public class UserInterestServiceImpl implements IUserInterestService {

	@Autowired
	private IUserInterestDao userInterestDao;

	public UserInterestVO findUserInterestVOById(String id) throws YuleException {
		return userInterestDao.findUserInterestVOById(id);
	}

	public boolean updateUserInterest(UserInterest userInterest) throws YuleException {
		boolean flag=false;
		try {
			this.userInterestDao.updateUserInterest(userInterest);
			flag=true;
		} catch (Exception e) {
	    	throw new YuleException(e);
	    }
		return flag;
	}

}
