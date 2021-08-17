package com.yule.mongo.search.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Repository;

import com.yule.exception.YuleException;
import com.yule.mongo.constant.CollectionConst;
import com.yule.mongo.pojo.Orders;
import com.yule.mongo.search.service.IOrdersMongo;
import com.yule.mongo.search.vo.OrdersHotVO;
import com.yule.mongo.vo.CountVO;

@Repository("ordersMongoImpl")
public class OrdersMongoImpl implements IOrdersMongo{

	@Autowired  
    private MongoTemplate mongoTemplate;  

	
	public OrdersHotVO findOrdersHotVO(String companyId) throws YuleException {
		Aggregation aggregation = Aggregation.newAggregation(Orders.class,
				Aggregation.project("company_id","create_time"),
				Aggregation.match(
	               Criteria.where("company_id").is(companyId)
	            ),
	            Aggregation.sort(Direction.ASC, "create_time"),
	            Aggregation.group("company_id")
	            .count().as("orders_count")
	            .last("create_time").as("create_time")
//	            Aggregation.sort(Direction.ASC, "create_time")
			);
		AggregationResults<OrdersHotVO> results = mongoTemplate.aggregate(aggregation,CollectionConst.ORDERS,OrdersHotVO.class);
		return results.getUniqueMappedResult();
	}


	public CountVO findOrdersByCompanyAreaCityId(String area_city_id)throws YuleException {
		Aggregation aggregation = Aggregation.newAggregation(Orders.class,
				Aggregation.project("company_area_city_id"),
				Aggregation.match(
	               Criteria.where("company_area_city_id").is(area_city_id)
	            ),
	            Aggregation.group("company_area_city_id").count().as("count")
			);
		AggregationResults<CountVO> results = mongoTemplate.aggregate(aggregation,CollectionConst.ORDERS,CountVO.class);		
		return results.getUniqueMappedResult();
	}


}
