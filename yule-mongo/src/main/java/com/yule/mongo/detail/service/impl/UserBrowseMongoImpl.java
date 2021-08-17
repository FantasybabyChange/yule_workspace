package com.yule.mongo.detail.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.yule.exception.YuleException;
import com.yule.mongo.detail.service.IUserBrowseMongo;
import com.yule.mongo.pojo.UserBrowse;
import com.yule.util.DateUtil;


@Service("userBrowseMongoImpl")
public class UserBrowseMongoImpl implements IUserBrowseMongo{
	@Autowired  
    private MongoTemplate mongoTemplate;
	public boolean insertUserBrowse(UserBrowse userBrowse)throws YuleException{
		boolean flag = false;
		try {
			if (StringUtils.isEmpty(userBrowse.getCreate_time())) {
				userBrowse.setCreate_time(DateUtil.getCurrentDate());
			}
			this.mongoTemplate.save(userBrowse);
			flag = true;
		} catch (Exception e) {
			throw new YuleException(e);
		}
		return flag;
	}
}