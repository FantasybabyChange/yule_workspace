package com.yule.salesman.dao;

import java.util.List;
import java.util.Map;

import com.yule.exception.YuleException;
import com.yule.pojo.Company;
import com.yule.vo.CompanyManagerVO;

public interface ICompanyDao {

	public List<Company> findCompanyList(String id) throws YuleException;
	
	public List<CompanyManagerVO> findCompanyManagerVOList(Map<String,Object> params) throws YuleException;
	
	public int findCompanyManagerVOCount(Map<String,Object> params) throws YuleException;
}
