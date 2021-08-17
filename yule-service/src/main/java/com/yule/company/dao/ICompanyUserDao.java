package com.yule.company.dao;

import java.util.List;
import java.util.Map;

import com.yule.company.vo.CompanyUserVO;
import com.yule.exception.YuleException;
import com.yule.pojo.CompanyUser;

public interface ICompanyUserDao {
	
	public CompanyUserVO findCompanyUserVOByAccount(Map<String, Object> map)throws YuleException;
	
	public CompanyUser findCompanyUserById(String id)throws YuleException;
	
	public CompanyUserVO findCompanyUserVOById(String id)throws YuleException;
	
	public int updateCompanyUser(CompanyUser companyUser)throws YuleException;
	
	public List<CompanyUser> findCompanyUserList(Map<String, Object> map)throws YuleException;
	
	public int findCompanyUserCount(Map<String,Object> map)throws YuleException;
	
	public int insertCompanyUser(CompanyUser companyUser) throws YuleException;
	
	public int findCompanyUserMap(Map<String, Object> map)throws YuleException ;
}
