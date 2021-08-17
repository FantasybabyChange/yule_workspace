package com.yule.admin.dao;

import com.yule.exception.YuleException;
import com.yule.pojo.CompanyEnterprise;

public interface ICompanyEnterpriseDao {
	
	public int insertCompanyEnterprise(CompanyEnterprise companyEnterprise) throws YuleException;
	
	public int deleteCompanyEnterprise(String id) throws YuleException;
	
	public int updateCompanyEnterprise(CompanyEnterprise companyEnterprise) throws YuleException;
}
