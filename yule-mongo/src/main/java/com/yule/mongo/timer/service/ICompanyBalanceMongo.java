package com.yule.mongo.timer.service;

import java.util.List;

import com.yule.exception.YuleException;
import com.yule.mongo.pojo.CompanyBalance;

public interface ICompanyBalanceMongo {

	public boolean batchInsertCompanyBalance(List<CompanyBalance> companyBalances)throws YuleException;
}
