package com.yule.detail.dao;

import java.util.List;

import com.yule.detail.vo.CompanyExpenseVO;
import com.yule.exception.YuleException;
/**
 * 
 */
public interface ICompanyExpenseDao {
	public List<CompanyExpenseVO> findCompanyExpenseVoList(String id) throws YuleException;
}
