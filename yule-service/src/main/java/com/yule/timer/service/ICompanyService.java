package com.yule.timer.service;

import java.util.List;

import com.yule.exception.YuleException;
import com.yule.timer.vo.CompanyLuceneVO;
import com.yule.timer.vo.CompanySearchCriteriaLuceneVO;
import com.yule.timer.vo.CompanyTaskVO;
import com.yule.timer.vo.CompanyVO;

public interface ICompanyService {
	
	public List<CompanyLuceneVO> findCompanyLuceneVOList() throws YuleException;
	
	public List<CompanyTaskVO> findCompanyTaskVOList() throws YuleException;

	public List<CompanyVO> findCompanyVOList()throws YuleException;
	
	public List<CompanyVO> findCompanyVOListBySalesmanId(String salesmanId)throws YuleException;
	
	public List<CompanySearchCriteriaLuceneVO> findCompanySearchCriteriaLuceneVOList() throws YuleException;
	
}
