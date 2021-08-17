package com.yule.mongo.admin.service;

import com.yule.exception.YuleException;
import com.yule.mongo.admin.query.CompanyBalanceQuery;
import com.yule.mongo.admin.vo.CompanyBalanceVO;
import com.yule.mongo.pojo.CompanyBalance;
import com.yule.vo.Page;

public interface ICompanyBalanceMongo {

	
	public Page<CompanyBalanceVO> findHistryCompanyBalancePage(CompanyBalanceQuery companyBalanceQuery,int pageSize,int pageNo)throws YuleException;

	public boolean updateCompanyBalance(CompanyBalance companyBalance) throws YuleException;
}
