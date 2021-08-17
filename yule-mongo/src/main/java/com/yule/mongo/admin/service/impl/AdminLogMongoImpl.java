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
import com.yule.mongo.admin.query.AdminLogQuery;
import com.yule.mongo.admin.service.IAdminLogMongo;
import com.yule.mongo.pojo.AdminLog;
import com.yule.util.ConvertUtil;
import com.yule.util.DateUtil;
import com.yule.vo.Page;

@Repository("adminLogMongoImpl")
public class AdminLogMongoImpl implements IAdminLogMongo{

	@Autowired  
    private MongoTemplate mongoTemplate;  
	
	public Page<AdminLog> findAdminLogPage(AdminLogQuery adminLogQuery,int pageSize,int pageNo) throws YuleException{
		Query query = new Query();
		if(!StringUtils.isEmpty(adminLogQuery.getAdmin_user_id())){
			query.addCriteria(new Criteria("admin_user_id").is(adminLogQuery.getAdmin_user_id()));
		}
		if(!StringUtils.isEmpty(adminLogQuery.getName())){
			query.addCriteria(new Criteria("name").regex(adminLogQuery.getName()));
		}
		Criteria createTime = null;
		if(!StringUtils.isEmpty(adminLogQuery.getStart_time())){
			if(createTime==null){
				createTime = new Criteria("create_time");
			}
			createTime.gte(DateUtil.StringToDate(adminLogQuery.getStart_time(),DateStyle.YYYY_MM_DD_HH_MM_SS_EN));
		}
		if(!StringUtils.isEmpty(adminLogQuery.getEnd_time())){
			if(createTime==null){
				createTime = new Criteria("create_time");
			}
			createTime.lte(DateUtil.StringToDate(adminLogQuery.getEnd_time(),DateStyle.YYYY_MM_DD_HH_MM_SS_EN));
		}
		if(null!=createTime){
			query.addCriteria(createTime);
		}
		List<Integer> status = adminLogQuery.getStatus();
		if ( status != null && status.size() > 0) {
			query.addCriteria(new Criteria("status").in(status));
		}
		query.with(new Sort(new Order(Direction.DESC,"create_time")));  
		query.skip((pageNo-1)*pageSize).limit(pageSize); 
		Page<AdminLog> page = new Page<AdminLog>();
		page.setDatas(this.mongoTemplate.find(query, AdminLog.class));
		page.setPageNo(pageNo);
		page.setRowCount(ConvertUtil.longToInteger(this.mongoTemplate.count(query, AdminLog.class)));
		if(null!=status){
			status.clear();
			status = null;
		}
		return page;
	}
	
	public boolean insertAdminLog(AdminLog adminLog)  throws YuleException {
		boolean flag = false;
		try{
			if(null==adminLog.getCreate_time()){
				adminLog.setCreate_time(DateUtil.getCurrentDate());
			}
			this.mongoTemplate.save(adminLog);
			flag = true;
		}catch(Exception e){
			throw new YuleException(e);
		}
		return flag;
	}
	
}
