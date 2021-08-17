package com.yule.timer.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yule.exception.YuleException;
import com.yule.timer.dao.ICompanyDao;
import com.yule.timer.service.ICompanyService;
import com.yule.timer.vo.CompanyLuceneVO;
import com.yule.timer.vo.CompanySearchCriteriaLuceneVO;
import com.yule.timer.vo.CompanyTaskVO;
import com.yule.timer.vo.CompanyVO;

@Service("companyServiceImpl")
public class CompanyServiceImpl implements ICompanyService {

	@Autowired
	private ICompanyDao companyDao;
	
	public List<CompanyLuceneVO> findCompanyLuceneVOList() throws YuleException {
		return this.companyDao.findCompanyLuceneVOList();
	}

	public List<CompanyTaskVO> findCompanyTaskVOList() throws YuleException {
		return this.companyDao.findCompanyTaskVOList();
	}

	public List<CompanyVO> findCompanyVOList() throws YuleException {
		Map<String,Object> params = new HashMap<String, Object>();
		return this.companyDao.findCompanyVOList(params);
	}

	public List<CompanySearchCriteriaLuceneVO> findCompanySearchCriteriaLuceneVOList() throws YuleException {
		return this.companyDao.findCompanySearchCriteriaLuceneVOList();
	}

	public List<CompanyVO> findCompanyVOListBySalesmanId(String salesmanId) throws YuleException {
		Map<String,Object> params = new HashMap<String, Object>();
		params.put("salesmanId", salesmanId);
		return this.companyDao.findCompanyVOList(params);
	}


}
