package com.yule.util;

import java.util.List;

import com.yule.company.service.ICompanyPrivilegeService;
import com.yule.constant.CustomBeanConst;
import com.yule.constant.TimeConst;
import com.yule.pojo.CompanyPrivilege;
import com.yule.redis.constant.RedisConst;
import com.yule.redis.util.JedisUtil;

public class PrivilegeUtil {
	
	public static void main(String[] args) {
		JedisUtil.getInstance().del(RedisConst.COMPANY_PRIVILEGE_URL+"925bb45f-6bc9-44b2-b63d-91abebf5bff1");
	}
	
	public static boolean getCompanyPrivilegeUrl(String companyUserId,String url) throws  Exception{
		String key = RedisConst.COMPANY_PRIVILEGE_URL+companyUserId;
		if(!JedisUtil.getInstance().exists(key)){
			ICompanyPrivilegeService companyPrivilegeServiceImpl = (ICompanyPrivilegeService)CustomBeanFactory.getContext(CustomBeanConst.COMPANY_SERVICE_PATHS).getBean("companyPrivilegeServiceImpl");
			List<CompanyPrivilege> lists = companyPrivilegeServiceImpl.findCompanyPrivilegeListByCompanyUserId(companyUserId,null);
			if(null!=lists&&lists.size()>0){
				for(CompanyPrivilege companyPrivilege :lists){
					JedisUtil.getInstance().sadd(key, companyPrivilege.getUrl());
				}
				lists.clear();
				lists = null;
				JedisUtil.getInstance().pexpire(key, TimeConst.ONE_HOUR);
			}
			companyPrivilegeServiceImpl = null;
		}
		boolean flag = JedisUtil.getInstance().sismember(key,url);
		return flag;
	}
	
	public static void cleanPrivilege(String companyUserId) throws Exception{
		JedisUtil.getInstance().del(RedisConst.COMPANY_PRIVILEGE+companyUserId);
//		JedisUtil.getInstance().del(CompanyRedisConst.COMPANY_PRIVILEGE_URL+companyUserId);
	}
	
}
