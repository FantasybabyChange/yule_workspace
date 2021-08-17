package com.yule.company.dao;

import java.util.List;

import com.yule.exception.YuleException;
import com.yule.pojo.AdminRole;

public interface IAdminRoleDao {
	public List<AdminRole> findAdminRoleList() throws YuleException;
}
