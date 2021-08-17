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
import com.yule.mongo.admin.query.CompanyCooperatorQuery;
import com.yule.mongo.admin.service.ICompanyCooperatorMongo;
import com.yule.mongo.pojo.CompanyCooperator;
import com.yule.util.ConvertUtil;
import com.yule.util.DateUtil;
import com.yule.vo.Page;

@Repository("companyCooperatorMongoImpl")
public class CompanyCooperatorMongoImpl implements ICompanyCooperatorMongo{

	@Autowired  
    private MongoTemplate mongoTemplate;  
	
	public Page<CompanyCooperator> findCompanyCooperatorPage(CompanyCooperatorQuery companyCooperatorQuery,int pageSize,int pageNo) throws YuleException{
		Query query = new Query();
		if(!StringUtils.isEmpty(companyCooperatorQuery.getName())){
			query.addCriteria(new Criteria("name").regex(companyCooperatorQuery.getName()));
		}
		if(!StringUtils.isEmpty(companyCooperatorQuery.getPhone())){
			query.addCriteria(new Criteria("phone").regex(companyCooperatorQuery.getPhone()));
		}
		if(!StringUtils.isEmpty(companyCooperatorQuery.getMail())){
			query.addCriteria(new Criteria("mail").regex(companyCooperatorQuery.getMail()));
		}
		if(!StringUtils.isEmpty(companyCooperatorQuery.getStatus())){
			query.addCriteria(new Criteria("status").is(companyCooperatorQuery.getStatus()));
		}
		Criteria createTime = null;
		if(!StringUtils.isEmpty(companyCooperatorQuery.getStart_time())){
			if(createTime==null){
				createTime = new Criteria("create_time");
			}
			createTime.gte(DateUtil.StringToDate(companyCooperatorQuery.getStart_time(),DateStyle.YYYY_MM_DD_HH_MM_SS_EN));
		}
		if(!StringUtils.isEmpty(companyCooperatorQuery.getEnd_time())){
			if(createTime==null){
				createTime = new Criteria("create_time");
			}
			createTime.lte(DateUtil.StringToDate(companyCooperatorQuery.getEnd_time(),DateStyle.YYYY_MM_DD_HH_MM_SS_EN));
		}
		if(null!=createTime){
			query.addCriteria(createTime);
		}
		query.with(new Sort(new Order(Direction.DESC,"create_time"))); 
		query.skip((pageNo-1)*pageSize).limit(pageSize); 
		Page<CompanyCooperator> page = new Page<CompanyCooperator>();
		page.setDatas(this.mongoTemplate.find(query, CompanyCooperator.class));
		page.setPageNo(pageNo);
		page.setRowCount(ConvertUtil.longToInteger(this.mongoTemplate.count(query, CompanyCooperator.class)));
		return page;
	}
	
	public CompanyCooperator findCompanyCooperatorById(String id) throws YuleException{
		return this.mongoTemplate.findById(new ObjectId(id), CompanyCooperator.class);
	}
	
	public boolean deleteCompanyCooperatorById(String id) throws YuleException{
		boolean flag = false;
		Query query = new Query();  
		query.addCriteria(new Criteria("_id").is(id));  
		try{
			this.mongoTemplate.remove(query, CompanyCooperator.class);
			flag = true;
		}catch(Exception e){
			throw new YuleException(e);
		}
		return flag;
	}

	public boolean updateCompanyCooperator(CompanyCooperator companyCooperator) throws YuleException {
		boolean flag = false;
		Query query = new Query();  
		query.addCriteria(new Criteria("_id").is(companyCooperator.getId()));  
        Update update = new Update();  
		if(!StringUtils.isEmpty(companyCooperator.getAddress())){
			update.set("address", companyCooperator.getAddress());  
		}
		if(!StringUtils.isEmpty(companyCooperator.getDetails())){
			update.set("details", companyCooperator.getDetails());  
		}
		if(!StringUtils.isEmpty(companyCooperator.getName())){
			update.set("name", companyCooperator.getName());
		}
		if(!StringUtils.isEmpty(companyCooperator.getPhone())){
			update.set("phone", companyCooperator.getPhone());
		}
		if(!StringUtils.isEmpty(companyCooperator.getStatus())){
			update.set("status", companyCooperator.getStatus());
		}
		if(StringUtils.isEmpty(companyCooperator.getUpdate_time())){
			update.set("update_time", DateUtil.getCurrentDate());  
		}
		try{
			this.mongoTemplate.updateFirst(query, update, CompanyCooperator.class);  
			flag = true;
		}catch(Exception e){
			throw new YuleException(e);
		}
		return flag;
	}

}
