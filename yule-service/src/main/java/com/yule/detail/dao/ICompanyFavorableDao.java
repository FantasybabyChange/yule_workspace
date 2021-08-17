package com.yule.detail.dao;

import java.util.List;

import com.yule.exception.YuleException;
import com.yule.pojo.CompanyFavorable;
/**
 * 
 */
public interface ICompanyFavorableDao {
	public List<CompanyFavorable> findCompanyFavorableList(String id) throws YuleException;
}
