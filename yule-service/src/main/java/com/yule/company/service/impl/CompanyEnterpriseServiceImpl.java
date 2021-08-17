package com.yule.company.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.druid.util.StringUtils;
import com.yule.company.dao.ICompanyEnterpriseDao;
import com.yule.company.service.ICompanyEnterpriseService;
import com.yule.exception.YuleException;
import com.yule.pojo.CompanyEnterprise;
import com.yule.util.PinyinUtil;



@Service("companyEnterpriseServiceImpl")
public class CompanyEnterpriseServiceImpl implements ICompanyEnterpriseService{

	@Autowired
	private ICompanyEnterpriseDao companyEnterpriseDao;


	public boolean updateCompanyEnterprise(CompanyEnterprise companyEnterprise)throws YuleException,Exception {
		boolean flag = false;
		try {
			if (null!=companyEnterprise.getName()&&StringUtils.isEmpty(companyEnterprise.getName())) {
				companyEnterprise.setPinyin(PinyinUtil.getPinYin(companyEnterprise.getName()));
			}
			this.companyEnterpriseDao.updateCompanyEnterprise(companyEnterprise);
			flag = true;
		} catch (Exception e) {
	    	throw new YuleException(e);
	    }
		return flag;
	}
	
}
