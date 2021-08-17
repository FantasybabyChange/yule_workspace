package com.yule.mongo.detail.service;

import com.yule.exception.YuleException;
import com.yule.mongo.pojo.UserFavorite;


public interface IUserFavoriteMongo {
	public  int findUserFavoriteByUserId(String userId,String companyId) throws YuleException;
	
	public boolean insertUserFavorite(UserFavorite  userFavorite)throws YuleException;
	
}
