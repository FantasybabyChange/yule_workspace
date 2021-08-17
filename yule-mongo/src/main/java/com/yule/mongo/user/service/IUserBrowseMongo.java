package com.yule.mongo.user.service;

import java.util.List;

import com.yule.exception.YuleException;
import com.yule.mongo.user.vo.UserBrowseVO;
import com.yule.vo.Page;

public interface IUserBrowseMongo {
	
	public Page<UserBrowseVO> findUserBrowsePageByUserId(String userId,int pageSize,int pageNo) throws YuleException;
	
	public List<UserBrowseVO> findUserBrowseListByUserId(String userId,int pageSize,int pageNo) throws YuleException;

	public int findUserBrowse(String userId) throws YuleException;
	
}
