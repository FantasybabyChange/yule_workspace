package com.yule.orders.service;

import com.yule.exception.YuleException;
import com.yule.pojo.Company;

public interface ICompanyService {
	
	public Company findCompanyById(String id) throws YuleException;

}
