package com.yule.company.dao;

import com.yule.exception.YuleException;
import com.yule.pojo.CompanyAddress;
import com.yule.vo.CompanyAddressVO;


public interface ICompanyAddressDao {

	public int updateCompanyAddress(CompanyAddress companyAddress) throws YuleException;
	
	public CompanyAddressVO findCompanyAddressVOById(String id) throws YuleException;
	
	public int insertCompanyAddress(CompanyAddress companyAddress)throws YuleException;
	
	public int findCompanyAddressAuthById(String id) throws YuleException;
	
}
