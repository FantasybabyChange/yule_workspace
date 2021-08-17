package com.yule.admin.service;

import java.util.List;

import com.yule.admin.param.InsertCompanyCommentCategoryParam;
import com.yule.exception.YuleException;
import com.yule.pojo.CompanyCommentCategory;
import com.yule.vo.CompanyCommentCategoryVO;

public interface ICompanyCommentCategoryService {
	
	public List<CompanyCommentCategory> findCompanyCommentCategoryList()throws YuleException;
	
	public List<CompanyCommentCategoryVO> findCompanyCommentCategoryVOList()throws YuleException;
	
	public boolean insertCompanyCommentCategory(CompanyCommentCategory companyCommentCategory) throws YuleException;
	
	public boolean updateCompanyCommentCategory(CompanyCommentCategory companyCommentCategory) throws YuleException;

	public boolean deleteCompanyCommentCategoryById(String id) throws YuleException;
	
	public boolean deleteCompanyPointCategoryAll() throws YuleException;
	
	public boolean batchInsertCompanyCommentCategory(List<CompanyCommentCategory> companyCommentCategories) throws YuleException;
	
	public boolean batchInsertAndUpdateCompanyCommentCategory(InsertCompanyCommentCategoryParam companyCommentCategoryParam) throws YuleException;
	
}
