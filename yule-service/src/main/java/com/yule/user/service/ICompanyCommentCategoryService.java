package com.yule.user.service;

import java.util.List;

import com.yule.exception.YuleException;
import com.yule.pojo.CompanyCommentCategory;

public interface ICompanyCommentCategoryService {
	
	public List<CompanyCommentCategory> findCompanyCommentCategoryList()throws YuleException;
}
