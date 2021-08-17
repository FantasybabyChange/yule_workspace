package com.yule.admin.service;

import java.util.List;

import com.yule.admin.param.InsertCompanyCategoryParam;
import com.yule.exception.YuleException;
import com.yule.pojo.CompanyCategory;
import com.yule.vo.CompanyCategoryCountVO;
import com.yule.vo.CompanyCategoryVO;

public interface ICompanyCategoryService {
	public boolean insertCompanyCategory(CompanyCategory companyCategory) throws YuleException;

	public boolean updateCompanyCategory(CompanyCategory companyCategory) throws YuleException;

	public boolean deleteCompanyCategoryById(String id) throws YuleException;
	
	public boolean deleteCompanyCategoryAll() throws YuleException;

	public List<CompanyCategoryVO> findCompanyCategoryVOList() throws YuleException;
	
	public List<CompanyCategory> findCompanyCategoryList() throws YuleException;
	
	public boolean batchInsertCompanyCategory(List<CompanyCategory> companyCategorys) throws YuleException;
	
	public boolean batchInsertAndUpdateCompanyCategory(InsertCompanyCategoryParam insertCompanyCategoryParam) throws YuleException;
	
	public List<CompanyCategoryCountVO> findCompanyCategoryCountVOListByAreaProvinceId(String areaProvinceId) throws YuleException;
	
	public List<CompanyCategoryCountVO> findCompanyCategoryCountVOListByAreaCityId(String areaCityId) throws YuleException;

	public List<CompanyCategoryCountVO> findCompanyCategoryCountVOListByAreaCountyId(String areaCountId) throws YuleException;
	
	public List<CompanyCategoryCountVO> findCompanyCategoryCountVOListByAreaBusinessId(String areaBusinessId) throws YuleException;
	
}
