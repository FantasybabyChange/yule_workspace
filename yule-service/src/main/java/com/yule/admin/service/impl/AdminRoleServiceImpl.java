package com.yule.admin.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.yule.admin.dao.IAdminRoleDao;
import com.yule.admin.dao.IAdminRolePrivilegeDao;
import com.yule.admin.param.InsertAdminRoleParam;
import com.yule.admin.param.UpdateAdminRolePrivilegeParam;
import com.yule.admin.service.IAdminRoleService;
import com.yule.admin.vo.AdminRoleVO;
import com.yule.constant.AdminConst;
import com.yule.constant.DeleteConst;
import com.yule.exception.YuleException;
import com.yule.pojo.AdminRole;
import com.yule.pojo.AdminRolePrivilege;
import com.yule.util.IDUtil;

@Service("adminRoleServiceImpl")
public class AdminRoleServiceImpl implements IAdminRoleService {

	@Autowired
	private IAdminRoleDao adminRoleDao;

	@Autowired
	private IAdminRolePrivilegeDao adminRolePrivilegeDao;
	
	public boolean insertAdminRole(AdminRole adminRole) throws YuleException {
		boolean flag = false;
		try {
			if(StringUtils.isEmpty(adminRole.getId())){
				adminRole.setId(IDUtil.getID());
			}
			adminRole.setIs_delete(DeleteConst.IS_DELETE_TRUE);
			this.adminRoleDao.insertAdminRole(adminRole);
			flag = true;
		} catch (Exception e) {
	    	throw new YuleException(e);
	    }
		return flag;
	}
	
	public boolean updateAdminRole(AdminRole adminRole) throws YuleException {
		boolean flag = false;
		try {
			this.adminRoleDao.updateAdminRole(adminRole);
			flag = true;
		} catch (Exception e) {
	    	throw new YuleException(e);
	    }
		return flag;
	}

	public boolean deleteAdminRoleById(String id) throws YuleException {
		boolean flag = false;
		try {
			this.adminRoleDao.deleteAdminRoleById(id);
			flag = true;
		} catch (Exception e) {
			throw new YuleException(e);
		}
		return flag;
	}

	public AdminRole findAdminRoleById(String id)throws YuleException {
		return this.adminRoleDao.findAdminRoleById(id);
	}

	public List<AdminRole> findAdminRoleList()throws YuleException {
		return this.adminRoleDao.findAdminRoleList();
	}

	public int findAdminRoleCount()throws YuleException {
		return this.adminRoleDao.findAdminRoleCount();
	}

	public boolean deleteAdminRoleAll() throws YuleException {
		boolean flag = false;
	    try {
	    	this.adminRoleDao.deleteAdminRoleAll();
	    	flag = true;
	    } catch (Exception e) {
			throw new YuleException(e);
		}
		return flag; 
	}

	public List<AdminRole> findAdminRoleByAdminUserId(String id) throws YuleException{
		return this.adminRoleDao.findAdminRoleByAdminUserId(id);
	}
	
	public boolean findAdminRoleIsAdminByAdminUserId(String adminUserId) throws YuleException{
		int num = this.adminRoleDao.findAdminRoleIsAdminByAdminUserId(adminUserId);
		if(num>0){
			return true;
		}
		return false;
	}

	public List<AdminRoleVO> findAdminRoleVOByAdminUserId(String id)
			throws YuleException {
		return this.adminRoleDao.findAdminRoleVOByAdminUserId(id);
	}

	public boolean batchInsertAndUpdateAdminRole(InsertAdminRoleParam insertAdminRoleParam)
			throws YuleException {
		boolean flag =false;
		try {
			List<String> lists = insertAdminRoleParam.getId();
			if(null != lists && lists.size()>0){
				List<AdminRole> insertAdminRoles = new ArrayList<AdminRole>();
				List<AdminRole> updateAdminRoles = new ArrayList<AdminRole>();
				List<String> names = insertAdminRoleParam.getName();
				int i =0;
				AdminRole adminRole = null;
				for (String id :lists) {
					adminRole = new AdminRole();
					adminRole.setName(names.get(i));
					if(StringUtils.isEmpty(id)){
						adminRole.setId(IDUtil.getID());
						adminRole.setIs_delete(DeleteConst.IS_DELETE_TRUE);
						adminRole.setIs_admin(AdminConst.IS_ADMIN_FALSE);
						insertAdminRoles.add(adminRole);
					}else{
						adminRole.setId(id);
						updateAdminRoles.add(adminRole);
					}
					i++;
				}
				lists.clear();
				names.clear();
				lists = null;
				names = null;
				if(insertAdminRoles.size()>0){
					   this.adminRoleDao.batchInsertAdminRole(insertAdminRoles);	
					   insertAdminRoles.clear();
				}
				insertAdminRoles=null;
				if(updateAdminRoles.size()>0){
					   this.adminRoleDao.batchUpdateAdminRole(updateAdminRoles);	
					   updateAdminRoles.clear();
				}
				updateAdminRoles=null;
				flag = true;
			}
			insertAdminRoleParam=null;
		} catch (Exception e) {
			throw new YuleException(e);
		}
		return flag;
	}
	
	public boolean updateAdminRolePrivilege(UpdateAdminRolePrivilegeParam updateAdminRolePrivilegeParam) throws YuleException {
		boolean flag = false;
		try {
			Map<String,Object> params = new HashMap<String, Object>();
			String adminRoleId = updateAdminRolePrivilegeParam.getAdmin_role_id();
			params.put("adminRoleId", adminRoleId);
			List<String> adminPrivilegeIds = updateAdminRolePrivilegeParam.getAdmin_privilege_id();
			if(null!=adminPrivilegeIds&&adminPrivilegeIds.size()>0){
				List<AdminRolePrivilege> lists = new ArrayList<AdminRolePrivilege>();
				AdminRolePrivilege adminRolePrivilege = null;
				for (String s : adminPrivilegeIds) {
					adminRolePrivilege=new AdminRolePrivilege();
					adminRolePrivilege.setAdmin_privilege_id(s);
					adminRolePrivilege.setId(IDUtil.getID());
					lists.add(adminRolePrivilege);
				}
				params.put("adminRolePrivileges", lists);
				adminPrivilegeIds.clear();
				adminPrivilegeIds= null;
				adminRolePrivilegeDao.updateAdminRolePrivilege(params);
				lists.clear();
				lists = null;
			}
			params.clear();
			flag = true;
		} catch (Exception e) {
			throw new YuleException(e);
		}
		return flag;
	}
	
	public boolean batchInsertAdminRolePrivilege(List<AdminRolePrivilege> adminRolePrivileges) throws YuleException {
		boolean flag = false;
	    try {
	    	adminRolePrivilegeDao.batchInsertAdminRolePrivilege(adminRolePrivileges);
	    	flag = true;
	    } catch(Exception e) {
	    	throw new YuleException(e);
	    }
		return flag; 
		
	}
	
	public List<AdminRolePrivilege> findAdminRoleIdListByAdminPrivilegeId(String adminPrivilegeId) throws YuleException {
		return adminRolePrivilegeDao.findAdminRoleIdListByAdminPrivilegeId(adminPrivilegeId);
	}

}
