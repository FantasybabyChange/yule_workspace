package com.yule.pc.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yule.pc.dao.ICompanyDao;
import com.yule.pc.service.ICompanyService;

@Service("companyServiceImpl")
public class CompanyServiceImpl implements ICompanyService {

	@Autowired
	private ICompanyDao companyDao;

//	public List<CompanyHotVO> findCompanyHotVOList(String areaCityId,String companyCategoryId,Integer pageSize, Integer pageNo) throws YuleException{
//		Map<String,Object> params = new HashMap<String,Object>();
//		params.put("areaCityId", areaCityId);
//		params.put("companyCategoryId", companyCategoryId);
//		params.put("pageStart", (pageNo - 1) * pageSize);
//		params.put("pageEnd", pageSize);
//		return companyDao.findCompanyHotVOList(params);
//	}
	
}
