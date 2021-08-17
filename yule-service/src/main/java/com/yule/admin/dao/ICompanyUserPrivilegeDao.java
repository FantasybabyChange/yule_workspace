package com.yule.admin.dao;

import java.util.List;
import java.util.Map;

import com.yule.exception.YuleException;
import com.yule.pojo.CompanyUserPrivilege;

public interface ICompanyUserPrivilegeDao {
	
	public int batchInsertCompanyUserPrivilege(List<CompanyUserPrivilege> list) throws YuleException;
	
	public int updateCompanyUserPrivilege(Map<String,Object> params) throws YuleException;
	
}
