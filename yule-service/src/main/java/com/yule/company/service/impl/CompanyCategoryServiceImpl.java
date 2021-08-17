package com.yule.company.service.impl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yule.company.dao.ICompanyCategoryDao;
import com.yule.company.service.ICompanyCategoryService;
import com.yule.exception.YuleException;
import com.yule.pojo.CompanyCategory;
@Service("companyCategoryServiceImpl")
public class CompanyCategoryServiceImpl implements ICompanyCategoryService {

	@Autowired
	private ICompanyCategoryDao companyCategoryDao;

	public CompanyCategory findCompanyCategoryById(String id) throws YuleException {
		return this.companyCategoryDao.findCompanyCategoryById(id);
	}

}
