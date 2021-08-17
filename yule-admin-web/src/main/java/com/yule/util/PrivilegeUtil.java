package com.yule.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.yule.admin.service.IAdminPrivilegeService;
import com.yule.constant.CustomBeanConst;
import com.yule.pojo.AdminPrivilege;

public class PrivilegeUtil {
	
	private static Map<String,Map<String,Map<String,AdminPrivilege>>> privilegeCodes = new HashMap<String, Map<String,Map<String,AdminPrivilege>>>();
	
	private static Map<String,Map<String,String>> privilegeUrls = new HashMap<String, Map<String,String>>();
	
	public static Map<String,String> getAdminPrivilegeUrl(String admin_role_id) throws  Exception{
//		if(!jedis.exists(admin_role_id)){
//			IAdminPrivilegeService adminPrivilegeServiceImpl = (IAdminPrivilegeService)CustomBeanFactory.getContext(CustomBeanConst.ADMIN_SERVICE_PATHS).getBean("adminPrivilegeServiceImpl");
//			List<AdminPrivilege> lists = adminPrivilegeServiceImpl.findAdminPrivilegeListByAdminRoleId(admin_role_id);
//			if(null!=lists&&lists.size()>0){
//				Map<String,String> maps = new HashMap<String, String>();
//				for(AdminPrivilege adminPrivilege :lists){
//					maps.put(adminPrivilege.getUrl(),adminPrivilege.getId());
//				}
//				lists.clear();
//				lists = null;
//				jedis.hmset(admin_role_id, maps);
//				maps.clear();
//				maps = null;
//			}
//			adminPrivilegeServiceImpl = null;
//		}
//		jedis.hget(admin_role_id, field)
		if(!privilegeUrls.containsKey(admin_role_id)){
			IAdminPrivilegeService adminPrivilegeServiceImpl = (IAdminPrivilegeService)CustomBeanFactory.getContext(CustomBeanConst.ADMIN_SERVICE_PATHS).getBean("adminPrivilegeServiceImpl");
			List<AdminPrivilege> lists = adminPrivilegeServiceImpl.findAdminPrivilegeListByAdminRoleId(admin_role_id);
			if(null!=lists&&lists.size()>0){
				Map<String,String> maps = new HashMap<String, String>();
				for(AdminPrivilege adminPrivilege :lists){
					maps.put(adminPrivilege.getUrl(),adminPrivilege.getId());
				}
				lists.clear();
				lists = null;
				privilegeUrls.put(admin_role_id, maps);
			}
			adminPrivilegeServiceImpl = null;
		}
		return privilegeUrls.get(admin_role_id);
	}
	
	public static Map<String,Map<String,AdminPrivilege>> getAdminPrivilegeCode(String admin_role_id) throws  Exception{
		if(!privilegeCodes.containsKey(admin_role_id)){
			IAdminPrivilegeService adminPrivilegeServiceImpl = (IAdminPrivilegeService)CustomBeanFactory.getContext(CustomBeanConst.ADMIN_SERVICE_PATHS).getBean("adminPrivilegeServiceImpl");
			List<AdminPrivilege> lists = adminPrivilegeServiceImpl.findAdminPrivilegeCodeListByAdminRoleId(admin_role_id);
			if(null!=lists&&lists.size()>0){
				Map<String,Map<String, AdminPrivilege>> userMaps = new HashMap<String, Map<String,AdminPrivilege>>();
				Map<String,AdminPrivilege> maps = null;
				for(AdminPrivilege adminPrivilege :lists){
					// 判断他的父URL是否存在  存在就新增操作 
					if(userMaps.containsKey(adminPrivilege.getParent_id())){
						userMaps.get(adminPrivilege.getParent_id()).put(adminPrivilege.getCode(), adminPrivilege);
					}else{
						maps = new HashMap<String, AdminPrivilege>();
						maps.put(adminPrivilege.getCode(), adminPrivilege);
						userMaps.put(adminPrivilege.getParent_id(), maps);
					}
				}
				lists.clear();
				lists = null;
				privilegeCodes.put(admin_role_id, userMaps);
			}
			adminPrivilegeServiceImpl = null;
		}
		return privilegeCodes.get(admin_role_id);
	}
	
	public static void initPrivilege(String admin_role_id) throws Exception{
		privilegeCodes.remove(admin_role_id);
		privilegeUrls.remove(admin_role_id);
		getAdminPrivilegeUrl(admin_role_id);
		getAdminPrivilegeCode(admin_role_id);
	}
	
	public static void cleanPrivilege() throws Exception{
		privilegeCodes.clear();
		privilegeUrls.clear();
	}
	
}
