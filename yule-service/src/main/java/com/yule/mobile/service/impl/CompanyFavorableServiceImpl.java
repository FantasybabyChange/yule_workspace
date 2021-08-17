package com.yule.mobile.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yule.mobile.dao.ICompanyFavorableDao;
import com.yule.mobile.service.ICompanyFavorableService;
import com.yule.exception.YuleException;
import com.yule.pojo.CompanyFavorable;
@Service("companyFavorableServiceImpl")
public class CompanyFavorableServiceImpl implements ICompanyFavorableService {
	@Autowired
	private ICompanyFavorableDao companyFavorableDao;
	public List<CompanyFavorable> findCompanyFavorableByCompanyId(String companyId) throws YuleException {
		return this.companyFavorableDao.findCompanyFavorableList(companyId);
	}

}
