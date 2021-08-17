package com.yule.company.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yule.company.dao.ICompanyPrivilegeDao;
import com.yule.company.service.ICompanyPrivilegeService;
import com.yule.exception.YuleException;
import com.yule.pojo.CompanyPrivilege;
import com.yule.vo.CompanyUserPrivilegeVO;

@Service("companyPrivilegeServiceImpl")
public class CompanyPrivilegeServiceImpl implements ICompanyPrivilegeService {
	
	@Autowired
	private ICompanyPrivilegeDao companyPrivilegeDao;

	public List<CompanyPrivilege> findCompanyPrivilegeListByCompanyUserId(String companyUserId,Integer isShow) throws YuleException {
		Map<String,Object> params = new HashMap<String, Object>();
		params.put("company_user_id", companyUserId);
		params.put("is_show", isShow);
		return  this.companyPrivilegeDao.findCompanyPrivilegeList(params);
	}
	
	public List<CompanyUserPrivilegeVO> findCompanyPrivilegeVOList(String companyId,String userId)throws YuleException {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("company_id", companyId);
		map.put("user_id", userId);
		return this.companyPrivilegeDao.findCompanyPrivilegeVOList(map);
	}
}
