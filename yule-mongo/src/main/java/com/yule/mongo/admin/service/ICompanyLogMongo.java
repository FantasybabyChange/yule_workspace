package com.yule.mongo.admin.service;

import com.yule.exception.YuleException;
import com.yule.mongo.admin.query.CompanyLogQuery;
import com.yule.mongo.pojo.CompanyLog;
import com.yule.vo.Page;

public interface ICompanyLogMongo {
	public Page<CompanyLog> findCompanyLogPage(CompanyLogQuery companyLogQuery,int pageSize,int pageNo) throws YuleException;
}
