package com.yule.user.dao;

import com.yule.user.vo.CompanyVO;
import com.yule.exception.YuleException;
/**
 * 
 */
public interface ICompanyDao {
	public CompanyVO findCompanyVOById(String id) throws YuleException;
}
