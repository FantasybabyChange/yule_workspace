package com.yule.admin.dao;

import java.util.List;
import java.util.Map;

import com.yule.exception.YuleException;
import com.yule.pojo.CompanyServeRelevant;

public interface ICompanyServeRelevantDao {
	
	public int batchInsertCompanyServeRelevant(List<CompanyServeRelevant> companyServeRelevants) throws YuleException;

	public int deleteCompanyServeRelevantAll() throws YuleException;
	
	public int updateCompanyServeRelevant(Map<String,Object> params) throws YuleException;
}