package com.yule.user.service;

import com.yule.exception.YuleException;
import com.yule.pojo.UserInterest;
import com.yule.user.vo.UserInterestVO;

public interface IUserInterestService {

	public UserInterestVO findUserInterestVOById(String id) throws YuleException;
	
	public boolean updateUserInterest(UserInterest userInterest) throws YuleException;
	
}
