package com.yule.mongo.user.service.impl;

import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.yule.constant.DeleteConst;
import com.yule.exception.YuleException;
import com.yule.mongo.constant.OrdersMongoConst;
import com.yule.mongo.pojo.Orders;
import com.yule.mongo.user.query.OrdersQuery;
import com.yule.mongo.user.service.IOrdersMongo;
import com.yule.util.ConvertUtil;
import com.yule.vo.Page;

@Service("ordersMongoImpl")
public class OrdersMongoImpl implements IOrdersMongo{
	
	@Autowired  
    private MongoTemplate mongoTemplate;

	public Page<Orders> findOrdersPage(OrdersQuery ordersQuery,int pageSize, int pageNo) throws YuleException {
		Query query=new Query();
		if(null!=ordersQuery.getDateType()){
			int dateType = ordersQuery.getDateType();
			if(OrdersMongoConst.DATE_TYPE_ONE==dateType){
				Criteria createTime = new Criteria("create_time");
				Calendar c = Calendar.getInstance();
				c.setTimeInMillis(System.currentTimeMillis());
				createTime.lte(c.getTime());
				c.add(Calendar.DAY_OF_YEAR,-1);
				createTime.gte(c.getTime());
				query.addCriteria(createTime);
			}else if(OrdersMongoConst.DATE_TYPE_TWO==dateType){
				Criteria createTime = new Criteria("create_time");
				Calendar c = Calendar.getInstance();
				c.setTimeInMillis(System.currentTimeMillis());
				createTime.lte(c.getTime());
				c.add(Calendar.DAY_OF_YEAR,-3);
				createTime.gte(c.getTime());
				query.addCriteria(createTime);
			}else if(OrdersMongoConst.DATE_TYPE_THREE==dateType){
				Criteria createTime = new Criteria("create_time");
				Calendar c = Calendar.getInstance();
				c.setTimeInMillis(System.currentTimeMillis());
				createTime.lte(c.getTime());
				c.add(Calendar.MONTH,-1);
				createTime.gte(c.getTime());
				query.addCriteria(createTime);
			}else if(OrdersMongoConst.DATE_TYPE_FOUR==dateType){
				Criteria createTime = new Criteria("create_time");
				Calendar c = Calendar.getInstance();
				c.setTimeInMillis(System.currentTimeMillis());
				createTime.lte(c.getTime());
				c.add(Calendar.MONTH,-3);
				createTime.gte(c.getTime());
				query.addCriteria(createTime);
			}
		}
		if(null!=ordersQuery.getStatus()){
			query.addCriteria(new Criteria("status").is(ordersQuery.getStatus()));
		}
		if(!StringUtils.isEmpty(ordersQuery.getName())){
			query.addCriteria(new Criteria("order_num").is(ordersQuery.getName()).orOperator(new Criteria("company_name").is(ordersQuery.getName())));
		}
		query.addCriteria(new Criteria("user_id").is(ordersQuery.getUserId()));
		query.addCriteria(new Criteria("is_delete").is(DeleteConst.IS_DELETE_TRUE));
		query.skip((pageNo-1)*pageSize).limit(pageSize);   
		Page<Orders> page = new Page<Orders>();
		query.with(new Sort(new Order(Direction.DESC,"create_time")));  
		page.setDatas(this.mongoTemplate.find(query, Orders.class));
		page.setPageNo(pageNo);
		page.setRowCount(ConvertUtil.longToInteger(this.mongoTemplate.count(query, Orders.class)));
		return page;
	}

	public int findOrders(String userId) throws YuleException {
		Query query=new Query();
		query.addCriteria(new Criteria("user_id").is(userId));
		return ConvertUtil.longToInteger(this.mongoTemplate.count(query, Orders.class));
	}

	public Orders findOrdersByOrderNum(String orderNum) throws YuleException {
		Query query=new Query();
		query.addCriteria(new Criteria("order_num").is(orderNum));
		return this.mongoTemplate.findOne(query, Orders.class);
	}

	public boolean updateOrdersByOrderNum(Orders orders) throws YuleException {
		boolean flag = false;
		Query query = new Query();  
		query.addCriteria(new Criteria("order_num").is(orders.getOrder_num()));  
        Update update = new Update();  
		if(!StringUtils.isEmpty(orders.getIs_comment())){
			update.set("is_comment", orders.getIs_comment());
		}
		if(!StringUtils.isEmpty(orders.getStatus())){
			update.set("status", orders.getStatus());
		}
		if(!StringUtils.isEmpty(orders.getUpdate_time())){
			update.set("update_time", orders.getUpdate_time());
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
