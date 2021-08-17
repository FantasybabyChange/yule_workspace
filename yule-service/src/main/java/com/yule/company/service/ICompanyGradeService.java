package com.yule.company.service;

import com.yule.exception.YuleException;
import com.yule.pojo.CompanyGrade;

public interface ICompanyGradeService {

	public CompanyGrade findCompanyGradeById(String id) throws YuleException;

}
