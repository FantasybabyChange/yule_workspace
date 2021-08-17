package com.yule.company.service;

import java.util.List;

import com.yule.exception.YuleException;
import com.yule.pojo.CompanyExpenseCategory;

public interface ICompanyExpenseCategoryService {

	public List<CompanyExpenseCategory> findCompanyExpenseList(String id)throws YuleException;

}
