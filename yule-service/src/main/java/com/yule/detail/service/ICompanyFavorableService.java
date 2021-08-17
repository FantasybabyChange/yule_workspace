package com.yule.detail.service;

import java.util.List;

import com.yule.exception.YuleException;
import com.yule.pojo.CompanyFavorable;

public interface ICompanyFavorableService {
	public List<CompanyFavorable> findCompanyFavorableByCompanyId(String companyId)throws YuleException;
}
