package com.yule.search.service;

import java.util.List;

import com.yule.exception.YuleException;
import com.yule.pojo.Company;

public interface ICompanyService {
	
	public List<Company>findOtherCompanyByCompanyId(String id,int pageSize,int pageNo)throws YuleException;
	
}
