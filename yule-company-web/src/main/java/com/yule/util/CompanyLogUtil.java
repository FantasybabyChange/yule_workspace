package com.yule.util;

import com.yule.company.vo.CompanyUserVO;
import com.yule.enumerate.LogEnum;
import com.yule.runnable.CompanyLogRunnable;

public class CompanyLogUtil {
	
	/**
	 * 添加系统日志
	 * @param name
	 * @param admin_user_id
	 * @param admin_user_name
	 * @param type
	 */
	public static void insertLog(String name,CompanyUserVO companyUser,LogEnum type){
		Thread log = new Thread(new CompanyLogRunnable(name, companyUser.getId(), companyUser.getAccount(),companyUser.getCompany_id(), type));
		log.start();
	}
	
}