package com.yule.admin.service;

import java.util.List;

import com.yule.admin.param.InsertCompanyManagerParam;
import com.yule.admin.query.CompanyManagerQuery;
import com.yule.exception.YuleException;
import com.yule.pojo.Company;
import com.yule.pojo.CompanyUser;
import com.yule.vo.CompanyManagerVO;
import com.yule.vo.CompanyVO;
import com.yule.vo.Page;

/**
 * 企业服务
 */
public interface ICompanyService {
	
	public boolean updateCompany(Company company) throws YuleException,Exception;

	public boolean updateCompanyInfo(Company company) throws YuleException;

	public boolean updateCompanyCategory(Company company) throws YuleException;
	
	public Company findCompanyById(String id) throws YuleException;
	
	public boolean findCompanyByName(String name) throws YuleException ;
	
	public CompanyVO findCompanyVOById(String id) throws YuleException;
	
	public List<Company> findCompanyList() throws YuleException;
	
	public boolean deleteCompanyManager(CompanyUser companyUser) throws YuleException;
	
	public boolean insertCompanyManager(InsertCompanyManagerParam insertCompanyManagerParam) throws YuleException,Exception;

	public Page<CompanyManagerVO> findCompanyManagerVOPage(CompanyManagerQuery companyManagerQuery,int pageSize,int pageNo) throws YuleException;
	
	public List<Company> findCompanyByCategoryId(String categoryID) throws YuleException;
	
	public boolean updateCompanyOrder(Company company) throws YuleException;
}
