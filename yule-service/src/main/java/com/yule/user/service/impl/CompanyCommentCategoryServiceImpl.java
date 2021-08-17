package com.yule.user.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yule.exception.YuleException;
import com.yule.pojo.CompanyCommentCategory;
import com.yule.user.dao.ICompanyCommentCategoryDao;
import com.yule.user.service.ICompanyCommentCategoryService;

@Service("companyCommentCategoryServiceImpl")
public class CompanyCommentCategoryServiceImpl implements ICompanyCommentCategoryService{
	@Autowired
	private ICompanyCommentCategoryDao companyCommentCategoryDao;
	public List<CompanyCommentCategory> findCompanyCommentCategoryList()throws YuleException {
		return this.companyCommentCategoryDao.findCompanyCommentCategoryList();
	}
}
