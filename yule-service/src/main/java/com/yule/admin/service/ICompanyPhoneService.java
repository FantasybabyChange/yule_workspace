package com.yule.admin.service;

import java.util.List;

import com.yule.admin.param.InsertCompanyPhoneParam;
import com.yule.exception.YuleException;
import com.yule.pojo.CompanyPhone;

public interface ICompanyPhoneService {

	public boolean insertCompanyPhone(CompanyPhone companyPhone) throws YuleException;

	public boolean updateCompanyPhone(CompanyPhone companyPhone) throws YuleException;

	public boolean deleteCompanyPhoneById(String id) throws YuleException;
	
	public List<CompanyPhone> findCompanyPhoneListByCompanyId(String companyId) throws YuleException;

	public boolean batchInsertAndUpdateCompanyPhone(InsertCompanyPhoneParam insertCompanyPhoneParam) throws YuleException;

}
