package com.yule.company.dao;

import java.util.List;
import java.util.Map;

import com.yule.exception.YuleException;
import com.yule.pojo.CompanyPrivilege;
import com.yule.vo.CompanyUserPrivilegeVO;

public interface ICompanyPrivilegeDao {
	
	public List<CompanyPrivilege> findCompanyPrivilegeList(Map<String, Object> map) throws YuleException;
	
	public List<CompanyUserPrivilegeVO> findCompanyPrivilegeVOList(Map<String, Object> map) throws YuleException;
	
}
