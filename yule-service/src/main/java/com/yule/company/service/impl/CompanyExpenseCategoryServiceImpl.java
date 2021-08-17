package com.yule.company.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yule.company.dao.ICompanyExpenseCategoryDao;
import com.yule.company.service.ICompanyExpenseCategoryService;
import com.yule.exception.YuleException;
import com.yule.pojo.CompanyExpenseCategory;

@Service("companyExpenseCategoryServiceImpl")
public class CompanyExpenseCategoryServiceImpl implements ICompanyExpenseCategoryService{

	@Autowired
	private ICompanyExpenseCategoryDao companyExpenseCategoryDao;
	
	public List<CompanyExpenseCategory> findCompanyExpenseList(String id)throws YuleException {
		if (null==id) {
			return companyExpenseCategoryDao.findCompanyExpenseCategory();
		}
		return companyExpenseCategoryDao.findCompanyExpenseCategoryByParentId(id);
	}


}
