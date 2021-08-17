package com.yule.company.dao;

import com.yule.exception.YuleException;
import com.yule.pojo.CompanyGrade;


public interface ICompanyGradeDao {

	public CompanyGrade findCompanyGradeById(String id) throws YuleException;
	
}
