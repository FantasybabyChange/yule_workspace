package com.yule.user.dao;

import com.yule.exception.YuleException;
import com.yule.pojo.UserInterest;
import com.yule.user.vo.UserInterestVO;

public interface IUserInterestDao {

	public UserInterestVO findUserInterestVOById(String id) throws YuleException;
	
	public int updateUserInterest(UserInterest userInterest) throws YuleException;
	
}