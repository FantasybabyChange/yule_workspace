package com.yule.company.dao;

import java.util.List;

import com.yule.exception.YuleException;
import com.yule.pojo.CompanyExpenseCategory;

public interface ICompanyExpenseCategoryDao {


	public List<CompanyExpenseCategory> findCompanyExpenseCategoryByParentId(String id)throws YuleException;
	
	public List<CompanyExpenseCategory> findCompanyExpenseCategory()throws YuleException;
}
