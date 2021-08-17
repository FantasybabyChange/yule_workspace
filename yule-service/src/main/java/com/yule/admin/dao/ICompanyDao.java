package com.yule.admin.dao;

import java.util.List;
import java.util.Map;

import com.yule.exception.YuleException;
import com.yule.pojo.Company;
import com.yule.vo.CompanyManagerVO;
import com.yule.vo.CompanyVO;

public interface ICompanyDao {
	
	public int insertCompany(Company company) throws YuleException;

	public int updateCompany(Company company) throws YuleException;
	
	public int updateCompanyCategory(Company company) throws YuleException;

	public int updateCompanyInfo(Company company) throws YuleException;

	public int deleteCompanyById(String id) throws YuleException;

	public Company findCompanyById(String id) throws YuleException;
	
	public int findCompanyByName(String name) throws YuleException;

	public CompanyVO findCompanyVOById(String id) throws YuleException;
	
	public List<Company> findCompanyListByCategoryId(Map<String, Object> map)throws YuleException;
	
	public List<Company> findCompanyListByPrivilegeId(String privilegeId)throws YuleException;
	
	public List<Company> findCompanyList(Integer is_delete) throws YuleException;
	
	public int updateCompanyOrder(Company company) throws YuleException;
	
	//---------------原CompanyManagerVO
	public List<CompanyManagerVO> findCompanyManagerVOList(Map<String,Object> params) throws YuleException;
	
	public int findCompanyManagerVOCount(Map<String,Object> params) throws YuleException;
	
	public int procInsertCompanyVo(Map<String,Object> params) throws YuleException;
}
