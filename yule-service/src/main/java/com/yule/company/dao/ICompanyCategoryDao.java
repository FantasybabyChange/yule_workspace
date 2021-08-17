package com.yule.company.dao;

import com.yule.exception.YuleException;
import com.yule.pojo.CompanyCategory;

public interface ICompanyCategoryDao {

	public CompanyCategory findCompanyCategoryById(String id) throws YuleException;

}
