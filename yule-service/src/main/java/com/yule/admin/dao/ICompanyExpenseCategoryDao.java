package com.yule.admin.dao;

import java.util.List;
import java.util.Map;

import com.yule.exception.YuleException;
import com.yule.pojo.CompanyExpenseCategory;

public interface ICompanyExpenseCategoryDao {

	public int insertCompanyExpenseCategory(CompanyExpenseCategory companyExpenseCategory) throws YuleException;
	
	public int updateCompanyExpenseCategory(CompanyExpenseCategory companyExpenseCategory)throws YuleException;

	public List<CompanyExpenseCategory> findCompanyExpenseCategoryByParentId(Map<String, Object> params)throws YuleException;

	public int findCompanyExpenseCategoryCountByParentId(String id)throws YuleException;
	
	public List<CompanyExpenseCategory> findCompanyExpenseCategory(Map<String, Object> params)throws YuleException;
	
	public int findCompanyExpenseCategoryCount()throws YuleException;

	public int deleteALL() throws YuleException;

	public int batchInsertCompanyExpenseCategory(List<CompanyExpenseCategory> companyExpenseCategories) throws YuleException;
}
