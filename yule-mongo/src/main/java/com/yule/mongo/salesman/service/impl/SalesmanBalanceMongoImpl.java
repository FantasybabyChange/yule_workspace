package com.yule.mongo.salesman.service.impl;

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
import com.yule.mongo.pojo.Orders;
import com.yule.mongo.salesman.query.SalesmanBalanceQuery;
import com.yule.mongo.salesman.service.ISalesmanBalanceMongo;
import com.yule.mongo.salesman.vo.SalesmanBalanceVO;
import com.yule.util.ConvertUtil;
import com.yule.util.DateUtil;
import com.yule.vo.Page;

@Repository("salesmanBalanceMongoImpl")
public class SalesmanBalanceMongoImpl implements ISalesmanBalanceMongo{

	@Autowired  
    private MongoTemplate mongoTemplate;
	
	public SalesmanBalanceVO findSalesmanBalanceVOByQuery(SalesmanBalanceQuery salesmanBalanceQuery) throws YuleException{
		Query query = new Query();
		if (null!=salesmanBalanceQuery) {
			if(!StringUtils.isEmpty(salesmanBalanceQuery.getSalesman_id())){
				query.addCriteria(new Criteria("salesman_id").is(salesmanBalanceQuery.getSalesman_id()));
			}
		}
		query.with(new Sort(new Order(Direction.DESC,"create_time")));
		query.skip(0);
		query.limit(1); 
		return this.mongoTemplate.findOne(query, SalesmanBalanceVO.class);
	}

	public Page<SalesmanBalanceVO> findSalesmanBalancePageByQuery(SalesmanBalanceQuery salesmanBalanceQuery,int pageSize, int pageNo)throws YuleException{
		Query query = new Query();
		if (null!=salesmanBalanceQuery) {
			if(!StringUtils.isEmpty(salesmanBalanceQuery.getSalesman_id())){
				query.addCriteria(new Criteria("salesman_id").is(salesmanBalanceQuery.getSalesman_id()));
			}
		}
		Criteria createTime = null;
		if(!StringUtils.isEmpty(salesmanBalanceQuery.getStart_time())){
			if(createTime==null){
				createTime = new Criteria("create_time");
			}
			createTime.gte(DateUtil.StringToDate(salesmanBalanceQuery.getStart_time(),DateStyle.YYYY_MM_DD_HH_MM_SS_EN));
		}
		if(!StringUtils.isEmpty(salesmanBalanceQuery.getEnd_time())){
			if(createTime==null){
				createTime = new Criteria("create_time");
			}
			createTime.lte(DateUtil.StringToDate(salesmanBalanceQuery.getEnd_time(),DateStyle.YYYY_MM_DD_HH_MM_SS_EN));
		}
		if(null!=createTime){
			query.addCriteria(createTime);
		}
		query.with(new Sort(new Order(Direction.DESC,"create_time")));  
		query.skip((pageNo-1)*pageSize).limit(pageSize); 
		Page<SalesmanBalanceVO> page = new Page<SalesmanBalanceVO>();
		page.setDatas(this.mongoTemplate.find(query, SalesmanBalanceVO.class));
		page.setPageNo(pageNo);
		page.setRowCount(ConvertUtil.longToInteger(this.mongoTemplate.count(query, SalesmanBalanceVO.class)));
		return page;
	}

	public SalesmanBalanceVO findNowMonthSalesmanBalanceVOByQuery(SalesmanBalanceQuery salesmanBalanceQuery) throws YuleException {
		Aggregation aggregation = Aggregation.newAggregation(Orders.class,
				Aggregation.project("company_id","status","is_delete","orders_count","user_score","expense_money","create_time"),
				Aggregation.match(
	               Criteria.where("company_id").in(salesmanBalanceQuery.getCompany_ids()).and("status").is(OrdersConst.ORDERS_STATUS_THREE).and("is_delete").is(DeleteConst.IS_DELETE_TRUE)
	               .and("create_time").gte(DateUtil.StringToDate(salesmanBalanceQuery.getStart_time())).lte(DateUtil.StringToDate(salesmanBalanceQuery.getEnd_time(),DateStyle.YYYY_MM_DD_HH_MM_SS_EN))
	            ),
	            Aggregation.group("is_delete").count().as("orders_count").sum("expense_money").as("orders_expense_sum")
			);
		AggregationResults<SalesmanBalanceVO> results = mongoTemplate.aggregate(aggregation,CollectionConst.ORDERS,SalesmanBalanceVO.class);
		return results.getUniqueMappedResult();
	}

}
