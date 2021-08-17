package com.yule.mongo.company.service;

import java.util.List;

import com.yule.exception.YuleException;
import com.yule.mongo.pojo.AdminMessage;
import com.yule.mongo.query.AdminMessageQuery;
import com.yule.vo.Page;

public interface IAdminMessageMongo {
	
	public Page<AdminMessage> findAdminMessagePage(AdminMessageQuery adminMessageQuery,int pageSize,int pageNo) throws YuleException;
	
	public AdminMessage findAdminMessageById(String id) throws YuleException;
	
	public boolean updateAdminMessage(AdminMessage adminMessage) throws YuleException;
		
	public boolean insertAdminMessage(AdminMessage adminMessage)  throws YuleException;
	
	public boolean deleteAdminMessageById(String id) throws YuleException;
	
	public Page<AdminMessage> findAdminMessagePageByType(AdminMessageQuery adminMessageQuery,int pageSize,int pageNo) throws YuleException;
	
	public int findAdminMessageCount(AdminMessageQuery adminMessageQuery) throws YuleException;
	
	public boolean batchInsertAdminMessage(List<AdminMessage> adminMessages)  throws YuleException;
}
