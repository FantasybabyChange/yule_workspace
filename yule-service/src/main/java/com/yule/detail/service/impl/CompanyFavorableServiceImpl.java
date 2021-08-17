package com.yule.detail.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yule.detail.dao.ICompanyFavorableDao;
import com.yule.detail.service.ICompanyFavorableService;
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
