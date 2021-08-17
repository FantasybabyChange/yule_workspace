package com.yule.search.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yule.exception.YuleException;
import com.yule.pojo.Company;
import com.yule.search.dao.ICompanyDao;
import com.yule.search.service.ICompanyService;

@Service("companyServiceImpl")
public class CompanyServiceImpl implements ICompanyService {
	@Autowired
	private ICompanyDao companyDao;
	

	public List<Company> findOtherCompanyByCompanyId(String city_id, int pageSize,int pageNo) throws YuleException {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("pageStart", (pageNo - 1) * pageSize);
		map.put("pageEnd", pageSize);
		map.put("city_id", city_id);
		return this.companyDao.findOtherCompanyById(map);
		
	}


}
