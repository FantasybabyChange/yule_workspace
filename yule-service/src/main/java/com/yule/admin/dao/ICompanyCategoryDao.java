package com.yule.admin.dao;

import java.util.List;
import java.util.Map;

import com.yule.exception.YuleException;
import com.yule.pojo.CompanyCategory;
import com.yule.vo.CompanyCategoryCountVO;
import com.yule.vo.CompanyCategoryVO;

public interface ICompanyCategoryDao {
	
	public int insertCompanyCategory(CompanyCategory companyCategory) throws YuleException;

	public int updateCompanyCategory(CompanyCategory companyCategory) throws YuleException;

	public int deleteCompanyCategoryById(String id) throws YuleException;
	
	public int deleteCompanyCategoryAll() throws YuleException;

	public List<CompanyCategory> findCompanyCategoryList() throws YuleException;
	
	public List<CompanyCategoryVO> findCompanyCategoryVOList() throws YuleException;
	
	public int findCompanyCategoryCount() throws YuleException;
	
	public List<CompanyCategory> findCompanyCategoryList(Map<String,Object> params) throws YuleException;
	
	public int batchUpdateCompanyCategory(List<CompanyCategory> companyCategory) throws YuleException;

	public int batchInsertCompanyCategory(List<CompanyCategory> companyCategory) throws YuleException;
	
	public List<CompanyCategoryCountVO> findCompanyCategoryCountVOListByAreaProvinceId(String areaProvinceId) throws YuleException;
	
	public List<CompanyCategoryCountVO> findCompanyCategoryCountVOListByAreaCityId(String areaCityId) throws YuleException;

	public List<CompanyCategoryCountVO> findCompanyCategoryCountVOListByAreaCountyId(String areaCountId) throws YuleException;
	
	public List<CompanyCategoryCountVO> findCompanyCategoryCountVOListByAreaBusinessId(String areaBusinessId) throws YuleException;

}
