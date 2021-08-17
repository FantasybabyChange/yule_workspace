package com.yule.admin.service;

import java.util.List;

import com.yule.exception.YuleException;
import com.yule.pojo.CompanyExpenseCategory;
import com.yule.vo.Page;

public interface ICompanyExpenseCategoryService {

	public boolean insertCompanyExpenseCategory(CompanyExpenseCategory category)throws YuleException;
	
	public boolean updateCompanyExpenseCategory(CompanyExpenseCategory category)throws YuleException;

	public Page<CompanyExpenseCategory> findCompanyExpenseCategoryByParentId(String id,int pageSize, int pageNo)throws YuleException;

	public List<CompanyExpenseCategory> findCompanyExpenseList()throws YuleException;

	public boolean deleteALL() throws YuleException;

	public boolean batchInsertCompanyExpenseCategory(List<CompanyExpenseCategory> companyExpenseCategories) throws YuleException;

}
