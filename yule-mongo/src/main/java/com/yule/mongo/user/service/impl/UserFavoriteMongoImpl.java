package com.yule.mongo.user.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import com.yule.constant.DeleteConst;
import com.yule.exception.YuleException;
import com.yule.mongo.pojo.UserFavorite;
import com.yule.mongo.user.service.IUserFavoriteMongo;
import com.yule.util.ConvertUtil;
import com.yule.vo.Page;

@Service("userFavoritesMongoImpl")
public class UserFavoriteMongoImpl implements IUserFavoriteMongo{
	
	@Autowired  
    private MongoTemplate mongoTemplate;

	public Page<UserFavorite> findUserFavoritePageByUserId(String userId,int pageSize, int pageNo) throws YuleException {
		Query query=new Query();
		query.addCriteria(new Criteria("user_id").is(userId));
		query.addCriteria(new Criteria("is_delete").is(DeleteConst.IS_DELETE_TRUE));
		query.skip((pageNo-1)*pageSize).limit(pageSize);   
		Page<UserFavorite> page = new Page<UserFavorite>();
		query.with(new Sort(new Order(Direction.DESC,"create_time")));  
		page.setDatas(this.mongoTemplate.find(query, UserFavorite.class));
		page.setPageNo(pageNo);
		page.setRowCount(ConvertUtil.longToInteger(this.mongoTemplate.count(query, UserFavorite.class)));
		return page;
	}

	public int findUserFavorite(String userId) throws YuleException {
		Query query=new Query();
		query.addCriteria(new Criteria("user_id").is(userId));
		return ConvertUtil.longToInteger(this.mongoTemplate.count(query, UserFavorite.class));
	}

}
