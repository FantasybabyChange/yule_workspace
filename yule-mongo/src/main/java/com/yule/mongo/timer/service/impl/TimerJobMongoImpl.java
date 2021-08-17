package com.yule.mongo.timer.service.impl;

import java.util.List;

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

import com.yule.constant.StatusConst;
import com.yule.exception.YuleException;
import com.yule.mongo.pojo.TimerJob;
import com.yule.mongo.timer.service.ITimerJobMongo;
import com.yule.util.ConvertUtil;
import com.yule.util.DateUtil;
import com.yule.vo.Page;

@Repository("timerJobMongoImpl")
public class TimerJobMongoImpl implements ITimerJobMongo{

	@Autowired  
    private MongoTemplate mongoTemplate;  
	
	public Page<TimerJob> findTimerJobPage(int pageSize,int pageNo) throws YuleException{
		Query query = new Query();
		query.with(new Sort(new Order(Direction.DESC,"create_time")));  
		query.skip((pageNo-1)*pageSize).limit(pageSize);
		Page<TimerJob> page = new Page<TimerJob>();
		page.setDatas(this.mongoTemplate.find(query, TimerJob.class));
		page.setPageNo(pageNo);
		page.setRowCount(ConvertUtil.longToInteger(this.mongoTemplate.count(query, TimerJob.class)));
		return page;
	}
	
	public TimerJob findTimerJobById(String id) throws YuleException{
		return this.mongoTemplate.findById(new ObjectId(id), TimerJob.class);
	}
	
	public boolean insertTimerJob(TimerJob TimerJob) throws YuleException{
		boolean flag = false;
		try{
			if(null==TimerJob.getCreate_time()){
				TimerJob.setCreate_time(DateUtil.getCurrentDate());
			}
			this.mongoTemplate.save(TimerJob);
			flag = true;
		}catch(Exception e){
			throw new YuleException(e);
		}
		return flag;
	}
	
	public boolean updateTimerJob(TimerJob timerJob) throws YuleException{
		boolean flag = false;
		Query query = new Query();  
		query.addCriteria(new Criteria("_id").is(timerJob.getId()));  
        Update update = new Update();  
		if(!StringUtils.isEmpty(timerJob.getName())){
			update.set("name", timerJob.getName());  
		}
		if(!StringUtils.isEmpty(timerJob.getExecute_num())){
			update.set("execute_num", timerJob.getExecute_num());  
		}
		if(!StringUtils.isEmpty(timerJob.getExecute_time())){
			update.set("execute_time", timerJob.getExecute_time());  
		}
		if(!StringUtils.isEmpty(timerJob.getStatus())){
			update.set("status", timerJob.getStatus());  
		}
		if(!StringUtils.isEmpty(timerJob.getGroup())){
			update.set("group", timerJob.getGroup());  
		}
		if(!StringUtils.isEmpty(timerJob.getCronExpression())){
			update.set("cronExpression", timerJob.getCronExpression());  
		}
		if(!StringUtils.isEmpty(timerJob.getDetails())){
			update.set("details", timerJob.getDetails());  
		}
		if(!StringUtils.isEmpty(timerJob.getAsync())){
			update.set("async", timerJob.getAsync());  
		}
		if(!StringUtils.isEmpty(timerJob.getClassName())){
			update.set("className", timerJob.getClassName());  
		}
		if(StringUtils.isEmpty(timerJob.getUpdate_time())){
			update.set("update_time", DateUtil.getCurrentDate());  
		}
		try{
			this.mongoTemplate.updateFirst(query, update, TimerJob.class);  
			flag = true;
		}catch(Exception e){
			throw new YuleException(e);
		}
		return flag;
	}
	
	public boolean deleteTimerJob(String id) throws YuleException{
		boolean flag = false;
		Query query = new Query();  
		query.addCriteria(new Criteria("_id").is(id));
		try{
			this.mongoTemplate.remove(query,TimerJob.class);
			flag = true;
		}catch(Exception e){
			throw new YuleException(e);
		}
		return flag;
	}
	
	public List<TimerJob> findTimerJobList() throws YuleException {
		Query query = new Query();
		query.addCriteria(new Criteria("status").is(StatusConst.STATUS_TRUE));
		return this.mongoTemplate.findAll(TimerJob.class);
	}

}
