package com.yule.orders.dao;

import com.yule.exception.YuleException;
import com.yule.pojo.Company;

public interface ICompanyOrderDao {
	
	public Company findCompanyById(String id) throws YuleException;
	
}
