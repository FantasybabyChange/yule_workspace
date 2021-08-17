package com.yule.salesman.service;

import java.util.List;

import com.yule.exception.YuleException;
import com.yule.pojo.Company;
import com.yule.salesman.query.CompanyManagerQuery;
import com.yule.vo.CompanyManagerVO;
import com.yule.vo.Page;

public interface ICompanyService {

	public List<Company> findCompanyList(String id) throws YuleException;
	
	public Page<CompanyManagerVO> findCompanyManagerVOPage(CompanyManagerQuery companyManagerQuery,int pageSize,int pageNo) throws YuleException;
}
