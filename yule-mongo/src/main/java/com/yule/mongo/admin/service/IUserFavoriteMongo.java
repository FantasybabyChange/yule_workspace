package com.yule.mongo.admin.service;

import com.yule.exception.YuleException;
import com.yule.mongo.admin.query.UserFavoriteQuery;
import com.yule.mongo.pojo.UserFavorite;
import com.yule.vo.Page;

public interface IUserFavoriteMongo {

	public Page<UserFavorite> findUserFavoritePageByUserId(UserFavoriteQuery userFavoriteQuery,int pageSize,int pageNo) throws YuleException;
	
}
