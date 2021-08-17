package com.yule.company.service;

import java.util.List;

import com.yule.exception.YuleException;
import com.yule.pojo.Company;
import com.yule.vo.CompanyVO;

public interface ICompanyService {
	
	public boolean updateCompany(Company company) throws YuleException,Exception;
	
	public CompanyVO findCompanyVOById(String id) throws YuleException;
	
	public List<Company> findCompanySame(String id) throws YuleException;
	
	public int findCompanyCountByMailOrPhone(Company company) throws YuleException,Exception;

	public int findCompanyAuthById(String id)throws YuleException;
	
}
