package com.yule.mongo.admin.service.impl;

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
import com.yule.mongo.admin.query.SalesmanCooperatorQuery;
import com.yule.mongo.admin.service.ISalesmanCooperatorMongo;
import com.yule.mongo.pojo.SalesmanCooperator;
import com.yule.util.ConvertUtil;
import com.yule.util.DateUtil;
import com.yule.vo.Page;

@Repository("salesmanCooperatorMongoImpl")
public class SalesmanCooperatorMongoImpl implements ISalesmanCooperatorMongo{
	@Autowired  
    private MongoTemplate mongoTemplate;

	public Page<SalesmanCooperator> findSalesmanCooperatorPage(SalesmanCooperatorQuery salesmanCooperatorQuery, int pageSize,
			int pageNo) throws YuleException {

		Query query = new Query();
		if(!StringUtils.isEmpty(salesmanCooperatorQuery.getAccount())){
			query.addCriteria(new Criteria("account").regex(salesmanCooperatorQuery.getAccount()));
		}
		if(!StringUtils.isEmpty(salesmanCooperatorQuery.getPhone())){
			query.addCriteria(new Criteria("phone").regex(salesmanCooperatorQuery.getPhone()));
		}
		if(!StringUtils.isEmpty(salesmanCooperatorQuery.getMail())){
			query.addCriteria(new Criteria("mail").regex(salesmanCooperatorQuery.getMail()));
		}
		if(!StringUtils.isEmpty(salesmanCooperatorQuery.getStatus())){
			query.addCriteria(new Criteria("status").is(salesmanCooperatorQuery.getStatus()));
		}
		Criteria createTime = null;
		if(!StringUtils.isEmpty(salesmanCooperatorQuery.getStart_time())){
			if(createTime==null){
				createTime = new Criteria("create_time");
			}
			createTime.gte(DateUtil.StringToDate(salesmanCooperatorQuery.getStart_time(),DateStyle.YYYY_MM_DD_HH_MM_SS_EN));
		}
		if(!StringUtils.isEmpty(salesmanCooperatorQuery.getEnd_time())){
			if(createTime==null){
				createTime = new Criteria("create_time");
			}
			createTime.lte(DateUtil.StringToDate(salesmanCooperatorQuery.getEnd_time(),DateStyle.YYYY_MM_DD_HH_MM_SS_EN));
		}
		if(null!=createTime){
			query.addCriteria(createTime);
		}
		query.with(new Sort(new Order(Direction.DESC,"create_time"))); 
		query.skip((pageNo-1)*pageSize).limit(pageSize); 
		Page<SalesmanCooperator> page = new Page<SalesmanCooperator>();
		page.setDatas(this.mongoTemplate.find(query, SalesmanCooperator.class));
		page.setPageNo(pageNo);
		page.setRowCount(ConvertUtil.longToInteger(this.mongoTemplate.count(query, SalesmanCooperator.class)));
		return page;
	}  
	public boolean updateSalesmanCooperator(SalesmanCooperator salesmanCooperator) throws YuleException {
		boolean flag = false;
		Query query = new Query();  
		query.addCriteria(new Criteria("_id").is(salesmanCooperator.getId()));  
        Update update = new Update();  
		if(!StringUtils.isEmpty(salesmanCooperator.getAddress())){
			update.set("address", salesmanCooperator.getAddress());  
		}
		if(!StringUtils.isEmpty(salesmanCooperator.getDetails())){
			update.set("details",salesmanCooperator.getDetails());  
		}
		if(!StringUtils.isEmpty(salesmanCooperator.getAccount())){
			update.set("account", salesmanCooperator.getAccount());
		}
		if(!StringUtils.isEmpty(salesmanCooperator.getPhone())){
			update.set("phone", salesmanCooperator.getPhone());
		}
		if(!StringUtils.isEmpty(salesmanCooperator.getStatus())){
			update.set("status", salesmanCooperator.getStatus());
		}
		if(StringUtils.isEmpty(salesmanCooperator.getUpdate_time())){
			update.set("update_time", DateUtil.getCurrentDate());  
		}
		try{
			this.mongoTemplate.updateFirst(query, update, SalesmanCooperator.class);  
			flag = true;
		}catch(Exception e){
			throw new YuleException(e);
		}
		return flag;
	}


}
