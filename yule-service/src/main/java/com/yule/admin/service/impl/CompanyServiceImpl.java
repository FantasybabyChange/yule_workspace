package com.yule.admin.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.yule.admin.dao.ICompanyAddressDao;
import com.yule.admin.dao.ICompanyDao;
import com.yule.admin.dao.ICompanyEnterpriseDao;
import com.yule.admin.dao.ICompanyPrivilegeDao;
import com.yule.admin.dao.ICompanyUserDao;
import com.yule.admin.dao.ICompanyUserPrivilegeDao;
import com.yule.admin.param.InsertCompanyManagerParam;
import com.yule.admin.query.CompanyManagerQuery;
import com.yule.admin.service.ICompanyService;
import com.yule.constant.AuthConst;
import com.yule.constant.DeleteConst;
import com.yule.constant.StatusConst;
import com.yule.exception.YuleException;
import com.yule.pojo.Company;
import com.yule.pojo.CompanyAddress;
import com.yule.pojo.CompanyPrivilege;
import com.yule.pojo.CompanyUser;
import com.yule.pojo.CompanyUserPrivilege;
import com.yule.util.EncryptUtil;
import com.yule.util.IDUtil;
import com.yule.util.PinyinUtil;
import com.yule.vo.CompanyManagerVO;
import com.yule.vo.CompanyVO;
import com.yule.vo.Page;

@Service("companyServiceImpl")
public class CompanyServiceImpl implements ICompanyService {

	@Autowired
	private ICompanyDao companyDao;
	
	@Autowired
	private ICompanyAddressDao companyAddressDao;
	
	@Autowired
	private ICompanyPrivilegeDao companyPrivilegeDao;
	@Autowired
	private ICompanyUserDao companyUserDao;
	@Autowired
	private ICompanyEnterpriseDao companyEnterpriseDao;
	@Autowired
	private ICompanyUserPrivilegeDao companyUserPrivilegeDao;
	
	public Company findCompanyById(String id) throws YuleException {
		return this.companyDao.findCompanyById(id);
	}

	public boolean updateCompany(Company company) throws YuleException,Exception {
		boolean flag = false;
		try{
			companyDao.updateCompany(company);
			flag = true;
		} catch(Exception e) {
	    	throw new YuleException(e);
	    }
		return flag;
	}
	
	public boolean updateCompanyCategory(Company company) throws YuleException{
		boolean flag  = false;
		try {
			this.companyDao.updateCompanyCategory(company);
			flag=true;
		} catch(Exception e) {
	    	throw new YuleException(e);
	    }
		return flag;
	}
	public boolean updateCompanyInfo(Company company) throws YuleException{
		boolean flag  = false;
		try {
			company.setPinyin(PinyinUtil.getPinYin(company.getName()));
			this.companyDao.updateCompanyInfo(company);
			flag=true;
		} catch(Exception e) {
			throw new YuleException(e);
		}
		return flag;
	}
	public List<Company> findCompanyList() throws YuleException {
		return this.companyDao.findCompanyList(DeleteConst.IS_DELETE_TRUE);
	}
	
	public boolean findCompanyByName(String name) throws YuleException {
		boolean flag = false;
		try {
			int count = this.companyDao.findCompanyByName(name);
			if (count <= 0) {
				flag = true;
			}
		} catch(Exception e) {
	    	throw new YuleException(e);
	    }
		return flag;
	}

	public CompanyVO findCompanyVOById(String id) throws YuleException {
		return this.companyDao.findCompanyVOById(id);
	}

