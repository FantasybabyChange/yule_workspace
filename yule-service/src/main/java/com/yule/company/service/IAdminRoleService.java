package com.yule.company.service;

import java.util.List;

import com.yule.exception.YuleException;
import com.yule.pojo.AdminRole;

public interface IAdminRoleService {
	
	public List<AdminRole> findAdminRoleList() throws YuleException ;


}
