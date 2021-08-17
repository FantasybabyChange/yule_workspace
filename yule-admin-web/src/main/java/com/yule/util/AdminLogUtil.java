package com.yule.util;

import com.yule.admin.vo.AdminUserVO;
import com.yule.enumerate.LogEnum;
import com.yule.runnable.AdminLogRunnable;

public class AdminLogUtil {
	
	/**
	 * 添加系统日志
	 * @param name
	 * @param admin_user_id
	 * @param admin_user_name
	 * @param type
	 */
	public static void insertLog(String name,AdminUserVO adminUser,LogEnum type){
		Thread log = new Thread(new AdminLogRunnable(name, adminUser.getId(), adminUser.getAccount(), type));
		log.start();
	}
	
}
