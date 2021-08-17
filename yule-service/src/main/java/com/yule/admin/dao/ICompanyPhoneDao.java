package com.yule.admin.dao;

import java.util.List;
import java.util.Map;

import com.yule.exception.YuleException;
import com.yule.pojo.CompanyPhone;


public interface ICompanyPhoneDao {
	
	public int insertCompanyPhone(CompanyPhone companyPhone) throws YuleException;

	public int updateCompanyPhone(CompanyPhone companyPhone) throws YuleException;

	public int deleteCompanyPhoneById(String id) throws YuleException;
	
	public List<CompanyPhone> findCompanyPhoneList(Map<String,Object> param) throws YuleException;
	
	public int batchInsertCompanyPhone(List<CompanyPhone> companyPhone) throws YuleException;
	
	public int batchUpdateCompanyPhone(List<CompanyPhone> companyPhone) throws YuleException;

	
}
