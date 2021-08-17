package com.yule.detail.dao;

import java.util.List;
import java.util.Map;

import com.yule.detail.vo.CompanyCountVO;
import com.yule.detail.vo.CompanyDistanceVO;
import com.yule.detail.vo.CompanyVO;
import com.yule.exception.YuleException;
import com.yule.pojo.Company;
/**
 * 
 */
public interface ICompanyDao {
	public CompanyVO findCompanyVOById(String id) throws YuleException;
	
	public List<Company> findOtherCompanyById(Map<String, Object> map) throws YuleException;
	
	public CompanyCountVO findCompanyCountVO(Map<String, Object> map) throws YuleException;
	
	public List<CompanyDistanceVO> findCompanyDistanceVO(Map<String, Object> map)throws YuleException;
	
	
}
