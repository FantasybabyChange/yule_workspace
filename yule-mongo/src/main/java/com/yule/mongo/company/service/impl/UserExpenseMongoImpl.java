package com.yule.mongo.company.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Service;

import com.yule.exception.YuleException;
import com.yule.mongo.company.service.IUserExpenseMongo;
import com.yule.mongo.company.vo.UserExpenseSumVO;
import com.yule.mongo.constant.CollectionConst;
import com.yule.mongo.pojo.UserExpense;
import com.yule.mongo.pojo.UserScore;



@Service("userExpenseMongoImpl")
public class UserExpenseMongoImpl implements IUserExpenseMongo{
	
	@Autowired  
    private MongoTemplate mongoTemplate;

	public boolean insertUserExpense(UserExpense userExpense) throws YuleException {
		boolean flag = false;
		try{
			mongoTemplate.save(userExpense);
			flag = true;
		}catch(Exception e){
			throw new YuleException();
		}
		return flag;
	}

	public double findUserExpenseByuserId(String id) throws YuleException {
		Double user_expense_sum = null;
		Aggregation aggregationFirst = Aggregation.newAggregation(UserScore.class,
				Aggregation.project("user_id","money"),
				Aggregation.match(
	               Criteria.where("user_id").is(id)
	            ),
	            Aggregation.group("user_id").sum("money").as("user_expense_sum")
			);
		AggregationResults<UserExpenseSumVO> resultsFirst = mongoTemplate.aggregate(aggregationFirst,CollectionConst.USER_EXPENSE,UserExpenseSumVO.class);
		if (resultsFirst.getUniqueMappedResult()!=null) {
			user_expense_sum = resultsFirst.getUniqueMappedResult().getUser_expense_sum();
		}
		return user_expense_sum;
	}

}
