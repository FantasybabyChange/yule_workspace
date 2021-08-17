package com.yule.admin.dao;

import java.util.List;
import java.util.Map;

import com.yule.exception.YuleException;
import com.yule.pojo.CompanyUser;

public interface ICompanyUserDao {
	
	public List<CompanyUser> findCompanyUserPage(Map<String, Object> params) throws YuleException;

	public int findCompanyUserCount(String companyId) throws YuleException;
	
	public int insertCompanyUser(CompanyUser companyUser) throws YuleException;
	
	public int findCompanyUserMap(Map<String, Object> map)throws YuleException ;
	
	public int findCompanyUserByAccount(String account)throws YuleException ;
	
	public int deleteCompanyUser(CompanyUser companyUser) throws YuleException;
	
	public CompanyUser findCompanyUserById(String id) throws YuleException;
	
	public int updateCompanyUser(CompanyUser companyUser) throws YuleException;
}
