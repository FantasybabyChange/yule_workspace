package com.yule.mongo.detail.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.yule.exception.YuleException;
import com.yule.mongo.detail.service.IUserFavoriteMongo;
import com.yule.mongo.pojo.UserFavorite;
import com.yule.util.ConvertUtil;
import com.yule.util.DateUtil;

@Service("userFavoriteMongoImpl")
public class UserFavoriteMongoImpl implements IUserFavoriteMongo{
	
	@Autowired  
    private MongoTemplate mongoTemplate;
	public int findUserFavoriteByUserId(String userId, String companyId)throws YuleException {
		Query query=new Query();
		if (!StringUtils.isEmpty(userId)) {
			query.addCriteria(new Criteria("user_id").is(userId));	
		}
		
		query.addCriteria(new Criteria("company_id").is(companyId));
		return ConvertUtil.longToInteger(this.mongoTemplate.count(query, UserFavorite.class));
	}
	public boolean insertUserFavorite(UserFavorite  userFavorite)throws YuleException{
		boolean flag = false;
		try {
			if (StringUtils.isEmpty(userFavorite.getCreate_time())) {
				userFavorite.setCreate_time(DateUtil.getCurrentDate());
			}
			this.mongoTemplate.save(userFavorite);
			flag =true;
		} catch (Exception e) {
			throw new YuleException(e);
		}
		return flag;
	}

}
