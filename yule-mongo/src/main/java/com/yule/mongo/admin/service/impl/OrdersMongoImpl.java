package com.yule.mongo.admin.service.impl;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.yule.enumerate.DateStyle;
import com.yule.exception.YuleException;
import com.yule.mongo.admin.service.IOrdersMongo;
import com.yule.mongo.pojo.Orders;
import com.yule.mongo.query.OrdersQuery;
import com.yule.util.ConvertUtil;
import com.yule.util.DateUtil;
import com.yule.vo.Page;

@Repository("ordersMongoImpl")
public class OrdersMongoImpl implements IOrdersMongo{

	@Autowired  
    private MongoTemplate mongoTemplate;

	public Page<Orders> findOrdersPage(OrdersQuery ordersQuery,int pageSize,int pageNo) throws YuleException{
		Query query = new Query();
		if(null!=ordersQuery){
			if(!StringUtils.isEmpty(ordersQuery.getCompany_id())){
				query.addCriteria(new Criteria("company_id").is(ordersQuery.getCompany_id()));
			}
			if(!StringUtils.isEmpty(ordersQuery.getDesc())){
				query.addCriteria(new Criteria("desc").in(ordersQuery.getDesc()));
			}
			if(!StringUtils.isEmpty(ordersQuery.getIs_delete())){
				query.addCriteria(new Criteria("is_delete").is(ordersQuery.getIs_delete()));
			}
			if(!StringUtils.isEmpty(ordersQuery.getUser_id())){
				query.addCriteria(new Criteria("user_id").is(ordersQuery.getUser_id()));
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
			if(!StringUtils.isEmpty(ordersQuery.getQueryStatus())){
				query.addCriteria(new Criteria("status").is(ordersQuery.getQueryStatus()));
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
	
	public Orders findOrdersById(String id) throws YuleException{
		return this.mongoTemplate.findById(new ObjectId(id), Orders.class);
	}
	
	public boolean updateOrders(Orders orders) throws YuleException{
		boolean flag = false;
		Query query = new Query();  
		query.addCriteria(new Criteria("_id").is(orders.getId()));  
        Update update = new Update();  
		if(!StringUtils.isEmpty(orders.getArrive_time())){
			update.set("arrive_time", orders.getArrive_time());  
		}
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
		if(!StringUtils.isEmpty(orders.getCustomer_name())){
			update.set("customer_name", orders.getCustomer_name());  
		}
		if(!StringUtils.isEmpty(orders.getCustomer_name())){
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
	
}
