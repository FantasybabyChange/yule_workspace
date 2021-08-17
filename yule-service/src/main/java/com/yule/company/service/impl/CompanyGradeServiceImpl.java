package com.yule.company.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yule.company.dao.ICompanyGradeDao;
import com.yule.company.service.ICompanyGradeService;
import com.yule.exception.YuleException;
import com.yule.pojo.CompanyGrade;

@Service("companyGradeServiceImpl")
public class CompanyGradeServiceImpl implements ICompanyGradeService {

	@Autowired
	private ICompanyGradeDao companyGradeDao;

	public CompanyGrade findCompanyGradeById(String id) throws YuleException {
		return this.companyGradeDao.findCompanyGradeById(id);
	}

}