	public boolean insertCompanyManager(InsertCompanyManagerParam insertCompanyManagerParam)
			throws YuleException,Exception {
		boolean flag = false;
		try{
			String id = insertCompanyManagerParam.getId();
			String passWord = EncryptUtil.encryptToMD5(insertCompanyManagerParam.getPassword());
			if(StringUtils.isEmpty(id)){
				id = IDUtil.getID();
				insertCompanyManagerParam.setId(id);
			}
			String categoryId = insertCompanyManagerParam.getCompany_category_id();
			List<CompanyPrivilege> companyPrivileges = companyPrivilegeDao.findCompanyPrivilegeListByCategoryId(categoryId);
			//创建子用户
			CompanyUser companyUser = new CompanyUser();
			companyUser.setId(id);
			companyUser.setPassword(passWord);
			companyUser.setAccount(insertCompanyManagerParam.getAccount());
			companyUser.setCompany_id(id);
			companyUser.setStatus(StatusConst.STATUS_TRUE);
			companyUser.setIs_delete(DeleteConst.IS_DELETE_TRUE);
			companyUserDao.insertCompanyUser(companyUser);
			List<CompanyUserPrivilege> companyUserPrivileges = new ArrayList<CompanyUserPrivilege>(); 
			if (companyPrivileges != null && companyPrivileges.size() > 0) {
				for (CompanyPrivilege companyPrivilege : companyPrivileges) {
					CompanyUserPrivilege companyUserPrivilege = new CompanyUserPrivilege();
					companyUserPrivilege.setId(IDUtil.getID());
					companyUserPrivilege.setCompany_privilege_id(companyPrivilege.getId());
					companyUserPrivilege.setCompany_user_id(id);
					companyUserPrivileges.add(companyUserPrivilege);
				}
				companyUserPrivilegeDao.batchInsertCompanyUserPrivilege(companyUserPrivileges);
				companyPrivileges.clear();
			}
			companyPrivileges = null;
			companyUserPrivileges.clear();
			companyUserPrivileges = null;
			Company company = new Company();
			company.setId(id);
			company.setName(insertCompanyManagerParam.getName());
			company.setCompany_category_id(categoryId);
			company.setPinyin(PinyinUtil.getPinYin(company.getName()));
			company.setCommision(insertCompanyManagerParam.getCommision());
			company.setPay_type(insertCompanyManagerParam.getPay_type());
			company.setCompany_grade_id(insertCompanyManagerParam.getCompany_grade_id());
			company.setRebast(insertCompanyManagerParam.getRebast());
			company.setIs_delete(DeleteConst.IS_DELETE_TRUE);
			company.setIs_auth(AuthConst.IS_AUTH_FALSE);
			company.setCooperatory_type(insertCompanyManagerParam.getCooperatory_type());
			company.setSalesman_id(insertCompanyManagerParam.getSalesman_id());
			companyDao.insertCompany(company);
			flag = true;
		} catch(Exception e) {
	    	throw new YuleException(e);
	    }
		return flag;
	}

	public Page<CompanyManagerVO> findCompanyManagerVOPage(CompanyManagerQuery companyManagerQuery ,int pageSize, int pageNo)
			throws YuleException {
		Page<CompanyManagerVO> page = new Page<CompanyManagerVO>();
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("pageStart", (pageNo - 1) * pageSize);
		params.put("pageEnd", pageSize);
		if (null != companyManagerQuery) {
			params.put("account", companyManagerQuery.getAccount());
			params.put("name", companyManagerQuery.getName());
			params.put("start_time", companyManagerQuery.getStart_time());
			params.put("end_time", companyManagerQuery.getEnd_time());
			params.put("status", companyManagerQuery.getStatus());
			params.put("is_delete", companyManagerQuery.getIs_delete());
			params.put("company_grade_id", companyManagerQuery.getCompany_grade_id());
			params.put("company_category_id", companyManagerQuery.getCompany_category_id());
		}
		page.setDatas(this.companyDao.findCompanyManagerVOList(params));
		page.setRowCount(this.companyDao.findCompanyManagerVOCount(params));
		page.setPageSize(pageSize);
		page.setPageNo(pageNo);
		return page;
	}

	public boolean deleteCompanyManager(CompanyUser companyUser) throws YuleException {
		boolean flag = false;
		try{
			String id = companyUser.getId();
			int is_delete = companyUser.getIs_delete();
			companyUserDao.updateCompanyUser(companyUser);
			Company company = new Company();
			company.setId(id);
			company.setIs_delete(is_delete);
			companyDao.updateCompany(company);
			CompanyAddress companyAddress = new CompanyAddress();
			companyAddress.setId(id);
			companyAddress.setIs_delete(is_delete);
			companyAddressDao.updateCompanyAddress(companyAddress);
			flag = true;
		} catch(Exception e) {
	    	throw new YuleException(e);
	    }
		return flag;
	}
	
	public List<Company> findCompanyByCategoryId(String categoryID) throws YuleException{
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("categoryId", categoryID);
		return this.companyDao.findCompanyListByCategoryId(map);
	}

	public boolean updateCompanyOrder(Company company) throws YuleException {
		boolean flag = false;
		try{
			companyDao.updateCompanyOrder(company);
			flag = true;
		} catch(Exception e) {
	    	throw new YuleException(e);
	    }
		return flag;
	}
}
