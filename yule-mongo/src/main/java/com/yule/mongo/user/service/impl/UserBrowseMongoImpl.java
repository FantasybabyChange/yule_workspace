package com.yule.mongo.user.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import com.yule.exception.YuleException;
import com.yule.mongo.constant.CollectionConst;
import com.yule.mongo.pojo.UserBrowse;
import com.yule.mongo.user.service.IUserBrowseMongo;
import com.yule.mongo.user.vo.UserBrowseVO;
import com.yule.mongo.vo.CountVO;
import com.yule.util.ConvertUtil;
import com.yule.vo.Page;

@Service("userBrowseMongoImpl")
public class UserBrowseMongoImpl implements IUserBrowseMongo{
	
	@Autowired  
    private MongoTemplate mongoTemplate;

	public Page<UserBrowseVO> findUserBrowsePageByUserId(String userId,int pageSize, int pageNo) throws YuleException {
		Aggregation aggregation = Aggregation.newAggregation(UserBrowse.class,
			Aggregation.project("company_id","user_id","company_name","address_datail","area_county_name","area_city_name","create_time"),
			Aggregation.match(
            	new Criteria("user_id").is(userId)
            ),
			Aggregation.sort(Direction.DESC, "create_time"),
			Aggregation.group("company_id").count().as("count")
			.last("company_id").as("company_id")
			.last("company_name").as("company_name")
			.last("address_datail").as("address_datail")
			.last("area_county_name").as("area_county_name")
			.last("area_city_name").as("area_city_name")
			.last("create_time").as("create_time")
			.last("user_id").as("user_id"),
			Aggregation.skip((pageNo-1)*pageSize),
			Aggregation.limit(pageSize)
		);
		AggregationResults<UserBrowseVO> result = mongoTemplate.aggregate(aggregation,CollectionConst.USER_BROWSE,UserBrowseVO.class);
		Page<UserBrowseVO> page = new Page<UserBrowseVO>();
		page.setDatas(new ArrayList<UserBrowseVO>(result.getMappedResults()));
		page.setPageNo(pageNo);
		Aggregation aggregationCount = Aggregation.newAggregation(UserBrowse.class,
			Aggregation.project("company_id","user_id"),
			Aggregation.match(
            	new Criteria("user_id").is(userId)
            ),
			Aggregation.group("company_id").count().as("company_count")
			.last("user_id").as("user_id"),
			Aggregation.group("user_id").count().as("count")
		);
		AggregationResults<CountVO> resultCount = mongoTemplate.aggregate(aggregationCount,CollectionConst.USER_BROWSE,CountVO.class);
		if(null!=resultCount.getUniqueMappedResult()){
			page.setRowCount(resultCount.getUniqueMappedResult().getCount());
		}else{
			page.setRowCount(0);
		}
		return page;
	}
	
	public List<UserBrowseVO> findUserBrowseListByUserId(String userId,int pageSize, int pageNo) throws YuleException {
		Aggregation aggregation = Aggregation.newAggregation(UserBrowse.class,
			Aggregation.project("company_id","user_id","company_name","address_datail","area_county_name","area_city_name","create_time"),
			Aggregation.match(
            	new Criteria("user_id").is(userId)
            ),
			Aggregation.sort(Direction.DESC, "create_time"),
			Aggregation.group("company_id").count().as("count")
			.last("company_id").as("company_id")
			.last("company_name").as("company_name")
			.last("address_datail").as("address_datail")
			.last("area_county_name").as("area_county_name")
			.last("area_city_name").as("area_city_name")
			.last("company_category_name").as("company_category_name")
			.last("company_grade_name").as("company_grade_name")
			.last("create_time").as("create_time")
			.last("user_id").as("user_id"),
			Aggregation.skip((pageNo-1)*pageSize),
			Aggregation.limit(pageSize)
		);
		AggregationResults<UserBrowseVO> result = mongoTemplate.aggregate(aggregation,CollectionConst.USER_BROWSE,UserBrowseVO.class);
		return new ArrayList<UserBrowseVO>(result.getMappedResults());
	}

	public int findUserBrowse(String userId) throws YuleException {
		Query query=new Query();
		query.addCriteria(new Criteria("user_id").is(userId));
		return ConvertUtil.longToInteger(this.mongoTemplate.count(query, UserBrowse.class));
	}

}
