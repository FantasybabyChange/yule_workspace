package com.yule.admin.service;

import java.util.List;

import com.yule.admin.param.InsertAdminRoleParam;
import com.yule.admin.param.UpdateAdminRolePrivilegeParam;
import com.yule.admin.vo.AdminRoleVO;
import com.yule.exception.YuleException;
import com.yule.pojo.AdminRole;
import com.yule.pojo.AdminRolePrivilege;

public interface IAdminRoleService {
	
	public boolean insertAdminRole(AdminRole adminRole) throws YuleException;	
	
	public boolean updateAdminRole(AdminRole adminRole) throws YuleException;

	public boolean deleteAdminRoleById(String id) throws YuleException;
	
	public boolean deleteAdminRoleAll() throws YuleException;

	public AdminRole findAdminRoleById(String id) throws YuleException ;

	public List<AdminRole> findAdminRoleList() throws YuleException ;

	public int findAdminRoleCount() throws YuleException ;

	public List<AdminRole> findAdminRoleByAdminUserId(String id) throws YuleException;
	
	public List<AdminRoleVO> findAdminRoleVOByAdminUserId(String id) throws YuleException;
	
	public boolean batchInsertAndUpdateAdminRole(InsertAdminRoleParam insertAdminRoleParam) throws YuleException;	
	
	public boolean updateAdminRolePrivilege(UpdateAdminRolePrivilegeParam updateAdminRolePrivilegeParam) throws YuleException;
	
	public boolean batchInsertAdminRolePrivilege(List<AdminRolePrivilege> adminRolePrivilege) throws YuleException;
	
	public List<AdminRolePrivilege> findAdminRoleIdListByAdminPrivilegeId(String adminPrivilegeId) throws YuleException;

}
