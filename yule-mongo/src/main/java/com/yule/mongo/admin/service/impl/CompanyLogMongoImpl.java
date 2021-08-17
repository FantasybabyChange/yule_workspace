package com.yule.mongo.admin.service.impl;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.yule.enumerate.DateStyle;
import com.yule.exception.YuleException;
import com.yule.mongo.admin.query.CompanyLogQuery;
import com.yule.mongo.admin.service.ICompanyLogMongo;
import com.yule.mongo.pojo.CompanyLog;
import com.yule.util.ConvertUtil;
import com.yule.util.DateUtil;
import com.yule.vo.Page;

@Repository("companyLogMongoImpl")
public class CompanyLogMongoImpl implements ICompanyLogMongo{
	@Autowired  
    private MongoTemplate mongoTemplate;  
	
	public Page<CompanyLog> findCompanyLogPage(CompanyLogQuery companyLogQuery,int pageSize,int pageNo) throws YuleException{
		Query query = new Query();
		if(!StringUtils.isEmpty(companyLogQuery.getCompany_id())){
			query.addCriteria(new Criteria("company_id").is(companyLogQuery.getCompany_id()));
		}
		if(!StringUtils.isEmpty(companyLogQuery.getName())){
			query.addCriteria(new Criteria("name").regex(companyLogQuery.getName()));
		}
		Criteria createTime = null;
		if(!StringUtils.isEmpty(companyLogQuery.getStart_time())){
			if(createTime==null){
				createTime = new Criteria("create_time");
			}
			createTime.gte(DateUtil.StringToDate(companyLogQuery.getStart_time(),DateStyle.YYYY_MM_DD_HH_MM_SS_EN));
		}
		if(!StringUtils.isEmpty(companyLogQuery.getEnd_time())){
			if(createTime==null){
				createTime = new Criteria("create_time");
			}
			createTime.lte(DateUtil.StringToDate(companyLogQuery.getEnd_time(),DateStyle.YYYY_MM_DD_HH_MM_SS_EN));
		}
		if(null!=createTime){
			query.addCriteria(createTime);
		}
		List<Integer> status = companyLogQuery.getStatus();
		if (status != null && status.size() > 0) {
			query.addCriteria(new Criteria("status").in(status));
		}
		query.with(new Sort(new Order(Direction.DESC,"create_time")));  
		query.skip((pageNo-1)*pageSize).limit(pageSize); 
		Page<CompanyLog> page = new Page<CompanyLog>();
		page.setDatas(this.mongoTemplate.find(query, CompanyLog.class));
		page.setPageNo(pageNo);
		page.setRowCount(ConvertUtil.longToInteger(this.mongoTemplate.count(query, CompanyLog.class)));
		if (status != null && status.size() > 0) {
			status.clear();
			status = null;
		}
		return page;
	}
}
