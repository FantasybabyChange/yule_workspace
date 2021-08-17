package com.yule.admin.service;

import java.util.List;

import com.yule.admin.vo.AdminPrivilegeVO;
import com.yule.exception.YuleException;
import com.yule.pojo.AdminPrivilege;
import com.yule.vo.Page;

public interface IAdminPrivilegeService {
	
	public boolean batchInsertAdminPrivilege(List<AdminPrivilege> adminPrivileges) throws YuleException;
	
	public boolean insertAdminPrivilege(AdminPrivilege adminPrivilege) throws YuleException;
	
	public boolean updateAdminPrivilege(AdminPrivilege adminPrivilege) throws YuleException;
	
	public boolean deleteAdminPrivilegeById(String id) throws YuleException;
	
	public boolean deleteAdminPrivilegeAll() throws YuleException;

	public AdminPrivilege findAdminPrivilegeById(String id) throws YuleException ;

	public List<AdminPrivilegeVO> findAdminPrivilegeVOList(String admin_role_id) throws YuleException ;
	
	public Page<AdminPrivilege> findAdminPrivilegePageByParentId(String parentId,int pageSize, int pageNo) throws YuleException ;

	public List<AdminPrivilege> findAdminPrivilegeParentListByAdminRoleId(String adminRoleId,String parentId) throws YuleException;
	
	public List<AdminPrivilege> findAdminPrivilegeCodeListByAdminRoleId(String id) throws YuleException;
	
	public List<AdminPrivilege> findAdminPrivilegeList(String parentId) throws YuleException;

	public List<AdminPrivilege> findAdminPrivilegeListByAdminRoleId(String id) throws YuleException ;
	
	public List<AdminPrivilege> findAdminPrivilegeListByParentId(String parentId) throws YuleException;

}
