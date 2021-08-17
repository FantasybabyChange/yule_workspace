package com.yule.runnable;

import java.util.List;

import com.alibaba.druid.util.StringUtils;
import com.yule.admin.service.IAdminRoleService;
import com.yule.constant.CustomBeanConst;
import com.yule.exception.YuleException;
import com.yule.pojo.AdminRolePrivilege;
import com.yule.util.CustomBeanFactory;
import com.yule.util.PrivilegeUtil;

public class AdminRolePrivilegeRunnable implements Runnable{
	
	private  String adminPrivilegeId;
	
	private List<AdminRolePrivilege> adminRolePrivileges;

	public AdminRolePrivilegeRunnable(String adminPrivilegeId ,List<AdminRolePrivilege> adminRolePrivileges) {
		super();
		this.adminPrivilegeId = adminPrivilegeId;
		this.adminRolePrivileges=adminRolePrivileges;
	}

	public void run() {
		try {
			IAdminRoleService adminRoleServiceImpl = (IAdminRoleService)CustomBeanFactory.getContext(CustomBeanConst.ADMIN_SERVICE_PATHS).getBean("adminRoleServiceImpl");
			if(!StringUtils.isEmpty(adminPrivilegeId)){
				adminRolePrivileges = adminRoleServiceImpl.findAdminRoleIdListByAdminPrivilegeId(adminPrivilegeId);

			}
			if(null!=adminRolePrivileges&&adminRolePrivileges.size()>0){
				for (AdminRolePrivilege adminRolePrivilege : adminRolePrivileges) {
					PrivilegeUtil.initPrivilege(adminRolePrivilege.getAdmin_role_id());
				}
				adminRolePrivileges.clear();
				adminRolePrivileges= null;
			}
		} catch (Exception e) {
			new YuleException("发生错误!",e);
			e.printStackTrace();
		}
	}

}
