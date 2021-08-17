package com.yule.mongo.salesman.service.impl;

import java.util.ArrayList;
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

import com.yule.constant.DeleteConst;
import com.yule.constant.OrdersConst;
import com.yule.enumerate.DateStyle;
import com.yule.exception.YuleException;
import com.yule.mongo.constant.CollectionConst;
import com.yule.mongo.constant.DataStatisticsConst;
import com.yule.mongo.pojo.Orders;
import com.yule.mongo.query.OrdersQuery;
import com.yule.mongo.salesman.service.IOrdersMongo;
import com.yule.mongo.salesman.vo.SaleVo;
import com.yule.util.ConvertUtil;
import com.yule.util.DateUtil;
import com.yule.vo.Page;

@Repository("ordersMongoImpl")
public class OrdersMongoImpl implements  IOrdersMongo{
	
	
	@Autowired  
    private MongoTemplate mongoTemplate;
	
	public List<SaleVo>  findOrdersToStatistics(OrdersQuery ordersQuery) throws YuleException{
		String timeType = DataStatisticsConst.SALE_TIME_TYPE[ordersQuery.getTime_type()];
		Aggregation aggregation = Aggregation.newAggregation(Orders.class,
				Aggregation.project(timeType,"company_id","status","is_delete","product_name","create_time","expense_count","expense_money"),
				Aggregation.match(
	               Criteria.where("company_id").is(ordersQuery.getCompany_id()).and("status").is(OrdersConst.ORDERS_STATUS_THREE).and("is_delete").is(DeleteConst.IS_DELETE_TRUE)
	               .and("create_time").gte(DateUtil.StringToDate(ordersQuery.getStart_time(),DateStyle.YYYY_MM_DD_HH_MM_SS)).lte(DateUtil.StringToDate(ordersQuery.getEnd_time(),DateStyle.YYYY_MM_DD_HH_MM_SS))
	            ),
	            Aggregation.group("product_name",timeType)
	            .count().as("product_order_count").sum("expense_money").as("expense_count")
	            .last("product_name").as("product_name")
	            .last(timeType).as("day")
			);
		AggregationResults<SaleVo> results = mongoTemplate.aggregate(aggregation,CollectionConst.ORDERS,SaleVo.class);
		return new ArrayList<SaleVo>(results.getMappedResults());
	}

	public Page<Orders> findOrdersByCompany(OrdersQuery ordersQuery,
			int pageSize, int pageNo) throws YuleException {
		Query query = new Query();
		if(null!=ordersQuery){
			if(!StringUtils.isEmpty(ordersQuery.getCompany_id())){
				query.addCriteria(new Criteria("company_id").is(ordersQuery.getCompany_id()));
			}
			if(!StringUtils.isEmpty(ordersQuery.getIs_delete())){
				query.addCriteria(new Criteria("is_delete").is(DeleteConst.IS_DELETE_TRUE));
			}
			if(!StringUtils.isEmpty(ordersQuery.getOrder_num())){
				query.addCriteria(new Criteria("order_num").is(ordersQuery.getOrder_num()));
			}
			if(!StringUtils.isEmpty(ordersQuery.getCompanyIds())){
				query.addCriteria(new Criteria("company_id").in(ordersQuery.getCompanyIds()));
			}
			if(!StringUtils.isEmpty(ordersQuery.getCompany_name())){
				query.addCriteria(new Criteria("company_name").regex(ordersQuery.getCompany_name()));
			}
			if(!StringUtils.isEmpty(ordersQuery.getProduct_name())){
				query.addCriteria(new Criteria("product_name").regex(ordersQuery.getProduct_name()));
			}
			if(ordersQuery.getStatus() != null && ordersQuery.getStatus().size() > 0){
				query.addCriteria(new Criteria("status").in(ordersQuery.getStatus()));
			}
			Criteria createTime = null;
			if(!StringUtils.isEmpty(ordersQuery.getStart_time())){
				if(createTime==null){
					createTime = new Criteria("create_time");
				}
				createTime.gte(DateUtil.StringToDate(ordersQuery.getStart_time(),DateStyle.YYYY_MM_DD_HH_MM_SS_EN));
			}
			if(!StringUtils.isEmpty(ordersQuery.getEnd_time())){
				if(createTime==null){
					createTime = new Criteria("create_time");
				}
				createTime.lte(DateUtil.StringToDate(ordersQuery.getEnd_time(),DateStyle.YYYY_MM_DD_HH_MM_SS_EN));
			}
			if(null!=createTime){
				query.addCriteria(createTime);
			}
		}
		query.with(new Sort(new Order(Direction.DESC,"create_time")));  
		query.skip((pageNo-1)*pageSize).limit(pageSize); 
		Page<Orders> page = new Page<Orders>();
		page.setDatas(this.mongoTemplate.find(query, Orders.class));
		page.setPageNo(pageNo);
		page.setRowCount(ConvertUtil.longToInteger(this.mongoTemplate.count(query, Orders.class)));
		return page;
	}
}
