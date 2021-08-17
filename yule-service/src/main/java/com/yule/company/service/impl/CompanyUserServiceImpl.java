package com.yule.company.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.druid.util.StringUtils;
import com.yule.company.dao.ICompanyUserDao;
import com.yule.company.dao.ICompanyUserPrivilegeDao;
import com.yule.company.query.CompanyUserQuery;
import com.yule.company.service.ICompanyUserService;
import com.yule.company.vo.CompanyUserVO;
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

	public CompanyUserVO findCompanyUserVOByAccount(String account,int code)throws YuleException {
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("account", account);
			map.put("code", code);
			return this.companyUserDao.findCompanyUserVOByAccount(map);
		} catch (Exception e) {
	    	throw new YuleException(e);
	    }
	}

	public CompanyUser findCompanyUserById(String id) throws YuleException {
		return this.companyUserDao.findCompanyUserById(id);
	}
	public CompanyUserVO findCompanyUserVOById(String id) throws YuleException {
		return this.companyUserDao.findCompanyUserVOById(id);
	}
	public boolean updateCompanyUser(CompanyUser companyUser) throws YuleException {
		boolean flag = false;
		try{
			if (!StringUtils.isEmpty(companyUser.getPassword())) {
			companyUser.setPassword(EncryptUtil.encryptToMD5(companyUser.getPassword()));
			}
			this.companyUserDao.updateCompanyUser(companyUser);
			flag = true;
		} catch(Exception e) {
	    	throw new YuleException(e);
	    }
		return flag;
	}
	public Page<CompanyUser> findCompanyUserPage(CompanyUserQuery companyUserQuery,int pageSize,int pageNo)throws YuleException{
		Page<CompanyUser> page = new Page<CompanyUser>();
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("pageStart", (pageNo - 1) * pageSize);
		params.put("pageEnd", pageSize);
		if (null != companyUserQuery) {
			params.put("account", companyUserQuery.getAccount());
			params.put("company_id", companyUserQuery.getCompany_id());
			params.put("start_time", companyUserQuery.getStart_time());
			params.put("end_time", companyUserQuery.getEnd_time());
			params.put("status", companyUserQuery.getStatus());
		}
		page.setDatas(this.companyUserDao.findCompanyUserList(params));
		page.setRowCount(this.companyUserDao.findCompanyUserCount(params));
		page.setPageSize(pageSize);
		page.setPageNo(pageNo);
		return page;
	}
	public boolean updateCompanyUserPrivilege(UpdateCompanyUserPrivilegeParam updateCompanyUserPrivilegeParam) throws YuleException {
		boolean flag = false;
		try {
			Map<String,Object> params = new HashMap<String, Object>();
			String companyUserId = updateCompanyUserPrivilegeParam.getCompany_user_id();
			params.put("companyUserId", companyUserId);
			List<String> companyPrivilegeIds = updateCompanyUserPrivilegeParam.getCompany_privilege_id();
			List<CompanyUserPrivilege> lists = new ArrayList<CompanyUserPrivilege>();
			if(null!=companyPrivilegeIds&&companyPrivilegeIds.size()>0){
				CompanyUserPrivilege companyUserPrivilege = null;
				for (String s : companyPrivilegeIds) {
					companyUserPrivilege=new CompanyUserPrivilege();
					companyUserPrivilege.setCompany_privilege_id(s);
					companyUserPrivilege.setId(IDUtil.getID());
					lists.add(companyUserPrivilege);
				}
				params.put("companyUserPrivileges", lists);
				companyPrivilegeIds.clear();
				companyPrivilegeIds= null;
			}
			companyUserPrivilegeDao.updateCompanyUserPrivilege(params);
			lists.clear();
			lists = null;
			params.clear();
			flag = true;
		} catch (Exception e) {
	    	throw new YuleException(e);
	    }
		return flag;
	}

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

	public boolean findCompanyUserByAccount(String account, String companyId)
			throws YuleException {
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
	
}
