package com.yule.mongo.admin.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.yule.constant.DeleteConst;
import com.yule.exception.YuleException;
import com.yule.mongo.admin.query.UserFavoriteQuery;
import com.yule.mongo.admin.service.IUserFavoriteMongo;
import com.yule.mongo.pojo.UserFavorite;
import com.yule.util.ConvertUtil;
import com.yule.vo.Page;

@Repository("userFavoriteMongoImpl")
public class UserFavoriteMongoImpl implements IUserFavoriteMongo{

	@Autowired
    private MongoTemplate mongoTemplate;

	public Page<UserFavorite> findUserFavoritePageByUserId(UserFavoriteQuery userFavoriteQuery, int pageSize, int pageNo)throws YuleException {
		Query query=new Query();
		if (!StringUtils.isEmpty(userFavoriteQuery.getUserId())) {
			query.addCriteria(new Criteria("user_id").is(userFavoriteQuery.getUserId()));
		}
		if (!StringUtils.isEmpty(userFavoriteQuery.getCompanyId())) {
			query.addCriteria(new Criteria("company_id").is(userFavoriteQuery.getCompanyId()));
		}
		query.addCriteria(new Criteria("is_delete").is(DeleteConst.IS_DELETE_TRUE));
		query.skip((pageNo-1)*pageSize).limit(pageSize);   
		Page<UserFavorite> page = new Page<UserFavorite>();
		query.with(new Sort(new Order(Direction.DESC,"create_time")));  
		page.setDatas(this.mongoTemplate.find(query, UserFavorite.class));
		page.setPageNo(pageNo);
		page.setRowCount(ConvertUtil.longToInteger(this.mongoTemplate.count(query, UserFavorite.class)));
		return page;
	}


}
