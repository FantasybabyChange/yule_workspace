package com.yule.user.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yule.exception.YuleException;
import com.yule.user.dao.ICompanyDao;
import com.yule.user.service.ICompanyService;
import com.yule.user.vo.CompanyVO;

@Service("companyServiceImpl")
public class CompanyServiceImpl implements ICompanyService {
	@Autowired
	private ICompanyDao companyDao;
	
	public CompanyVO findCompanyVOById(String id) throws YuleException {
		return  this.companyDao.findCompanyVOById(id);
	}
}
