package com.yule.admin.dao;

import java.util.List;
import java.util.Map;

import com.yule.exception.YuleException;
import com.yule.pojo.CompanyExpense;

public interface ICompanyExpenseDao {

	public List<CompanyExpense> findCompanyExpenseList(Map<String, Object> params) throws YuleException;

	public int findCompanyExpenseCount(Map<String, Object> params) throws YuleException;

	public int updateCompanyExpense(CompanyExpense companyExpense) throws YuleException;
	
	public int insertCompanyExpense(CompanyExpense companyExpense) throws YuleException;

	public int deleteCompanyExpense(String id) throws YuleException;
	
}
