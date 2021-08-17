package com.yule.company.service.impl;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yule.company.dao.IAdminUserDao;
import com.yule.company.service.IAdminUserService;
import com.yule.exception.YuleException;
import com.yule.pojo.AdminUser;

@Service("adminUserServiceImpl")
public class AdminUserServiceImpl implements IAdminUserService {
	@Autowired
	private IAdminUserDao adminUserDao;
	public List<AdminUser> findAdminUserListByRoleIds(List<String> roleIds)
			throws YuleException {
		return this.adminUserDao.findAdminUserListByRoleIds(roleIds);
	}
	
	
	
}
