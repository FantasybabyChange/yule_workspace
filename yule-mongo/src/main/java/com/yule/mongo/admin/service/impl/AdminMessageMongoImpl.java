package com.yule.mongo.admin.service.impl;

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

import com.yule.constant.DeleteConst;
import com.yule.enumerate.DateStyle;
import com.yule.exception.YuleException;
import com.yule.mongo.admin.service.IAdminMessageMongo;
import com.yule.mongo.pojo.AdminMessage;
import com.yule.mongo.query.AdminMessageQuery;
import com.yule.util.ConvertUtil;
import com.yule.util.DateUtil;
import com.yule.vo.Page;

@Repository("adminMessageMongoImpl")
public class AdminMessageMongoImpl implements IAdminMessageMongo{

	@Autowired  
    private MongoTemplate mongoTemplate;  
	
	public Page<AdminMessage> findAdminMessagePage(AdminMessageQuery adminMessageQuery,int pageSize,int pageNo) throws YuleException{
		Query query = new Query();
		if(!StringUtils.isEmpty(adminMessageQuery.getAdmin_user_id())){
			query.addCriteria(new Criteria("send_id").is(adminMessageQuery.getAdmin_user_id()));
		}
		if(!StringUtils.isEmpty(adminMessageQuery.getReceive_id())){
			query.addCriteria(new Criteria("receive_id").is(adminMessageQuery.getReceive_id()));
		}
		if(!StringUtils.isEmpty(adminMessageQuery.getContent())){
			query.addCriteria(new Criteria("content").in(adminMessageQuery.getContent()));
		}
		if(!StringUtils.isEmpty(adminMessageQuery.getTitle())){
			query.addCriteria(new Criteria("title").regex(adminMessageQuery.getTitle()));
		}
		if(!StringUtils.isEmpty(adminMessageQuery.getIs_read())){
			query.addCriteria(new Criteria("is_read").is(adminMessageQuery.getIs_read()));
		}
		Criteria createTime = null;
		if(!StringUtils.isEmpty(adminMessageQuery.getStart_time())){
			if(createTime==null){
				createTime = new Criteria("create_time");
			}
			createTime.gte(DateUtil.StringToDate(adminMessageQuery.getStart_time(),DateStyle.YYYY_MM_DD_HH_MM_SS_EN));
		}
		if(!StringUtils.isEmpty(adminMessageQuery.getEnd_time())){
			if(createTime==null){
				createTime = new Criteria("create_time");
			}
			createTime.lte(DateUtil.StringToDate(adminMessageQuery.getEnd_time(),DateStyle.YYYY_MM_DD_HH_MM_SS_EN));
		}
		if(null!=createTime){
			query.addCriteria(createTime);
		}
		query.with(new Sort(new Order(Direction.DESC,"create_time")));  
		query.skip((pageNo-1)*pageSize).limit(pageSize); 
		Page<AdminMessage> page = new Page<AdminMessage>();
		page.setDatas(this.mongoTemplate.find(query, AdminMessage.class));
		page.setPageNo(pageNo);
		page.setRowCount(ConvertUtil.longToInteger(this.mongoTemplate.count(query, AdminMessage.class)));
		return page;
	}
	
	public AdminMessage findAdminMessageById(String id) throws YuleException{
		return this.mongoTemplate.findById(new ObjectId(id), AdminMessage.class);
	}
	
	public boolean insertAdminMessage(AdminMessage AdminMessage)  throws YuleException {
		boolean flag = false;
		try{
			if(null==AdminMessage.getCreate_time()){
				AdminMessage.setCreate_time(DateUtil.getCurrentDate());
			}
			this.mongoTemplate.save(AdminMessage);
			flag = true;
		}catch(Exception e){
			throw new YuleException(e);
		}
		return flag;
	}
	
	public boolean updateAdminMessage(AdminMessage adminMessage) throws YuleException {
		boolean flag = false;
		Query query = new Query();  
		query.addCriteria(new Criteria("_id").is(adminMessage.getId()));  
        Update update = new Update();  
		if(!StringUtils.isEmpty(adminMessage.getSend_id())){
			update.set("send_id", adminMessage.getSend_id());  
		}
		if(!StringUtils.isEmpty(adminMessage.getReceive_id())){
			update.set("receive_id", adminMessage.getReceive_id());  
		}
		if(!StringUtils.isEmpty(adminMessage.getContent())){
			update.set("content", adminMessage.getContent());
		}
		if(!StringUtils.isEmpty(adminMessage.getIs_read())){
			update.set("is_read", adminMessage.getIs_read());
		}
		if(!StringUtils.isEmpty(adminMessage.getIs_delete())){
			update.set("is_delete", adminMessage.getIs_delete());
		}
		if(!StringUtils.isEmpty(adminMessage.getTitle())){
			update.set("title", adminMessage.getTitle());
		}
		try{
			this.mongoTemplate.updateFirst(query, update, AdminMessage.class);  
			flag = true;
		}catch(Exception e){
			throw new YuleException(e);
		}
		return flag;
	}
	
	public boolean deleteAdminMessageById(String id) throws YuleException{
		boolean flag = false;
		Query query = new Query();  
		query.addCriteria(new Criteria("_id").is(id));  
        Update update = new Update();  
        update.set("is_delete", DeleteConst.IS_DELETE_FALSE);
		try{
			this.mongoTemplate.remove(query, AdminMessage.class);
			flag = true;
		}catch(Exception e){
			throw new YuleException(e);
		}
		return flag;
	}

	public long findAdminMessageCountByIsRead(String receiveId,Integer isRead) throws YuleException {
		Query query = new Query();
		if(!StringUtils.isEmpty(isRead)){
			query.addCriteria(new Criteria("is_read").is(isRead));
		}
		if(!StringUtils.isEmpty(isRead)){
			query.addCriteria(new Criteria("recevie_id").is(receiveId));
		}
		return this.mongoTemplate.count(query,AdminMessage.class);
	}

	public boolean batchInsertAdminMessage(List<AdminMessage> adminMessages)throws YuleException {
		boolean flag = false;
		try{
			this.mongoTemplate.insertAll(adminMessages);
			flag = true;
		}catch(Exception e){
			throw new YuleException(e);
		}
		return flag;
	}

}
