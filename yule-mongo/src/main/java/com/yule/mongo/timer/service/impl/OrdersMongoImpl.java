package com.yule.mongo.timer.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.yule.constant.DeleteConst;
import com.yule.constant.OrdersConst;
import com.yule.enumerate.DateStyle;
import com.yule.exception.YuleException;
import com.yule.mongo.constant.CollectionConst;
import com.yule.mongo.pojo.Orders;
import com.yule.mongo.timer.service.IOrdersMongo;
import com.yule.mongo.timer.vo.CompanyOrdersBalanceVO;
import com.yule.util.DateUtil;

@Repository("ordersMongoImpl")
public class OrdersMongoImpl implements IOrdersMongo{

	@Autowired  
    private MongoTemplate mongoTemplate;  
	
	public long findOrdersCount(String companyId) throws YuleException {
		Query query = new Query();
		query.addCriteria(new Criteria("company_id").is(companyId));
		mongoTemplate.count(query, Orders.class);
		return this.mongoTemplate.count(query,Orders.class);
	}

	public CompanyOrdersBalanceVO findLastMonthOrdersByCompanyId(String companyId,String month) throws YuleException {
		Aggregation aggregation = Aggregation.newAggregation(Orders.class,
				Aggregation.project("create_month","company_id","status","is_delete","orders_count","user_score","expense_money"),
				Aggregation.match(
	               Criteria.where("status").is(OrdersConst.ORDERS_STATUS_THREE).and("is_delete").is(DeleteConst.IS_DELETE_TRUE)
	               .and("create_month").is(month)
	            ),
	            Aggregation.group("company_id").count().as("orders_count").sum("expense_money").as("orders_expense_sum")
	            .sum("user_score").as("orders_score_sum").last("company_id").as("company_id"),
	            Aggregation.match(
	               Criteria.where("company_id").is(companyId)
	            )
			);
		AggregationResults<CompanyOrdersBalanceVO> results = mongoTemplate.aggregate(aggregation,CollectionConst.ORDERS,CompanyOrdersBalanceVO.class);
		return results.getUniqueMappedResult();
	}
	
	public CompanyOrdersBalanceVO findOrdersByCompanyId(String companyId,String startTime,String endTime) throws YuleException {
		Aggregation aggregation = Aggregation.newAggregation(Orders.class,
			Aggregation.project("company_id","status","is_delete","orders_count","user_score","expense_money","create_time"),
			Aggregation.match(
               Criteria.where("status").is(OrdersConst.ORDERS_STATUS_THREE).and("is_delete").is(DeleteConst.IS_DELETE_TRUE)
               .and("create_time").gte(DateUtil.StringToDate(startTime,DateStyle.YYYY_MM_DD_HH_MM_SS_EN)).lte(DateUtil.StringToDate(endTime,DateStyle.YYYY_MM_DD_HH_MM_SS_EN))
            ),
            Aggregation.group("company_id").count().as("orders_count").sum("expense_money").as("orders_expense_sum")
            .sum("user_score").as("orders_score_sum").last("company_id").as("company_id"),
            Aggregation.match(
               Criteria.where("company_id").is(companyId)
            )
		);
		AggregationResults<CompanyOrdersBalanceVO> results = mongoTemplate.aggregate(aggregation,CollectionConst.ORDERS,CompanyOrdersBalanceVO.class);
		return results.getUniqueMappedResult();
	}

}
