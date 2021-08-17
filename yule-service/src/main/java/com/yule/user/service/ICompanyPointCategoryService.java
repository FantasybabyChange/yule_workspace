package com.yule.user.service;

import java.util.List;

import com.yule.exception.YuleException;
import com.yule.pojo.CompanyPointCategory;
public interface ICompanyPointCategoryService {
	public List<CompanyPointCategory> findCompanyPointCategoryList()throws YuleException;
}
