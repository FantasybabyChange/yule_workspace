package com.yule.admin.service.impl;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.yule.admin.dao.IAdminUserDao;
import com.yule.admin.service.IAdminUserService;
import com.yule.admin.vo.AdminUserVO;
import com.yule.constant.DeleteConst;
import com.yule.exception.YuleException;
import com.yule.pojo.AdminUser;
import com.yule.util.EncryptUtil;
import com.yule.util.IDUtil;
import com.yule.vo.Page;

@Service("adminUserServiceImpl")
public class AdminUserServiceImpl implements IAdminUserService {

	@Autowired
	private IAdminUserDao adminUserDao;

	public boolean insertAdminUser(AdminUser adminUser) throws YuleException {
		boolean flag = false;
		try {
			adminUser.setPassword(EncryptUtil.encryptToMD5(adminUser.getPassword()));
			if(StringUtils.isEmpty(adminUser.getId())){
				adminUser.setId(IDUtil.getID());
			}
			adminUser.setIs_delete(DeleteConst.IS_DELETE_TRUE);
			this.adminUserDao.insertAdminUser(adminUser);
			flag = true;
		} catch (Exception e) {
	    	throw new YuleException(e);
	    }
		return flag;
	}
	
	public boolean updateAdminUser(AdminUser adminUser,boolean isDeleteAdminUserRole) throws YuleException {
		boolean flag = false;
		try {
			this.adminUserDao.updateAdminUser(adminUser);
			flag = true;
		} catch (Exception e) {
	    	throw new YuleException(e);
	    }
		return flag;

	}

	public boolean deleteAdminUserById(String id) throws YuleException {
		boolean flag = false;
		try {
			this.adminUserDao.deleteAdminUserById(id);
			flag = true;
		} catch (Exception e) {
	    	throw new YuleException(e);
	    }
		return flag;

	}

	public AdminUserVO findAdminUserVOById(String id) throws YuleException {
		return this.adminUserDao.findAdminUserVOById(id);
	}
	
	public AdminUser findAdminUserById(String id) throws YuleException {
		return this.adminUserDao.findAdminUserById(id);
	}

	public AdminUserVO findAdminUserVOByAccount(String account) throws YuleException {
		return this.adminUserDao.findAdminUserVOByAccount(account);
	}
	
	public AdminUser findAdminUserByAccount(String account) throws YuleException {
		return this.adminUserDao.findAdminUserByAccount(account);
	}
	
	public int findAdminUserCountByAccount(String account) throws YuleException {
		return this.adminUserDao.findAdminUserCountByAccount(account);
	}

	public Page<AdminUser> findAdminUserPage(int pageSize,int pageNo) throws YuleException {		
		Page<AdminUser> page = new Page<AdminUser>();
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("pageStart", (pageNo - 1) * pageSize);
			params.put("pageEnd", pageSize);
			
			page.setDatas(this.adminUserDao.findAdminUserList(params));
			page.setRowCount(this.adminUserDao.findAdminUserCount());
			page.setPageSize(pageSize);
			page.setPageNo(pageNo);
		} catch (Exception e) {
	    	throw new YuleException(e);
	    }
		return page;
	}

	public boolean updateAdminUser(AdminUser adminUser) throws YuleException {
		boolean flag = false;
		try {
			this.adminUserDao.updateAdminUser(adminUser);
			flag = true;
		} catch (Exception e) {
	    	throw new YuleException(e);
	    }
		return flag;
	}

	public boolean deleteAdminUserAll() throws YuleException {
		boolean flag = false;
	    try {
	    	this.adminUserDao.deleteAdminUserAll();
	    	flag = true;
	    } catch(Exception e) {
	    	throw new YuleException(e);
	    }
		return flag; 
	}

	public List<AdminUser> findAdminUserByAdminRoleId(String id) throws YuleException{
		return this.adminUserDao.findAdminUserByRoleId(id);
	}

}
