package com.yule.company.service;

import com.yule.company.query.CompanyUserQuery;
import com.yule.company.vo.CompanyUserVO;
import com.yule.exception.YuleException;
import com.yule.param.UpdateCompanyUserPrivilegeParam;
import com.yule.pojo.CompanyUser;
import com.yule.vo.Page;

public interface ICompanyUserService {
	
	public CompanyUserVO findCompanyUserVOByAccount(String account,int code)throws YuleException;
	
	public CompanyUser findCompanyUserById(String id)throws YuleException;
	
	public CompanyUserVO findCompanyUserVOById(String id) throws YuleException;
	
	public boolean updateCompanyUser(CompanyUser companyUser)throws YuleException;
	
	public Page<CompanyUser> findCompanyUserPage(CompanyUserQuery companyUserQuery,int pageSize,int pageNo)throws YuleException;
	
	public boolean updateCompanyUserPrivilege(UpdateCompanyUserPrivilegeParam updateCompanyUserPrivilegeParam) throws YuleException;
	
	public boolean insertCompanyUser(CompanyUser companyUser) throws YuleException;
	
	public boolean findCompanyUserByAccount(String account,String companyId)throws YuleException ;
	
}
