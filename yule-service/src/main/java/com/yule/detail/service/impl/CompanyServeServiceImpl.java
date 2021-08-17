package com.yule.detail.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yule.detail.dao.ICompanyServeDao;
import com.yule.detail.service.ICompanyServeService;
import com.yule.exception.YuleException;
import com.yule.pojo.CompanyServe;
@Service("companyServeServiceImpl")
public class CompanyServeServiceImpl implements ICompanyServeService{
	@Autowired
	private ICompanyServeDao companyServeDao;
	public List<CompanyServe> findCompanyServeByCompanyId(String companyId)
			throws YuleException {
		return  this.companyServeDao.findCompanyServeByCompanyId(companyId);
	}
	

}
