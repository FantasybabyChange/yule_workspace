package com.yule.admin.service;


import com.yule.exception.YuleException;
import com.yule.param.UpdateCompanyUserPrivilegeParam;
import com.yule.pojo.CompanyUser;
import com.yule.vo.Page;

public interface ICompanyUserService {
	
	public boolean insertCompanyUser(CompanyUser companyUser) throws YuleException;

	public Page<CompanyUser> findCompanyUserPage(int pageNo,int pageSize,String companyId) throws YuleException ;
	
	public boolean findCompanyUserByAccount(String account,String companyId)throws YuleException ;
	
	public boolean findCompanyUserByAccount(String account)throws YuleException ;

	public boolean deleteCompanyUserById(CompanyUser companyUser)throws YuleException;
	
	public CompanyUser findCompanyUserById(String id) throws YuleException;
	
	public boolean updateCompanyUser(CompanyUser companyUser) throws YuleException;
	
	public boolean updateCompanyUserPrivilege(UpdateCompanyUserPrivilegeParam updateCompanyUserPrivilegeParam) throws YuleException;
}
