package com.yule.orders.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yule.exception.YuleException;
import com.yule.orders.dao.ICompanyOrderDao;
import com.yule.orders.service.ICompanyService;
import com.yule.pojo.Company;

@Service("companyServiceImpl")
public class CompanyServiceImpl implements ICompanyService {

	@Autowired
	private ICompanyOrderDao companyDao;
	
	public Company findCompanyById(String id) throws YuleException {
		return this.companyDao.findCompanyById(id);
	}
	
}
