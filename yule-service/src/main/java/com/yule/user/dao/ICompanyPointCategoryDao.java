package com.yule.user.dao;

import java.util.List;

import com.yule.exception.YuleException;
import com.yule.pojo.CompanyPointCategory;


public interface ICompanyPointCategoryDao {
	public List<CompanyPointCategory> findCompanyPointCategoryList() throws YuleException;
}