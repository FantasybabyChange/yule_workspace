package com.yule.mobile.service;

import java.util.List;

import com.yule.mobile.vo.CompanyVO;
import com.yule.exception.YuleException;
import com.yule.pojo.Company;


public interface ICompanyService {
	public Company findCompanyById(String companyId)throws Exception;
	
	public List<Company> findCompanyList(int pageSize,int pageNo)throws Exception;
	
	public CompanyVO findCompanyVOById(String id)throws YuleException;
}
