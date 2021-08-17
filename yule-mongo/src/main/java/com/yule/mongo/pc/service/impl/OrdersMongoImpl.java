package com.yule.mongo.pc.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.yule.enumerate.DateStyle;
import com.yule.exception.YuleException;
import com.yule.mongo.constant.CollectionConst;
import com.yule.mongo.pc.service.IOrdersMongo;
import com.yule.mongo.pc.vo.OrdersHotAreaCityVO;
import com.yule.mongo.pc.vo.OrdersHotVO;
import com.yule.mongo.pojo.Orders;
import com.yule.util.DateUtil;

@Repository("ordersMongoImpl")
public class OrdersMongoImpl implements IOrdersMongo{

	@Autowired  
    private MongoTemplate mongoTemplate;  

	public boolean insertOrders(Orders orders) throws YuleException {
		boolean flag = false;
		try{
			Date date = DateUtil.getCurrentDate();
			if(null==orders.getCreate_time()){
				orders.setCreate_time(date);
			}
			orders.setCreate_year(DateUtil.DateToString(date, DateStyle.YYYY));
			orders.setCreate_month(DateUtil.DateToString(date, DateStyle.YYYY_MM));
			orders.setCreate_day(DateUtil.DateToString(date, DateStyle.YYYY_MM_DD));
			orders.setTime(DateUtil.DateToString(date, DateStyle.HHMMSS));
			mongoTemplate.insert(orders);
			flag = true;
		}catch(Exception e){
			throw new YuleException(e);
		}
		return flag;
	}

	public Orders findOrdersNew(String companyId) throws YuleException {
		Query query = new Query();
		if(!StringUtils.isEmpty(companyId)){
			query.addCriteria(new Criteria("company_id").is(companyId));
		}
		query.with(new Sort(new Order(Direction.DESC,"create_time")));  
		query.skip(0).limit(1); 
		return mongoTemplate.findOne(query, Orders.class);
	}
	
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

	public List<Orders> findOrdersList(String areaProvinceId,Integer pageSize, Integer pageNo)
			throws YuleException {
		Query query = new Query();
		if(!StringUtils.isEmpty(areaProvinceId)){
			query.addCriteria(new Criteria("company_area_province_id").is(areaProvinceId));
		}
		query.with(new Sort(new Order(Direction.DESC,"create_time")));  
		query.skip((pageNo-1)*pageSize).limit(pageSize);
		return mongoTemplate.find(query, Orders.class);
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
