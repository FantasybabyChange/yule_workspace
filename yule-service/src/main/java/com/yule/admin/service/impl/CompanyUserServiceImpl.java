package com.yule.admin.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.yule.admin.dao.ICompanyUserDao;
import com.yule.admin.dao.ICompanyUserPrivilegeDao;
import com.yule.admin.service.ICompanyUserService;
import com.yule.constant.DeleteConst;
import com.yule.constant.StatusConst;
import com.yule.exception.YuleException;
import com.yule.param.UpdateCompanyUserPrivilegeParam;
import com.yule.pojo.CompanyUser;
import com.yule.pojo.CompanyUserPrivilege;
import com.yule.util.EncryptUtil;
import com.yule.util.IDUtil;
import com.yule.vo.Page;
@Service("companyUserServiceImpl")
public class CompanyUserServiceImpl implements ICompanyUserService {
	@Autowired
	private ICompanyUserDao companyUserDao;
	
	@Autowired
	private ICompanyUserPrivilegeDao  companyUserPrivilegeDao;
	
	
	public boolean insertCompanyUser(CompanyUser companyUser) throws YuleException {
		boolean flag = false;
		try {
			companyUser.setPassword(EncryptUtil.encryptToMD5(companyUser.getPassword()));
			if(StringUtils.isEmpty(companyUser.getId())){
				companyUser.setId(IDUtil.getID());
			}
			companyUser.setIs_delete(DeleteConst.IS_DELETE_TRUE);
			companyUser.setStatus(StatusConst.STATUS_TRUE);
			this.companyUserDao.insertCompanyUser(companyUser);
			flag = true;
		} catch (Exception e) {
	    	throw new YuleException(e);
	    }
		return flag;
	}

	public Page<CompanyUser> findCompanyUserPage(int pageNo, int pageSize,String companyId)throws YuleException {
		Page<CompanyUser> companyUserPage = new Page<CompanyUser>();
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("pageStart", (pageNo - 1) * pageSize);
			params.put("pageEnd", pageSize);
			params.put("companyId", companyId);
			companyUserPage.setDatas(this.companyUserDao.findCompanyUserPage(params));
			companyUserPage.setPageCount(this.companyUserDao.findCompanyUserCount(companyId));
			companyUserPage.setPageNo(pageNo);
			companyUserPage.setPageSize(pageSize);
		} catch (Exception e) {
	    	throw new YuleException(e);
	    }
		return companyUserPage;
	}

	public boolean findCompanyUserByAccount(String account,String companyId)throws YuleException {
		boolean flag = false; 
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("account", account);
			map.put("companyId", companyId);
			int count = this.companyUserDao.findCompanyUserMap(map);
			if (count <= 0) {
				flag = true;
			}
		} catch (Exception e) {
	    	throw new YuleException(e);
	    }
		return flag;
	}

	public boolean deleteCompanyUserById(CompanyUser companyUser) throws YuleException {
		boolean flag = false;
		try {
			 this.companyUserDao.deleteCompanyUser(companyUser);
			 flag = true;
		} catch (Exception e) {
	    	throw new YuleException(e);
	    }
		return flag;
	}

	public CompanyUser findCompanyUserById(String id) throws YuleException {
		return this.companyUserDao.findCompanyUserById(id);
		
	}

	public boolean updateCompanyUser(CompanyUser companyUser) throws YuleException {
		boolean flag = false;
		try {
		if(null != companyUser.getPassword() && !companyUser.getPassword().equals("")){
			companyUser.setPassword(EncryptUtil.encryptToMD5(companyUser.getPassword()));
		}
			this.companyUserDao.updateCompanyUser(companyUser);
			flag =true;
		} catch (Exception e) {
	    	throw new YuleException(e);
	    }
		return flag;
	}

	public boolean findCompanyUserByAccount(String account)throws YuleException {
		boolean flag = false;
		try {
			int count = this.companyUserDao.findCompanyUserByAccount(account);
			if (count <= 0) {
				flag = true;
			}
		} catch (Exception e) {
	    	throw new YuleException(e);
	    }
		return flag;
	}
	
	public boolean updateCompanyUserPrivilege(UpdateCompanyUserPrivilegeParam updateCompanyUserPrivilegeParam) throws YuleException {
			boolean flag = false;
		try {
			List<String> company_privilege_id = updateCompanyUserPrivilegeParam.getCompany_privilege_id();
			Map<String,Object> params = new HashMap<String, Object>();
			params.put("companyUserId", updateCompanyUserPrivilegeParam.getCompany_user_id());
			if(null != company_privilege_id && company_privilege_id.size() > 0){
				CompanyUserPrivilege companyUserPrivilege = null;
				List<CompanyUserPrivilege> lists = new ArrayList<CompanyUserPrivilege>();
				for (String s : company_privilege_id) {
					companyUserPrivilege=new CompanyUserPrivilege();
					companyUserPrivilege.setCompany_privilege_id(s);
					companyUserPrivilege.setId(IDUtil.getID());
					lists.add(companyUserPrivilege);
				}
				params.put("companyUserPrivileges", lists);
				company_privilege_id.clear();
				company_privilege_id= null;
				}
			companyUserPrivilegeDao.updateCompanyUserPrivilege(params);
			params.clear();
			flag = true;
		} catch (Exception e) {
	    	throw new YuleException(e);
	    }
		return flag;
	}
}
