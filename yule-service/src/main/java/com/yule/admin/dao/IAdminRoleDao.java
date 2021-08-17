package com.yule.admin.dao;

import java.util.List;

import com.yule.admin.vo.AdminRoleVO;
import com.yule.exception.YuleException;
import com.yule.pojo.AdminRole;

public interface IAdminRoleDao {
	public int insertAdminRole(AdminRole adminRole) throws YuleException;

	public int updateAdminRole(AdminRole adminRole) throws YuleException;

	public int deleteAdminRoleById(String id) throws YuleException;
	
	public int deleteAdminRoleAll() throws YuleException;

	public AdminRole findAdminRoleById(String id) throws YuleException;

	public List<AdminRole> findAdminRoleList() throws YuleException;

	public int findAdminRoleCount() throws YuleException;

	public int findAdminRoleIsAdminByAdminUserId(String adminUserId) throws YuleException;
	
	/**
	 * 根据用户id获取角色信息
	 */
	public List<AdminRole> findAdminRoleByAdminUserId(String id) throws YuleException;
	
	public List<AdminRoleVO> findAdminRoleVOByAdminUserId(String id) throws YuleException;
	
	public int batchInsertAdminRole(List<AdminRole> adminRole) throws YuleException;
	
	public int batchUpdateAdminRole(List<AdminRole> adminRole) throws YuleException;

	public List<AdminRole> findAdminRoleIsAdminList()throws YuleException; 
}
