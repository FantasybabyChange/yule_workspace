package com.yule.admin.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.yule.admin.dao.ICompanyDao;
import com.yule.admin.dao.ICompanyPrivilegeDao;
import com.yule.admin.dao.ICompanyUserPrivilegeDao;
import com.yule.admin.service.ICompanyPrivilegeService;
import com.yule.exception.YuleException;
import com.yule.pojo.Company;
import com.yule.pojo.CompanyPrivilege;
import com.yule.pojo.CompanyUserPrivilege;
import com.yule.util.IDUtil;
import com.yule.vo.CompanyUserPrivilegeVO;

@Service("companyPrivilegeServiceImpl")
public class CompanyPrivilegeServiceImpl implements ICompanyPrivilegeService {
	@Autowired
	private ICompanyPrivilegeDao companyPrivilegeDao;
	
	@Autowired
	private ICompanyDao companyDao;
	@Autowired
	private ICompanyUserPrivilegeDao companyUserPrivilegeDao;

	public boolean insertCompanyPrivilege(CompanyPrivilege companyPrivilege)throws YuleException {
		boolean flag =false;
		try {
			String id = IDUtil.getID();
			if(StringUtils.isEmpty(companyPrivilege.getId())) {
				companyPrivilege.setId(id);
			}
			if (StringUtils.isEmpty(companyPrivilege.getParent_id())) {
				companyPrivilege.setParent_id(null);
			}
			this.companyPrivilegeDao.insertCompanyPrivilege(companyPrivilege);
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("categoryId", companyPrivilege.getCompany_category_id());
			List<Company> companys = this.companyDao.findCompanyListByCategoryId(map);
			List<CompanyUserPrivilege> companyUserPrivileges = new ArrayList<CompanyUserPrivilege>(); 
			if (companys != null && companys.size() > 0) {
				for (Company company : companys) {
					CompanyUserPrivilege companyUserPrivilege = new CompanyUserPrivilege();
					companyUserPrivilege.setId(IDUtil.getID());
					companyUserPrivilege.setCompany_user_id(company.getId());
					companyUserPrivilege.setCompany_privilege_id(id);
					companyUserPrivileges.add(companyUserPrivilege);
				}
				companys.clear();
				companys = null;
			}
			if ( companyUserPrivileges.size() > 0) {
				this.companyUserPrivilegeDao.batchInsertCompanyUserPrivilege(companyUserPrivileges);
				companyUserPrivileges.clear();
			}
			companyUserPrivileges = null;
			flag = true;
		} catch (Exception e) {
	    	throw new YuleException(e);
	    }
		return flag;
	}

	public List<CompanyPrivilege> findCompanyPrivilegeList(String companyCategory_id,String parent_id) throws YuleException {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("company_category_id", companyCategory_id);
		map.put("parent_id", parent_id);
		return  this.companyPrivilegeDao.findCompanyPrivilegeList(map);
	}

	public boolean deleteCompanyPrivilegeById(String id) throws YuleException {
		boolean flag = false;
		try {
			List<CompanyPrivilege> privileIds = this.companyPrivilegeDao.findCompanyPrivilegesByParentId(id);
			if (privileIds != null && privileIds.size() > 0) {
				this.companyPrivilegeDao.deleteCompanyPrivivlegeById(privileIds);	
			}
			flag = true;
		} catch (Exception e) {
	    	throw new YuleException(e);
	    }
		return flag;
	}

	public List<CompanyUserPrivilegeVO> findCompanyPrivilegeVOList(String companyId,String userId)throws YuleException {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("company_id", companyId);
		map.put("user_id", userId);
		return this.companyPrivilegeDao.findCompanyPrivilegeVOList(map);
	}

	public CompanyPrivilege findCompanyPrivilegeById(String id)throws YuleException {
		return this.companyPrivilegeDao.findCompanyPrivilegeById(id);
		
	}
	public boolean batchInsertCompanyPrivilege(List<CompanyPrivilege> companyPrivileges) throws YuleException {
		boolean flag = false;
		try {
			if (companyPrivileges != null && companyPrivileges.size() > 0) {
				this.companyPrivilegeDao.batchInsertCompanyPrivilege(companyPrivileges);
				companyPrivileges.clear();
				companyPrivileges = null;
			}
			flag = true;
		} catch (Exception e) {
	    	throw new YuleException(e);
	    }
		return flag;
	}

	public boolean deleteCompanyPrivilegeByAll() throws YuleException {
		boolean flag = false;
		try {
			this.companyPrivilegeDao.deleteCompanyPrivivlegeByAll();
			flag = true;
		} catch (Exception e) {
	    	throw new YuleException(e);
	    }
		return flag;
	}

	public List<CompanyPrivilege> findCompanyPrivilegeListByCompanyUserId(String companyUserId, Integer isShow) throws YuleException {
		Map<String,Object> params = new HashMap<String, Object>();
		params.put("company_user_id", companyUserId);
		params.put("is_show", isShow);
		return  this.companyPrivilegeDao.findCompanyPrivilegeListByUserId(params);
	}
}
