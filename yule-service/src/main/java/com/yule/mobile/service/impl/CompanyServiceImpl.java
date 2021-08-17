package com.yule.mobile.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yule.mobile.vo.CompanyVO;
import com.yule.exception.YuleException;
import com.yule.mobile.service.ICompanyService;
import com.yule.pojo.Company;
import com.yule.mobile.dao.ICompanyDao;

@Service("companyServiceImpl")
public class CompanyServiceImpl implements ICompanyService {
	@Autowired
	private ICompanyDao companyDao;
	
	public CompanyVO findCompanyVOById(String id) throws YuleException {
		return  this.companyDao.findCompanyVOById(id);
	}
	public Company findCompanyById(String companyId) throws Exception {
		return this.companyDao.findCompanyById(companyId);
		
	}
	public List<Company> findCompanyList(int pageSize, int pageNo)
			throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("pageStart", (pageNo - 1) * pageSize);
		map.put("pageEnd", pageSize);
		return this.companyDao.findCompanys(map);
	}
	
}
