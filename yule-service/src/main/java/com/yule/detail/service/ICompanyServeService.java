package com.yule.detail.service;

import java.util.List;

import com.yule.exception.YuleException;
import com.yule.pojo.CompanyServe;

public interface ICompanyServeService {
	public List<CompanyServe> findCompanyServeByCompanyId(String companyId)throws YuleException;
}
