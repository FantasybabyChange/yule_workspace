package com.yule.company.service;

import com.yule.exception.YuleException;
import com.yule.pojo.CompanyAddress;
import com.yule.vo.CompanyAddressVO;

/**
 * 企业地址服务
 */
public interface ICompanyAddressService {

	public boolean updateCompanyAddress(CompanyAddress companyAddress) throws YuleException;

	public CompanyAddressVO findCompanyAddressVOById(String id) throws YuleException;
	
	public boolean updateCompanyAddress(CompanyAddress companyAddress,String companyId) throws YuleException;
	
	public int findCompanyAddressAuthById(String id)throws YuleException;

}
