package com.yule.mongo.cooperators.service;


import com.yule.exception.YuleException;
import com.yule.mongo.pojo.CompanyCooperator;

public interface ICompanyCooperatorMongo {
	public boolean insertCompanyCooperator(CompanyCooperator companyCooperator)throws YuleException;
}
