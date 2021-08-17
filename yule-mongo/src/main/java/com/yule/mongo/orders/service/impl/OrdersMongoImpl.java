package com.yule.mongo.orders.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.yule.enumerate.DateStyle;
import com.yule.exception.YuleException;
import com.yule.mongo.orders.service.IOrdersMongo;
import com.yule.mongo.pojo.Counter;
import com.yule.mongo.pojo.Orders;
import com.yule.util.DateUtil;

@Repository("ordersMongoImpl")
public class OrdersMongoImpl implements IOrdersMongo{

	@Autowired  
    private MongoTemplate mongoTemplate;

	public boolean insertOrders(Orders orders) throws YuleException{
		boolean flag = false;
		try{
			if(null==orders.getCreate_time()){
				orders.setCreate_time(DateUtil.getCurrentDate());
			}
			long num = 10000000;
			String year = DateUtil.DateToString(new Date(), DateStyle.YY);
			Query query = new Query();
			query.addCriteria(new Criteria("collection_name").is("orders"));
			Counter counter= this.mongoTemplate.findOne(query, Counter.class);
			
			if (null!=counter) {
				num=counter.getNum()+1;
			}else{
				Counter insertCounter = new Counter();
				insertCounter.setCollection_name("orders");
				insertCounter.setNum(num);
				this.mongoTemplate.save(insertCounter);
			}
			orders.setOrder_num(year+(num+"").substring(2));
			orders.setCreate_year(DateUtil.DateToString(orders.getCreate_time(), DateStyle.YYYY));
			orders.setCreate_month(DateUtil.DateToString(orders.getCreate_time(), DateStyle.YYYY_MM));
			orders.setCreate_day(DateUtil.DateToString(orders.getCreate_time(), DateStyle.YYYY_MM_DD));
			orders.setTime(DateUtil.DateToString(orders.getCreate_time(), DateStyle.HHMMSS));
			this.mongoTemplate.save(orders);
			 Update update = new Update();
			if(!StringUtils.isEmpty(num)){
				update.set("num", num);  
			}
			this.mongoTemplate.updateFirst(query, update, Counter.class);  
			flag = true;
		}catch(Exception e){
			throw new YuleException(e);
		}
		return flag;
	}
	
}
