package com.yule.detail.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yule.detail.dao.ICompanyExpenseDao;
import com.yule.detail.service.ICompanyExpenseService;
import com.yule.detail.vo.CompanyExpenseVO;
import com.yule.exception.YuleException;
@Service("companyExpenseServiceImpl")
public class CompanyExpenseServiceImpl implements ICompanyExpenseService {
	
	@Autowired
	private ICompanyExpenseDao companyExpenseDao;
	
	public List<CompanyExpenseVO> findCompanyExpenseVoList(String companyId) throws YuleException {
		return this.companyExpenseDao.findCompanyExpenseVoList(companyId);
	}

}
