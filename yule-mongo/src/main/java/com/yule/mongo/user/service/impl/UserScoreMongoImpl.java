package com.yule.mongo.user.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import com.yule.constant.ScoreConst;
import com.yule.exception.YuleException;
import com.yule.mongo.constant.CollectionConst;
import com.yule.mongo.pojo.UserScore;
import com.yule.mongo.user.query.UserScoreQuery;
import com.yule.mongo.user.service.IUserScoreMongo;
import com.yule.mongo.vo.CountVO;
import com.yule.util.ConvertUtil;
import com.yule.util.DateUtil;
import com.yule.vo.Page;

@Service("userScoreMongoImpl")
public class UserScoreMongoImpl implements IUserScoreMongo{
	
	@Autowired  
    private MongoTemplate mongoTemplate;

	public Page<UserScore> findUserScorePage(UserScoreQuery userScoreQuery,int pageSize, int pageNo) throws YuleException {
		Query query=new Query();
		query.addCriteria(new Criteria("user_id").is(userScoreQuery.getUserId()));
		if(null!=userScoreQuery.getType()){
			query.addCriteria(new Criteria("type").is(userScoreQuery.getType()));
		}
		query.skip((pageNo-1)*pageSize).limit(pageSize);   
		Page<UserScore> page = new Page<UserScore>();
		query.with(new Sort(new Order(Direction.DESC,"create_time")));  
		page.setDatas(this.mongoTemplate.find(query, UserScore.class));
		page.setPageNo(pageNo);
		page.setRowCount(ConvertUtil.longToInteger(this.mongoTemplate.count(query, UserScore.class)));
		return page;
	}

	public int findUserScore(String userId) throws YuleException {
		Aggregation aggregationFirst = Aggregation.newAggregation(UserScore.class,
			Aggregation.project("user_id","score","status"),
			Aggregation.match(
               Criteria.where("user_id").is(userId).and("status").is(ScoreConst.STATUS_IN)
            ),
            Aggregation.group("user_id").sum("score").as("count")
		);
		AggregationResults<CountVO> resultsFirst = mongoTemplate.aggregate(aggregationFirst,CollectionConst.USER_SCORE,CountVO.class);
		
		Aggregation aggregationLast = Aggregation.newAggregation(UserScore.class,
			Aggregation.project("user_id","score","status"),
			Aggregation.match(
               Criteria.where("user_id").is(userId).and("status").is(ScoreConst.STATUS_OUT)
            ),
            Aggregation.group("user_id").sum("score").as("count")
		);
		AggregationResults<CountVO> resultsLast = mongoTemplate.aggregate(aggregationLast,CollectionConst.USER_SCORE,CountVO.class);
		int countFirst = 0;
		if(null!=resultsFirst.getUniqueMappedResult()){
			countFirst = resultsFirst.getUniqueMappedResult().getCount();
		}
		int countLast = 0;
		if(null!=resultsLast.getUniqueMappedResult()){
			countLast = resultsLast.getUniqueMappedResult().getCount();
		}
		return countFirst-countLast;
	}

	public boolean insertUserScore(UserScore userScore) throws YuleException {
		boolean flag = false;
		try {
			if (userScore.getCreate_time() == null) {
				userScore.setCreate_time(DateUtil.getCurrentDate());
			}
			this.mongoTemplate.save(userScore);
			flag = true;
		} catch (Exception e) {
			throw new YuleException();
		}
		return flag;
	}

}
