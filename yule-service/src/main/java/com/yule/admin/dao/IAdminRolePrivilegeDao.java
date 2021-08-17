package com.yule.admin.dao;

import java.util.List;
import java.util.Map;

import com.yule.exception.YuleException;
import com.yule.pojo.AdminRolePrivilege;

public interface IAdminRolePrivilegeDao {
	
	public int deleteAdminRolePrivilegeAll()  throws YuleException;

	public int updateAdminRolePrivilege(Map<String,Object> params) throws YuleException;
	
	public int batchInsertAdminRolePrivilege(List<AdminRolePrivilege> adminRolePrivilege) throws YuleException;
	
	public List<AdminRolePrivilege> findAdminRoleIdListByAdminPrivilegeId(String adminPrivilegeId) throws YuleException;
	
}
