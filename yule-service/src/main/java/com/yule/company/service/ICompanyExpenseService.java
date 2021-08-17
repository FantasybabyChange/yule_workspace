package com.yule.company.service;

import com.yule.company.query.CompanyExpenseQuery;
import com.yule.exception.YuleException;
import com.yule.pojo.CompanyExpense;
import com.yule.vo.Page;

public interface ICompanyExpenseService {
	
	public boolean updateCompanyExpense(CompanyExpense companyExpense) throws YuleException;
	
	public boolean insertCompanyExpense(CompanyExpense companyExpense) throws YuleException;

	public boolean deleteCompanyExpense(String id) throws YuleException;
	
	public Page<CompanyExpense> findCompanyExpensePage(CompanyExpenseQuery companyExpenseQuery,int pageSize, int pageNo) throws YuleException;

}
