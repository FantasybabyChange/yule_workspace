package com.yule.detail.dao;

import java.util.List;

import com.yule.exception.YuleException;
import com.yule.pojo.CompanyServe;

public interface ICompanyServeDao {
	public List<CompanyServe>findCompanyServeByCompanyId(String companyId) throws YuleException;
}
