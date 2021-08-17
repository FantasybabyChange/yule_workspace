package com.yule.mongo.company.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.bson.types.ObjectId;
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
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.yule.constant.DeleteConst;
import com.yule.constant.OrdersConst;
import com.yule.enumerate.DateStyle;
import com.yule.exception.YuleException;
import com.yule.mongo.company.query.BalanceQuery;
import com.yule.mongo.company.service.IOrdersMongo;
import com.yule.mongo.company.vo.BalanceVo;
import com.yule.mongo.company.vo.SaleVo;
import com.yule.mongo.constant.CollectionConst;
import com.yule.mongo.constant.DataStatisticsConst;
import com.yule.mongo.pojo.Orders;
import com.yule.mongo.query.OrdersQuery;
import com.yule.mongo.vo.CountVO;
import com.yule.util.ConvertUtil;
import com.yule.util.DateUtil;
import com.yule.vo.Page;

@Repository("ordersMongoImpl")
public class OrdersMongoImpl implements IOrdersMongo{

	@Autowired  
    private MongoTemplate mongoTemplate;

	public Page<Orders> findOrdersPage(OrdersQuery ordersQuery,int pageSize,int pageNo) throws YuleException{
		Query query = new Query();
		if(!StringUtils.isEmpty(ordersQuery.getCompany_id())){
			query.addCriteria(new Criteria("company_id").is(ordersQuery.getCompany_id()));
		}
		if(!StringUtils.isEmpty(ordersQuery.getDesc())){
			query.addCriteria(new Criteria("desc").in(ordersQuery.getDesc()));
		}
		if(!StringUtils.isEmpty(ordersQuery.getIs_delete())){
			query.addCriteria(new Criteria("is_delete").is(ordersQuery.getIs_delete()));
		}
		if(!StringUtils.isEmpty(ordersQuery.getOrder_num())){
			query.addCriteria(new Criteria("order_num").is(ordersQuery.getOrder_num()));
		}
		if(!StringUtils.isEmpty(ordersQuery.getPerson_name())){
			query.addCriteria(new Criteria("customer_name").regex(ordersQuery.getPerson_name()));
		}
		if(!StringUtils.isEmpty(ordersQuery.getPhone())){
			query.addCriteria(new Criteria("customer_phone").regex(ordersQuery.getPhone()));
		}
		if(!StringUtils.isEmpty(ordersQuery.getProduct_id())){
			query.addCriteria(new Criteria("product_id").is(ordersQuery.getProduct_id()));
		}
		if(!StringUtils.isEmpty(ordersQuery.getProduct_name())){
			query.addCriteria(new Criteria("product_name").regex(ordersQuery.getProduct_name()));
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
		List<Integer> status = ordersQuery.getStatus();
		if(null!=status&&status.size()>0){
			query.addCriteria(new Criteria("status").in(status));
		}
		query.with(new Sort(new Order(Direction.DESC,"create_time")));  
		query.skip((pageNo-1)*pageSize).limit(pageSize); 
		Page<Orders> page = new Page<Orders>();
		page.setDatas(this.mongoTemplate.find(query, Orders.class));
		page.setPageNo(pageNo);
		page.setRowCount(ConvertUtil.longToInteger(this.mongoTemplate.count(query, Orders.class)));
		if(null!=status&&status.size()>0){
			status.clear();
			status = null;
		}
		return page;
	}
	
	public Orders findOrdersById(String id) throws YuleException{
		return this.mongoTemplate.findById(new ObjectId(id), Orders.class);
	}
	public Orders findOrdersByNum(String order_num) throws YuleException {
		Query query = new Query();
			query.addCriteria(new Criteria("order_num").is(order_num));
		return this.mongoTemplate.findOne(query, Orders.class);
	}
	
	public boolean updateOrders(Orders orders) throws YuleException{
		boolean flag = false;
		Query query = new Query();  
		query.addCriteria(new Criteria("_id").is(orders.getId()));  
        Update update = new Update();  
		if(!StringUtils.isEmpty(orders.getArrive_time())){
			update.set("arrive_time", orders.getArrive_time());  
		}
		if(!StringUtils.isEmpty(orders.getCompany_id())){
			update.set("company_id", orders.getCompany_id());  
		}
		if(!StringUtils.isEmpty(orders.getDesc())){
			update.set("desc", orders.getDesc());  
		}
		if(!StringUtils.isEmpty(orders.getExpense_money())){
			update.set("expense_money", orders.getExpense_money());  
		}
		if(!StringUtils.isEmpty(orders.getEstimate_arrive_time())){
			update.set("estimate_arrive_time", orders.getEstimate_arrive_time());  
		}
		if(!StringUtils.isEmpty(orders.getCustomer_name())){
			update.set("customer_name", orders.getCustomer_name());  
		}
		if(!StringUtils.isEmpty(orders.getCustomer_phone())){
			update.set("customer_phone", orders.getCustomer_phone());  
		}
		if(!StringUtils.isEmpty(orders.getProduct_id())){
			update.set("product_id", orders.getProduct_id());  
		}
		if(!StringUtils.isEmpty(orders.getStatus())){
			update.set("status", orders.getStatus());  
		}
		if(StringUtils.isEmpty(orders.getUpdate_time())){
			update.set("update_time", DateUtil.getCurrentDate());  
		}
		try{
			this.mongoTemplate.updateFirst(query, update, Orders.class);  
			flag = true;
		}catch(Exception e){
			throw new YuleException(e);
		}
		return flag;
	}
	
	public boolean updateOrdersByNum(Orders orders) throws YuleException{
		boolean flag = false;
		Query query = new Query();  
		query.addCriteria(new Criteria("order_num").is(orders.getOrder_num()));  
        Update update = new Update();  
		if(!StringUtils.isEmpty(orders.getArrive_time())){
			update.set("arrive_time", orders.getArrive_time());  
		}
		if(!StringUtils.isEmpty(orders.getCompany_id())){
			update.set("company_id", orders.getCompany_id());  
		}
		if(!StringUtils.isEmpty(orders.getDesc())){
			update.set("desc", orders.getDesc());  
		}
		if(!StringUtils.isEmpty(orders.getEstimate_arrive_time())){
			update.set("estimate_arrive_time", orders.getEstimate_arrive_time());  
		}
		if(!StringUtils.isEmpty(orders.getExpense_money())){
			update.set("expense_money", orders.getExpense_money());  
		}
		if(!StringUtils.isEmpty(orders.getCustomer_name())){
			update.set("customer_name", orders.getCustomer_name());  
		}
		if(!StringUtils.isEmpty(orders.getCustomer_phone())){
			update.set("customer_phone", orders.getCustomer_phone());  
		}
		if(!StringUtils.isEmpty(orders.getProduct_id())){
			update.set("product_id", orders.getProduct_id());  
		}
		if(!StringUtils.isEmpty(orders.getStatus())){
			update.set("status", orders.getStatus());  
		}
		if(!StringUtils.isEmpty(orders.getIs_comment())){
			update.set("is_comment", orders.getIs_comment());  
		}
		if(StringUtils.isEmpty(orders.getUpdate_time())){
			update.set("update_time", DateUtil.getCurrentDate());  
		}
		try{
			this.mongoTemplate.updateFirst(query, update, Orders.class);  
			flag = true;
		}catch(Exception e){
			throw new YuleException(e);
		}
		return flag;
	}

	public Page<Orders> findOrdersPageByCompanyId(String companyId,int status,int pageSize, int pageNo) throws YuleException {
		Query query = new Query();
		if(!StringUtils.isEmpty(companyId)){
			query.addCriteria(new Criteria("company_id").is(companyId));
		}
		query.addCriteria(new Criteria("is_delete").is(DeleteConst.IS_DELETE_TRUE));
		query.addCriteria(new Criteria("status").is(status));
		query.with(new Sort(new Order(Direction.DESC,"create_time")));  
		query.skip((pageNo-1)*pageSize).limit(pageSize); 
		Page<Orders> page = new Page<Orders>();
		page.setDatas(this.mongoTemplate.find(query, Orders.class));
		page.setPageNo(pageNo);
		page.setRowCount(ConvertUtil.longToInteger(this.mongoTemplate.count(query, Orders.class)));
		return page;
	}

	public int findOrdersCount(String companyId,int status) throws YuleException {
		Query query = new Query();
			query.addCriteria(new Criteria("company_id").is(companyId));
			query.addCriteria(new Criteria("is_delete").is(DeleteConst.IS_DELETE_TRUE));
			query.addCriteria(new Criteria("status").is(status));
		return ConvertUtil.longToInteger(this.mongoTemplate.count(query, Orders.class));
	}
	
	public List<SaleVo>  findOrdersToStatistics(OrdersQuery ordersQuery) throws YuleException{
		String timeType = DataStatisticsConst.SALE_TIME_TYPE[ordersQuery.getTime_type()];
		
		Aggregation aggregation = Aggregation.newAggregation(Orders.class,
				Aggregation.project(timeType,"company_id","status","is_delete","product_name","create_time","expense_count","expense_money"),
				Aggregation.match(
	               Criteria.where("company_id").is(ordersQuery.getCompany_id()).
	               and("status").is(OrdersConst.ORDERS_STATUS_THREE).and("is_delete").is(DeleteConst.IS_DELETE_TRUE)
	               .and("create_time").gte(DateUtil.StringToDate(ordersQuery.getStart_time(),DateStyle.YYYY_MM_DD_HH_MM_SS)).lte(DateUtil.StringToDate(ordersQuery.getEnd_time(),DateStyle.YYYY_MM_DD_HH_MM_SS))
	            ),
	            Aggregation.group("product_name",timeType)
	            .count().as("product_order_count").sum("expense_money").as("expense_count")
	            .last("product_name").as("product_name")
	            .last(timeType).as("day")
			);
		AggregationResults<SaleVo> results = mongoTemplate.aggregate(aggregation,CollectionConst.ORDERS,SaleVo.class);
		List<SaleVo> saleVos = results.getMappedResults();
		return saleVos;
	}
	
	public BalanceVo findLastMonthBalance(BalanceQuery balanceQuery) throws YuleException {
		Aggregation aggregation = Aggregation.newAggregation(Orders.class,
			Aggregation.project("create_month","company_id","status","is_delete","orders_count","expense_money","orders_expense_count"),
			Aggregation.match(
               Criteria.where("company_id").is(balanceQuery.getCompany_id()).and("status").is(OrdersConst.ORDERS_STATUS_THREE).and("is_delete").is(DeleteConst.IS_DELETE_TRUE)
               .and("create_month").is(balanceQuery.getMonth())	
            ),
            Aggregation.group("create_month").count().as("orders_count").sum("expense_money").as("orders_expense_count")
            .last("create_month").as("month")
		);
		AggregationResults<BalanceVo> results = mongoTemplate.aggregate(aggregation,CollectionConst.ORDERS,BalanceVo.class);
		BalanceVo balanceVo = null;
		if (null!=results) {
			balanceVo= results.getUniqueMappedResult();
		}
		return balanceVo;
	}
	
	public Page<BalanceVo> findHistoryBalance(BalanceQuery balanceQuery,int pageSize, int pageNo) throws YuleException {
		Page<BalanceVo> page =new Page<BalanceVo>();
		MatchOperation matchOperation =new MatchOperation(Criteria.where("company_id").is(balanceQuery.getCompany_id()).and("status").is(OrdersConst.ORDERS_STATUS_THREE).and("is_delete").is(DeleteConst.IS_DELETE_TRUE)
			.and("create_time").gte(DateUtil.StringToDate(balanceQuery.getStart_time(),DateStyle.YYYY_MM_DD_HH_MM_SS)).lte(DateUtil.StringToDate(balanceQuery.getEnd_time(),DateStyle.YYYY_MM_DD_HH_MM_SS)) );
			Aggregation aggregation = Aggregation.newAggregation(Orders.class,
				matchOperation,
			Aggregation.project("create_time","create_month","company_id","status","is_delete","expense_money"),
            Aggregation.group("create_month").count().as("orders_count").sum("expense_money").as("orders_expense_count")
            .last("create_month").as("month"),
            Aggregation.skip((pageNo-1)*pageSize),
            Aggregation.limit(pageSize)
		);
		AggregationResults<BalanceVo> results = mongoTemplate.aggregate(aggregation,CollectionConst.ORDERS,BalanceVo.class);
		Aggregation count = Aggregation.newAggregation(
			Aggregation.project("create_time","create_month","company_id","status","is_delete"),
			matchOperation,
            Aggregation.group("create_month").count().as("count")
		);
		AggregationResults<CountVO> counts = mongoTemplate.aggregate(count,CollectionConst.ORDERS,CountVO.class);
		if (null!=results.getMappedResults()) {
			page.setDatas(new ArrayList<BalanceVo>(results.getMappedResults()));
			page.setPageNo(pageNo);
			page.setRowCount(counts.getMappedResults().size());
			results = null;
			counts =null;
		}
		matchOperation = null;
		return page;
	}
	
}
