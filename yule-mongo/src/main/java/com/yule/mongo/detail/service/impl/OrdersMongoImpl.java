package com.yule.mongo.detail.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Repository;
import com.yule.exception.YuleException;
import com.yule.mongo.constant.CollectionConst;
import com.yule.mongo.detail.service.IOrdersMongo;
import com.yule.mongo.pojo.Orders;
import com.yule.mongo.vo.CountVO;

@Repository("ordersMongoImpl")
public class OrdersMongoImpl implements IOrdersMongo{

	@Autowired  
    private MongoTemplate mongoTemplate;  

	public CountVO findOrdersByCompanyAreaCityId(String company_id)throws YuleException {
		Aggregation aggregation = Aggregation.newAggregation(Orders.class,
				Aggregation.project("company_id"),
				Aggregation.match(
	               Criteria.where("company_id").is(company_id)
	            ),
	            Aggregation.group("company_id").count().as("count")
			);
		AggregationResults<CountVO> results = mongoTemplate.aggregate(aggregation,CollectionConst.ORDERS,CountVO.class);		
		return results.getUniqueMappedResult();
	}


}
