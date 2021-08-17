package com.yule.admin.service;

import java.util.List;

import com.yule.admin.param.InsertCompanyPointCategoryParam;
import com.yule.exception.YuleException;
import com.yule.pojo.CompanyPointCategory;
import com.yule.vo.CompanyPointCategoryVO;
public interface ICompanyPointCategoryService {

	public boolean insertCompanyPointCategory(CompanyPointCategory companyPointCategory) throws YuleException;

	public boolean updateCompanyPointCategory(CompanyPointCategory companyPointCategory) throws YuleException;

	public boolean deleteCompanyPointCategoryById(String id) throws YuleException;
	
	public boolean deleteCompanyPointCategoryAll() throws YuleException;

	public List<CompanyPointCategory> findCompanyPointCategoryList()throws YuleException;
	
	public List<CompanyPointCategoryVO> findCompanyPointCategoryVOList()throws YuleException;

	public boolean batchInsertCompanyPointCategory(List<CompanyPointCategory> companyPointCategories)throws YuleException;
	/**
	 * 批量新增并更新
	 */
	public boolean batchInsertAndUpdateCompanyPointCategory(InsertCompanyPointCategoryParam insertCompanyPointCategoryParam) throws YuleException;
	
}
