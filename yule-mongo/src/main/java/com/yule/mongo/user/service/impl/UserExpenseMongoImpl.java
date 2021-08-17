package com.yule.mongo.user.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.aggregation.MatchOperation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.yule.enumerate.DateStyle;
import com.yule.exception.YuleException;
import com.yule.mongo.constant.CollectionConst;
import com.yule.mongo.pojo.UserExpense;
import com.yule.mongo.user.query.UserExpenseQuery;
import com.yule.mongo.user.service.IUserExpenseMongo;
import com.yule.mongo.user.vo.MoneyVO;
import com.yule.mongo.user.vo.UserExpenseVO;
import com.yule.mongo.vo.CountVO;
import com.yule.util.ConvertUtil;
import com.yule.util.DateUtil;
import com.yule.util.RatioUtil;
import com.yule.vo.Page;

@Service("userExpenseMongoImpl")
public class UserExpenseMongoImpl implements IUserExpenseMongo{
	
	@Autowired  
    private MongoTemplate mongoTemplate;

	public Page<UserExpense> findUserExpensePage(UserExpenseQuery userExpenseQuery,int pageSize, int pageNo) throws YuleException {
		Query query=new Query();
		query.addCriteria(new Criteria("user_id").is(userExpenseQuery.getUserId()));
		query.skip((pageNo-1)*pageSize).limit(pageSize);   
		Page<UserExpense> page = new Page<UserExpense>();
		query.with(new Sort(new Order(Direction.DESC,"create_time")));  
		page.setDatas(this.mongoTemplate.find(query, UserExpense.class));
		page.setPageNo(pageNo);
		page.setRowCount(ConvertUtil.longToInteger(this.mongoTemplate.count(query, UserExpense.class)));
		return page;
	}

	public UserExpenseVO findUserExpenseVO(UserExpenseQuery userExpenseQuery) throws YuleException {
		String userId = userExpenseQuery.getUserId();
		Criteria c = new Criteria("create_time");
		if(!StringUtils.isEmpty(userExpenseQuery.getStartTime())){
			c.gte(DateUtil.StringToDate(userExpenseQuery.getStartTime(),DateStyle.YYYY_MM_DD_HH_MM_SS_EN));
		}
		if(!StringUtils.isEmpty(userExpenseQuery.getEndTime())){
			c.lte(DateUtil.StringToDate(userExpenseQuery.getEndTime(),DateStyle.YYYY_MM_DD_HH_MM_SS_EN));
		}
		MatchOperation match  = new MatchOperation(c);
		Aggregation aggregation = Aggregation.newAggregation(UserExpense.class,
			Aggregation.project("user_id","money","create_time"),
			match,
			Aggregation.group("user_id").count().as("count")
			.sum("money").as("money")
			.last("user_id").as("user_id"),
            Aggregation.match(
            	new Criteria("user_id").is(userId)
            )
		);
		AggregationResults<MoneyVO> resultSums = mongoTemplate.aggregate(aggregation,CollectionConst.USER_EXPENSE,MoneyVO.class);
		double money = 0;
		if(null!=resultSums.getUniqueMappedResult()){
			money = resultSums.getUniqueMappedResult().getMoney();
		}
		aggregation = Aggregation.newAggregation(UserExpense.class,
			Aggregation.project("user_id","money","create_time"),
			Aggregation.match(
            	new Criteria("user_id").ne(userId)
            ),
            match,
			Aggregation.group("user_id").count().as("count")
			.sum("money").as("money")
			.last("user_id").as("user_id"),
            Aggregation.match(
            	new Criteria("money").lte(money)
            )
		);
		AggregationResults<CountVO> resultLtCounts = mongoTemplate.aggregate(aggregation,CollectionConst.USER_EXPENSE,CountVO.class);
		int ltCount = 0;
		if(null!=resultLtCounts.getMappedResults()){
			ltCount = resultLtCounts.getMappedResults().size();
		}
		aggregation = Aggregation.newAggregation(UserExpense.class,
			Aggregation.project("user_id","money","create_time"),
			Aggregation.match(
            	new Criteria("user_id").ne(userId)
            ),
			match,
			Aggregation.group("user_id").count().as("count")
			.last("user_id").as("user_id")
		);
		AggregationResults<CountVO> resultCounts = mongoTemplate.aggregate(aggregation,CollectionConst.USER_EXPENSE,CountVO.class);
		int count = 0;
		if(null!=resultLtCounts.getMappedResults()){
			count = resultCounts.getMappedResults().size();
		}
		UserExpenseVO userExpenseVO = new UserExpenseVO();
		userExpenseVO.setMoney(money);
		if(ltCount==0&&count==0){
			ltCount = 1;
		}
		userExpenseVO.setRatio(RatioUtil.getUpperRatio(ltCount, count));
		return userExpenseVO;
	}
	
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

}
