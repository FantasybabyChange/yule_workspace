package com.yule.mongo.admin.service;

import com.yule.exception.YuleException;
import com.yule.mongo.admin.query.AdminLogQuery;
import com.yule.mongo.pojo.AdminLog;
import com.yule.vo.Page;

public interface IAdminLogMongo {
	
	public Page<AdminLog> findAdminLogPage(AdminLogQuery adminLogQuery,int pageSize,int pageNo) throws YuleException;
	
	public boolean insertAdminLog(AdminLog adminLog)  throws YuleException;
	
}
