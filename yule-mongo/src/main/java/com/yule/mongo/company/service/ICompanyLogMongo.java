package com.yule.mongo.company.service;

import com.yule.exception.YuleException;
import com.yule.mongo.company.query.CompanyLogQuery;
import com.yule.mongo.pojo.CompanyLog;
import com.yule.vo.Page;

public interface ICompanyLogMongo {
	
	public Page<CompanyLog> findCompanyLogPage(CompanyLogQuery companyLogQuery,int pageSize,int pageNo) throws YuleException;
	
	public boolean insertCompanyLog(CompanyLog companyLog)  throws YuleException;
	
}
