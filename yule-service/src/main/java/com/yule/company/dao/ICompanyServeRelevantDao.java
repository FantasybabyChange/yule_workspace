package com.yule.company.dao;

import java.util.List;

import com.yule.exception.YuleException;
import com.yule.pojo.CompanyServeRelevant;

public interface ICompanyServeRelevantDao {
	
	public int batchInsertCompanyServeRelevant(List<CompanyServeRelevant> companyServeRelevants) throws YuleException;
	
	public int deleteCompanyServeRelevantByCompanyId(String id) throws YuleException;
	
}
