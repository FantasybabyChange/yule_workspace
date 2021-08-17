package com.yule.admin.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.yule.admin.dao.IAdminPrivilegeDao;
import com.yule.admin.dao.IAdminRoleDao;
import com.yule.admin.dao.IAdminRolePrivilegeDao;
import com.yule.admin.dao.IAdminUserDao;
import com.yule.admin.service.IAdminPrivilegeService;
import com.yule.admin.vo.AdminPrivilegeVO;
import com.yule.exception.YuleException;
import com.yule.pojo.AdminPrivilege;
import com.yule.util.IDUtil;
import com.yule.vo.Page;

@Service("adminPrivilegeServiceImpl")
public class AdminPrivilegeServiceImpl implements IAdminPrivilegeService {

	@Autowired
	private IAdminPrivilegeDao adminPrivilegeDao;
	
	@Autowired
	private IAdminRolePrivilegeDao adminRolePrivilegeDao;
	
	@Autowired
	private IAdminRoleDao  adminRoleDao;
	
	@Autowired
	private IAdminUserDao  adminUserDao;

	
	public boolean insertAdminPrivilege(AdminPrivilege adminPrivilege) throws YuleException {
		boolean flag = false;
		try {
			if(StringUtils.isEmpty(adminPrivilege.getId())){
				adminPrivilege.setId(IDUtil.getID());
			}
			if (StringUtils.isEmpty(adminPrivilege.getParent_id())) {
				adminPrivilege.setParent_id(null);
			}
			if (null==adminPrivilege.getCode()) {
				adminPrivilege.setCode("");
			}
			this.adminPrivilegeDao.insertAdminPrivilege(adminPrivilege);
			flag  = true;
		} catch (Exception e) {
			throw new YuleException(e);
		}
		return flag;
	}

	public boolean updateAdminPrivilege(AdminPrivilege adminPrivilege) throws YuleException {
		boolean flag = false;
		try {
			this.adminPrivilegeDao.updateAdminPrivilege(adminPrivilege);
			flag = true;
		} catch (Exception e) {
			throw new YuleException(e);
		}
		return flag;
	}
	
	public boolean deleteAdminPrivilegeById(String id) throws YuleException {
		boolean flag = false;
		try {
			adminPrivilegeDao.deleteAdminPrivilegeById(id);
			flag = true;
		} catch (Exception e) {
			throw new YuleException(e);
		}
		return flag;
	}

	public AdminPrivilege findAdminPrivilegeById(String id) throws YuleException{
		return this.adminPrivilegeDao.findAdminPrivilegeById(id);
	}

	public List<AdminPrivilegeVO> findAdminPrivilegeVOList(String admin_role_id) throws YuleException{
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("admin_role_id", admin_role_id);
		return this.adminPrivilegeDao.findAdminPrivilegeVOList(params);
	}

	public List<AdminPrivilege> findAdminPrivilegeParentListByAdminRoleId(String adminRoleId,String parentId)throws YuleException {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("parent_id", parentId);
		params.put("admin_role_id", adminRoleId);
		return this.adminPrivilegeDao.findAdminPrivilegeParentListByAdminRoleId(params);
	}
	
	public List<AdminPrivilege> findAdminPrivilegeList(String parentId)throws YuleException {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("parent_id", parentId);
		return this.adminPrivilegeDao.findAdminPrivilegeList(params);
	}

	public List<AdminPrivilege> findAdminPrivilegeListByAdminRoleId(String id) throws YuleException{
		return this.adminPrivilegeDao.findAdminPrivilegeListByAdminRoleId(id);
	}
	
	public List<AdminPrivilege> findAdminPrivilegeCodeListByAdminRoleId(String id) throws YuleException {
		return this.adminPrivilegeDao.findAdminPrivilegeCodeListByAdminRoleId(id);
	}

	public Page<AdminPrivilege> findAdminPrivilegePageByParentId(String parentId,int pageSize,int pageNo) throws YuleException{
		Page<AdminPrivilege> page = new Page<AdminPrivilege>();
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("pageStart", (pageNo - 1) * pageSize);
		params.put("pageEnd", pageSize);
		params.put("parent_id", parentId);
		page.setDatas(this.adminPrivilegeDao.findAdminPrivilegeList(params));
		page.setRowCount(this.adminPrivilegeDao.findAdminPrivilegeCount(params));
		page.setPageSize(pageSize);
		page.setPageNo(pageNo);
		return page;
	}

	public boolean deleteAdminPrivilegeAll() throws YuleException {
		boolean flag = false;
	    try {
	    	this.adminPrivilegeDao.deleteAdminPrivilegeAll();
	    	this.adminRolePrivilegeDao.deleteAdminRolePrivilegeAll();
	    	flag = true;
	    } catch(Exception e) {
	    	throw new YuleException(e);
	    }
		return flag; 
	}

	public boolean batchInsertAdminPrivilege(List<AdminPrivilege> adminPrivileges) throws YuleException {
		boolean flag = false;
	    try {
	    	this.adminPrivilegeDao.batchInsertAdminPrivilege(adminPrivileges);
	    	flag = true;
	    } catch(Exception e) {
	    	throw new YuleException(e);
	    }
		return flag; 
	}

	public List<AdminPrivilege> findAdminPrivilegeListByParentId(String parentId)throws YuleException {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("parent_id", parentId);
		return this.adminPrivilegeDao.findAdminPrivilegeList(params);
	}


}
