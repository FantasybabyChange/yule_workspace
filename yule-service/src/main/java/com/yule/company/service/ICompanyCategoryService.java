package com.yule.company.service;

import com.yule.exception.YuleException;
import com.yule.pojo.CompanyCategory;

public interface ICompanyCategoryService {

	public CompanyCategory findCompanyCategoryById(String id) throws YuleException;

}
