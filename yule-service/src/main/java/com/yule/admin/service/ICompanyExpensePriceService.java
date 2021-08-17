package com.yule.admin.service;


import com.yule.exception.YuleException;
import com.yule.pojo.CompanyExpensePrice;
import com.yule.vo.Page;

public interface ICompanyExpensePriceService {

	public boolean updateCompanyExpensePrice(CompanyExpensePrice companyExpensePrice) throws YuleException;
	
	public boolean insertCompanyExpensePrice(CompanyExpensePrice companyExpensePrice) throws YuleException;

	
	public boolean deleteCompanyExpensePriceById(String id) throws YuleException;
	
	public Page<CompanyExpensePrice> findCompanyExpensePricePage(String company_id,int pageSize,int pageNo) throws YuleException;
	
}
