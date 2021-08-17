package com.yule.company.service;

import java.util.List;

import com.yule.exception.YuleException;
import com.yule.pojo.CompanyPrivilege;
import com.yule.vo.CompanyUserPrivilegeVO;

public interface ICompanyPrivilegeService {
	
	public List<CompanyPrivilege> findCompanyPrivilegeListByCompanyUserId(String companyUserId,Integer isShow) throws YuleException;
	
	public List<CompanyUserPrivilegeVO> findCompanyPrivilegeVOList(String companId,String userId) throws YuleException;
	
}
