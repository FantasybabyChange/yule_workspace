package com.yule.admin.dao;

import java.util.List;
import java.util.Map;

import com.yule.admin.vo.AdminPrivilegeVO;
import com.yule.exception.YuleException;
import com.yule.pojo.AdminPrivilege;

public interface IAdminPrivilegeDao {
	
	public int batchInsertAdminPrivilege(List<AdminPrivilege> adminPrivileges) throws YuleException;
	
	public int insertAdminPrivilege(AdminPrivilege adminPrivilege) throws YuleException;

	public int updateAdminPrivilege(AdminPrivilege adminPrivilege) throws YuleException;

	public int deleteAdminPrivilegeById(String id) throws YuleException;
	
	public int deleteAdminPrivilegeAll() throws YuleException;

	public AdminPrivilege findAdminPrivilegeById(String id) throws YuleException;

	public List<AdminPrivilegeVO> findAdminPrivilegeVOList(Map<String, Object> params) throws YuleException;

	public int findAdminPrivilegeCount(Map<String, Object> params) throws YuleException;
	
	public List<AdminPrivilege> findAdminPrivilegeList(Map<String, Object> params) throws YuleException;
	
	public List<AdminPrivilege> findAdminPrivilegeParentListByAdminRoleId(Map<String, Object> params) throws YuleException;

	public List<AdminPrivilege> findAdminPrivilegeListByAdminRoleId(String id) throws YuleException;
	
	public List<AdminPrivilege> findAdminPrivilegeCodeListByAdminRoleId(String id) throws YuleException;
	
    public List<AdminPrivilege> findAdminPrivilegeByParentId(Map<String, Object> params) throws YuleException;
	
}
