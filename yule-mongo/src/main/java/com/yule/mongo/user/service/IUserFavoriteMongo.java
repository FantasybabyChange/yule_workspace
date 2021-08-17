package com.yule.mongo.user.service;

import com.yule.exception.YuleException;
import com.yule.mongo.pojo.UserFavorite;
import com.yule.vo.Page;

public interface IUserFavoriteMongo {
	
	public Page<UserFavorite> findUserFavoritePageByUserId(String userId,int pageSize,int pageNo) throws YuleException;

	public int findUserFavorite(String userId) throws YuleException;
	
}
