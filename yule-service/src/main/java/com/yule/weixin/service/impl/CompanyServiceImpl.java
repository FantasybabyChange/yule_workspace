package com.yule.weixin.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yule.exception.YuleException;
import com.yule.pojo.Company;
import com.yule.weixin.dao.ICompanyDao;
import com.yule.weixin.service.ICompanyService;

@Service("companyServiceImpl")
public class CompanyServiceImpl implements ICompanyService{
	
	@Autowired
	private ICompanyDao companyDao; 

	public boolean updateCompanyOpenId(String companyId, String openId) throws YuleException {
		boolean flag = false;
		try{
			Company company = new Company();
			company.setId(companyId);
			company.setOpenId(openId);
			companyDao.updateCompany(company);
			flag = true;
		}catch(Exception e){
			throw new YuleException(e);
		}
		return flag;
	}
	
}
