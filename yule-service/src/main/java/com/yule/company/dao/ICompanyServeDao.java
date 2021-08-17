package com.yule.company.dao;

import java.util.List;
import java.util.Map;

import com.yule.exception.YuleException;
import com.yule.vo.CompanyServeVO;

public interface ICompanyServeDao {

	public List<CompanyServeVO> findCompanyAndServeList(Map<String,Object> params) throws YuleException;
	
}
