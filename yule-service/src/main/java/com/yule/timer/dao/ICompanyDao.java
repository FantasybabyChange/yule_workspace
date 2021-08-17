package com.yule.timer.dao;

import java.util.List;
import java.util.Map;

import com.yule.exception.YuleException;
import com.yule.timer.vo.CompanyLuceneVO;
import com.yule.timer.vo.CompanySearchCriteriaLuceneVO;
import com.yule.timer.vo.CompanyTaskVO;
import com.yule.timer.vo.CompanyVO;

public interface ICompanyDao {
	
	public List<CompanyLuceneVO> findCompanyLuceneVOList() throws YuleException;
	
	public List<CompanyTaskVO> findCompanyTaskVOList() throws YuleException;
	
	public List<CompanyVO> findCompanyVOList(Map<String,Object> params)throws YuleException;
	
	public List<CompanySearchCriteriaLuceneVO> findCompanySearchCriteriaLuceneVOList() throws YuleException;
	
}
