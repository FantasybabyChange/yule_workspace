package com.yule.admin.dao;

import java.util.List;
import java.util.Map;

import com.yule.admin.vo.AdminUserVO;
import com.yule.exception.YuleException;
import com.yule.pojo.AdminUser;

public interface IAdminUserDao {
	
	public int insertAdminUser(AdminUser adminUser) throws YuleException;

	public int updateAdminUser(AdminUser adminUser) throws YuleException;

	public int deleteAdminUserById(String id)  throws YuleException;
	
	public int deleteAdminUserAll() throws YuleException;

	public AdminUserVO findAdminUserVOById(String id) throws YuleException;
	
	public AdminUser findAdminUserById(String id) throws YuleException;
	
	public int findAdminUserCount() throws YuleException;
	
	public AdminUser findAdminUserByAccount(String account) throws YuleException;
	
	public int findAdminUserCountByAccount(String account) throws YuleException;

	public AdminUserVO findAdminUserVOByAccount(String account) throws YuleException;

	public List<AdminUser> findAdminUserList(Map<String,Object> params) throws YuleException;
	
	public List<AdminUser> findAdminUserByRoleId(String id) throws YuleException;
	
}
