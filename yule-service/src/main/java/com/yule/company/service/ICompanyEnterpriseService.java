package com.yule.company.service;

import com.yule.exception.YuleException;
import com.yule.pojo.CompanyEnterprise;

public interface ICompanyEnterpriseService {
	
	public boolean updateCompanyEnterprise(CompanyEnterprise companyEnterprise) throws YuleException,Exception;
	
}
