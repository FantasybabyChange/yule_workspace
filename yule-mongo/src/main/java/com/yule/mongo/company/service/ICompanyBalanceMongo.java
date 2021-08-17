package com.yule.mongo.company.service;

import com.yule.exception.YuleException;
import com.yule.mongo.company.query.BalanceQuery;
import com.yule.mongo.company.vo.CompanyBalanceVO;
import com.yule.mongo.timer.vo.CompanyOrdersBalanceVO;
import com.yule.vo.Page;

public interface ICompanyBalanceMongo {

	
	public Page<CompanyBalanceVO> findHistryCompanyBalancePage(BalanceQuery balanceQuery,int pageSize,int pageNo)throws YuleException;
	
	public CompanyOrdersBalanceVO findNowWeekCompanyBalanceByQuery(String companyId ,String start_time,String end_time)throws YuleException;
	
}
