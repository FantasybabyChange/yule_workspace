package com.yule.mobile.dao;

import java.util.List;
import java.util.Map;

import com.yule.mobile.vo.CompanyVO;
import com.yule.exception.YuleException;
import com.yule.pojo.Company;

public interface ICompanyDao {
	public CompanyVO findCompanyVOById(String id) throws YuleException;
	
	public Company findCompanyById(String id) throws YuleException;
	
	public List<Company> findCompanys(Map<String, Object> map) throws YuleException;
}
