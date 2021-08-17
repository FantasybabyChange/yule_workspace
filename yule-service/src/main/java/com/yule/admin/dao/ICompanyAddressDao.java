package com.yule.admin.dao;

import com.yule.exception.YuleException;
import com.yule.pojo.CompanyAddress;
import com.yule.vo.CompanyAddressVO;


public interface ICompanyAddressDao {

	public int insertCompanyAddress(CompanyAddress companyAddress) throws YuleException;
	
	public int updateCompanyAddress(CompanyAddress companyAddress) throws YuleException;
	
	public int deleteCompanyAddressById(String id) throws YuleException;

	public CompanyAddressVO findCompanyAddressVOById(String id) throws YuleException;

}
