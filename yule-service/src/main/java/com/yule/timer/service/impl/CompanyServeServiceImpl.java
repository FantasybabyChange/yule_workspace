package com.yule.timer.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yule.exception.YuleException;
import com.yule.timer.dao.ICompanyServeDao;
import com.yule.timer.service.ICompanyServeService;
import com.yule.timer.vo.CompanyServeLuceneVO;

@Service("companyServeServiceImpl")
public class CompanyServeServiceImpl implements ICompanyServeService {

	@Autowired
	private ICompanyServeDao companyServeDao;
	
	public List<CompanyServeLuceneVO> findCompanyServeLuceneVOList(String companyId) throws YuleException{
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("company_id", companyId);
		return this.companyServeDao.findCompanyServeLuceneVOList(params);
	}

}
