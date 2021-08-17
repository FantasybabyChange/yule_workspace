package com.yule.detail.service;

import java.util.List;

import com.yule.detail.vo.CompanyExpenseVO;
import com.yule.exception.YuleException;

public interface ICompanyExpenseService {
	public List<CompanyExpenseVO> findCompanyExpenseVoList(String companyId)throws YuleException;
}
