package com.yule.company.service;

import java.util.List;

import com.yule.exception.YuleException;
import com.yule.param.InsertCompanyFavorableParam;
import com.yule.pojo.CompanyFavorable;

public interface ICompanyFavorableService {
	
	public boolean batchUpdateCompanyFavorable(InsertCompanyFavorableParam insertCompanyFavorableParam) throws YuleException;
   
	public List<CompanyFavorable> findCompanyFavorableByCompanyId(String companyId) throws YuleException;
	
	public boolean deleteCompanyFavorableById(String id) throws YuleException;
	
	public boolean updateCompanyFavorable(CompanyFavorable companyFavorable) throws YuleException;
	
	public boolean insertCompanyFavorable(CompanyFavorable companyFavorable) throws YuleException;
	
}
