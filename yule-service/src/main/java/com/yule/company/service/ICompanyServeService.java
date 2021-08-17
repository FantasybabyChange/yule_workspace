package com.yule.company.service;

import java.util.List;

import com.yule.exception.YuleException;
import com.yule.vo.CompanyServeVO;

/**
 * 企业服务设施
 */
public interface ICompanyServeService {

	public List<CompanyServeVO> findCompanyAndServeList(String companyId) throws YuleException;
	
	public boolean updateCompanyCheckServe(String companyId,List<String> companyServeIds) throws YuleException;
	
}