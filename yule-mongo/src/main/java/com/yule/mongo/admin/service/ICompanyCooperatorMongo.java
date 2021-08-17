package com.yule.mongo.admin.service;


import com.yule.exception.YuleException;
import com.yule.mongo.admin.query.CompanyCooperatorQuery;
import com.yule.mongo.pojo.CompanyCooperator;
import com.yule.vo.Page;

public interface ICompanyCooperatorMongo {
	
	public Page<CompanyCooperator> findCompanyCooperatorPage(CompanyCooperatorQuery companyCooperatorQuery,int pageSize,int pageNo) throws YuleException;
	
	public boolean deleteCompanyCooperatorById(String id) throws YuleException;
	
	public boolean updateCompanyCooperator(CompanyCooperator companyCooperator) throws YuleException;
	
	public CompanyCooperator findCompanyCooperatorById(String id) throws YuleException;
}
