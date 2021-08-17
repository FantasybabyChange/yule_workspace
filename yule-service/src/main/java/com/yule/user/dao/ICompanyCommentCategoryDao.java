package com.yule.user.dao;

import java.util.List;

import com.yule.exception.YuleException;
import com.yule.pojo.CompanyCommentCategory;

public interface ICompanyCommentCategoryDao {
	
	public List<CompanyCommentCategory> findCompanyCommentCategoryList()throws YuleException;
	
}
