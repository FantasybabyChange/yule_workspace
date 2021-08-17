package com.yule.mongo.company.service;

import com.yule.exception.YuleException;
import com.yule.mongo.pojo.CompanyTask;
import com.yule.vo.Page;

public interface ICompanyTaskMongo {
	public Page<CompanyTask> findCompanyTaskPage(String companyId,int pageSize,int pageNo) throws YuleException;
	
	public int findCompanyTaskCount(String companyId) throws YuleException ;
}
