package com.yule.mongo.mobile.service.impl;

import java.util.ArrayList;
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
import com.yule.mongo.mobile.service.IOrdersMongo;
import com.yule.mongo.mobile.vo.CompanyHotVO;
import com.yule.mongo.mobile.vo.OrdersHotAreaCityVO;
import com.yule.mongo.mobile.vo.OrdersHotVO;
import com.yule.mongo.pojo.Orders;
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
	
	public List<CompanyHotVO> findOrdersHotAreaCityVOList(Integer pageSize,Integer pageNo,String cityId) throws YuleException {
		Aggregation aggregation = Aggregation.newAggregation(Orders.class,
				Aggregation.project("company_area_city_id","company_area_city_name","company_id","company_name"),
				Aggregation.match(
			               Criteria.where("company_area_city_id").is(cityId)
			            ),
	            Aggregation.group("company_id").count().as("orders_count")
	            .last("company_id").as("company_id")
	            .last("company_name").as("company_name"),
	            Aggregation.sort(Direction.ASC, "orders_count"),
	            Aggregation.skip((pageNo-1)*pageSize),
	            Aggregation.limit(pageSize)
			);
		AggregationResults<CompanyHotVO> results = mongoTemplate.aggregate(aggregation,CollectionConst.ORDERS,CompanyHotVO.class);
		return new ArrayList<CompanyHotVO>(results.getMappedResults());
	}
	
	public List<OrdersHotAreaCityVO> findOrdersHotAreaCityVOList(Integer pageSize,Integer pageNo) throws YuleException {
		Aggregation aggregation = Aggregation.newAggregation(Orders.class,
				Aggregation.project("company_area_city_id","company_area_city_name"),
				Aggregation.match(
			               Criteria.where("company_area_city_id").ne("")
			            ),
	            Aggregation.group("company_area_city_id").count().as("orders_count")
	            .last("company_area_city_id").as("area_city_id")
	            .last("company_area_city_name").as("area_city_name"),
	            Aggregation.sort(Direction.ASC, "orders_count"),
	            Aggregation.skip((pageNo-1)*pageSize),
	            Aggregation.limit(pageSize)
			);
		AggregationResults<OrdersHotAreaCityVO> results = mongoTemplate.aggregate(aggregation,CollectionConst.ORDERS,OrdersHotAreaCityVO.class);
		return new ArrayList<OrdersHotAreaCityVO>(results.getMappedResults());
	}
}
