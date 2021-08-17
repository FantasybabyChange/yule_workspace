package com.yule.mongo.orders.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Repository;

import com.yule.exception.YuleException;
import com.yule.mongo.constant.CollectionConst;
import com.yule.mongo.orders.service.IUserScoreMongo;
import com.yule.mongo.orders.vo.UserScoreVO;
import com.yule.mongo.pojo.UserScore;
import com.yule.util.DateUtil;

@Repository("userScoreMongoImpl")
public class UserScoreMongoImpl implements IUserScoreMongo {

	@Autowired  
    private MongoTemplate mongoTemplate;

	public boolean insertUserScore(UserScore userScore) throws YuleException {
		boolean flag = false;
		try{
			if(null==userScore.getCreate_time()){
				userScore.setCreate_time(DateUtil.getCurrentDate());
			}
			this.mongoTemplate.save(userScore);
			flag = true;
		}catch(Exception e){
			throw new YuleException(e);
		}
		return flag;
	}

	public int findUserScore(String id) throws YuleException {
		int user_score =0;
		Aggregation aggregation = Aggregation.newAggregation(UserScore.class,
			Aggregation.project("user_id","status","score"),
			Aggregation.match(
		               Criteria.where("user_id").is(id)
		            ),
            Aggregation.group("status").sum("score").as("user_score_sum").last("status").as("user_score_status"),
            Aggregation.sort(Direction.ASC, "user_score_status")
				);
		AggregationResults<UserScoreVO> results = mongoTemplate.aggregate(aggregation,CollectionConst.USER_SCORE,UserScoreVO.class);
		List<UserScoreVO> userScores = results.getMappedResults();
		if (null!=userScores&&userScores.size()>0) {
			if (userScores.size()==2) {
				user_score = userScores.get(0).getUser_score_sum()- userScores.get(1).getUser_score_sum();
			}else{
				user_score =userScores.get(0).getUser_score_sum();
			}
			
		}
		results=null;
		aggregation = null;
		return user_score;
	}

	
}
