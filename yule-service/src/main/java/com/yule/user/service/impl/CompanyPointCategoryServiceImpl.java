package com.yule.user.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yule.exception.YuleException;
import com.yule.pojo.CompanyPointCategory;
import com.yule.user.dao.ICompanyPointCategoryDao;
import com.yule.user.service.ICompanyPointCategoryService;


@Service("companyPointCategoryServiceImpl")
public class CompanyPointCategoryServiceImpl implements ICompanyPointCategoryService {
	@Autowired
	private ICompanyPointCategoryDao companyPointCategoryDao;
	public List<CompanyPointCategory> findCompanyPointCategoryList() throws YuleException {
		return this.companyPointCategoryDao.findCompanyPointCategoryList();
	}
	
}
