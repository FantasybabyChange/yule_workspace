package com.yule.admin.dao;

import java.util.List;
import java.util.Map;

import com.yule.exception.YuleException;
import com.yule.pojo.CompanyServe;
import com.yule.vo.CompanyServeVO;

public interface ICompanyServeDao {
	
	public int insertCompanyServe(CompanyServe companyServe) throws YuleException;

	public int updateCompanyServe(CompanyServe companyServe) throws YuleException;

	public int deleteCompanyServeById(String id) throws YuleException;
	
	public int deleteCompanyServeAll() throws YuleException;
	
	public List<CompanyServe> findCompanyServeCheckList(String companyId) throws YuleException;
	
	public List<CompanyServe> findCompanyServeList() throws YuleException;
	
	public List<CompanyServeVO> findCompanyAndServeList(Map<String,Object> params) throws YuleException;
	
	public int findCompanyServeCount() throws YuleException;
	
	public int batchInsertCompanyServe(List<CompanyServe> companyServe) throws YuleException;
	
	public int batchUpdateCompanyServe(List<CompanyServe> companyServe) throws YuleException;
}
