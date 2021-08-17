package com.yule.runnable;

import com.yule.constant.CustomBeanConst;
import com.yule.enumerate.LogEnum;
import com.yule.exception.YuleException;
import com.yule.mongo.admin.service.IAdminLogMongo;
import com.yule.mongo.pojo.AdminLog;
import com.yule.util.CustomBeanFactory;


public class AdminLogRunnable implements Runnable {

	private static IAdminLogMongo adminLogMongoImpl = (IAdminLogMongo)CustomBeanFactory.getContext(CustomBeanConst.ADMIN_MONGO_PATHS).getBean("adminLogMongoImpl");
	
	// 日志名
	private String name;
	// 操作人ID
	private String admin_user_id;
	// 操作人名称
	private String admin_user_name;
	// 操作状态
	private int status;
	
	public AdminLogRunnable(String name,String admin_user_id,String admin_user_name,LogEnum logEnum) {
		super();
		this.name = name;
		this.admin_user_id = admin_user_id;
		this.admin_user_name = admin_user_name;
		this.status = logEnum.getValue();
	}

	public void run() {
		try {
			AdminLog adminLog  = new AdminLog(); 
			adminLog.setAdmin_user_id(admin_user_id);
			adminLog.setAdmin_user_name(admin_user_name);
			adminLog.setName(name);
			adminLog.setStatus(status);
			adminLogMongoImpl.insertAdminLog(adminLog);
		} catch (Exception e) {
			new YuleException("发生错误!",e);
			e.printStackTrace();
		}
	}
}
