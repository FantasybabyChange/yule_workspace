package com.yule.company.service;

import java.util.List;

import com.yule.exception.YuleException;
import com.yule.pojo.AdminUser;


public interface IAdminUserService {
	
	public List<AdminUser> findAdminUserListByRoleIds(List<String> roleIds)throws YuleException;
	
}
