package com.yule.admin.dao;

import java.util.List;

import com.yule.exception.YuleException;
import com.yule.pojo.CompanyPointCategory;
import com.yule.vo.CompanyPointCategoryVO;


public interface ICompanyPointCategoryDao {

	public int insertCompanyPointCategory(CompanyPointCategory companyPointCategory) throws YuleException;
	
	public int updateCompanyPointCategory(CompanyPointCategory companyPointCategory) throws YuleException;
	
	public int deleteCompanyPointCategoryById(String id) throws YuleException;
	
	public int deleteCompanyPointCategoryAll() throws YuleException;

	public List<CompanyPointCategory> findCompanyPointCategoryList() throws YuleException;
	
	public List<CompanyPointCategoryVO> findCompanyPointCategoryVOList() throws YuleException;
	
	public boolean batchUpdateCompanyPointCategory(List<CompanyPointCategory> companyPointCategories)throws YuleException;
	
	public boolean batchInsertCompanyPointCategory(List<CompanyPointCategory> companyPointCategories)throws YuleException;
}
