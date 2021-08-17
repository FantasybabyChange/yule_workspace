package com.yule.company.dao;

import java.util.List;

import com.yule.exception.YuleException;
import com.yule.pojo.CompanyPhone;


public interface ICompanyPhoneDao {
	
	public int insertCompanyPhone(CompanyPhone companyPhone) throws YuleException;

	public int updateCompanyPhone(CompanyPhone companyPhone) throws YuleException;

	public int deleteCompanyPhoneById(String id) throws YuleException;
	
	public List<CompanyPhone> findCompanyPhoneList(String companyId) throws YuleException;
	
	public int batchUpdateCompanyPhone(List<CompanyPhone> companyPhone) throws YuleException;

	
}
