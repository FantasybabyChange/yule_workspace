package com.yule.company.dao;

import java.util.List;
import java.util.Map;

import com.yule.exception.YuleException;
import com.yule.pojo.Company;
import com.yule.vo.CompanyVO;

public interface ICompanyDao {
	
	public int updateCompany(Company company) throws YuleException;

	public CompanyVO findCompanyVOById(String id) throws YuleException;
	
	public List<Company> findCompanySame(String id) throws YuleException;
	
	public int findCompanyCount(Map<String,Object> params) throws YuleException;
	
	public int findCompanyAuthById(String id) throws YuleException;
}
