package com.yule.weixin.dao;

import com.yule.exception.YuleException;
import com.yule.pojo.Company;

public interface ICompanyDao {
	
	public int updateCompany(Company company) throws YuleException;
	
}