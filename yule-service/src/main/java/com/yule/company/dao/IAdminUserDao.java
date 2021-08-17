package com.yule.company.dao;

import java.util.List;

import com.yule.exception.YuleException;
import com.yule.pojo.AdminUser;


public interface IAdminUserDao {
	public List<AdminUser> findAdminUserListByRoleIds(List<String> roleIds)throws YuleException;
	
	
}


