package com.yule.admin.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yule.admin.dao.ICompanyEnterpriseDao;
import com.yule.admin.service.ICompanyEnterpriseService;
import com.yule.exception.YuleException;
import com.yule.pojo.CompanyEnterprise;

@Service("companyEnterpriseServiceImpl")
public class CompanyEnterpriseServiceImpl implements ICompanyEnterpriseService{

	@Autowired
	private ICompanyEnterpriseDao companyEnterpriseDao;


	public boolean updateCompanyEnterprise(CompanyEnterprise companyEnterprise)throws YuleException {
		boolean flag = false;
		try {
			this.companyEnterpriseDao.updateCompanyEnterprise(companyEnterprise);
			flag = true;
		} catch (Exception e) {
	    	throw new YuleException(e);
	    }
		return flag;
	}
	
}
