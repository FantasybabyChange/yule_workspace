package com.yule.admin.service;

import java.util.List;

import com.yule.admin.vo.AdminUserVO;
import com.yule.exception.YuleException;
import com.yule.pojo.AdminUser;
import com.yule.vo.Page;

public interface IAdminUserService {
	
	public boolean insertAdminUser(AdminUser adminUser) throws YuleException;
	/**
	 * 修改用户信息
	 */
	public boolean updateAdminUser(AdminUser adminUser) throws YuleException;
	
	public boolean deleteAdminUserById(String id) throws YuleException;
	
	public boolean deleteAdminUserAll() throws YuleException;

	public AdminUserVO findAdminUserVOById(String id) throws YuleException;
	
	public AdminUser findAdminUserById(String id) throws YuleException;
	
	public AdminUserVO findAdminUserVOByAccount(String account) throws YuleException ;

	public Page<AdminUser> findAdminUserPage(int pageSize,int pageNo) throws YuleException ;
	
	public List<AdminUser> findAdminUserByAdminRoleId(String id) throws YuleException ;
	
	public AdminUser findAdminUserByAccount(String account) throws YuleException ;
	
	public int findAdminUserCountByAccount(String account) throws YuleException;

}
