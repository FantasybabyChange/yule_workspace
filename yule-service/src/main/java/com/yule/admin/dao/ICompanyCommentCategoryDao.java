package com.yule.admin.dao;

import java.util.List;

import com.yule.exception.YuleException;
import com.yule.pojo.CompanyCommentCategory;
import com.yule.vo.CompanyCommentCategoryVO;

public interface ICompanyCommentCategoryDao {

	public List<CompanyCommentCategory> findCompanyCommentCategoryList()throws YuleException;

	public List<CompanyCommentCategoryVO> findCompanyCommentCategoryVOList()throws YuleException;
	
	public int findCompanyCommentCategoryCount()throws YuleException;
	
	public int insertCompanyCommentCategory(CompanyCommentCategory companyCommentCategory) throws YuleException;
	
	public int updateCompanyCommentCategory(CompanyCommentCategory companyCommentCategory) throws YuleException;

	public int deleteCompanyCommentCategoryById(String id) throws YuleException;
	
	public int deleteCompanyPointCategoryAll() throws YuleException;
	
	public int batchUpdateCompanyCommentCategory(List<CompanyCommentCategory> companyCommentCategories) throws YuleException;
	
	public int batchInsertCompanyCommentCategory(List<CompanyCommentCategory> companyCommentCategories) throws YuleException;
	
	
}
