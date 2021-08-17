package com.yule.search.dao;

import java.util.List;
import java.util.Map;

import com.yule.exception.YuleException;
import com.yule.pojo.Company;
/**
 * 
 */
public interface ICompanyDao {
	
	public List<Company> findOtherCompanyById(Map<String, Object> map) throws YuleException;
	
}
