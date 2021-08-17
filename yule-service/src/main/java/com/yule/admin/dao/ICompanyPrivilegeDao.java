package com.yule.admin.dao;

import java.util.List;
import java.util.Map;

import com.yule.exception.YuleException;
import com.yule.pojo.CompanyPrivilege;
import com.yule.vo.CompanyUserPrivilegeVO;

public interface ICompanyPrivilegeDao {
	
	public int insertCompanyPrivilege(CompanyPrivilege companyPrivilege) throws YuleException;
	
	public List<CompanyPrivilege> findCompanyPrivilegeList(Map<String, Object> map) throws YuleException;
	
	public CompanyPrivilege findCompanyPrivilegeById(String id) throws YuleException;
	
	public 	List<CompanyPrivilege> findCompanyPrivilegeListByCategoryId(String id);
	
	public int deleteCompanyPrivivlegeById(List<CompanyPrivilege> list) throws YuleException;
	
	public int deleteCompanyPrivivlegeByAll() throws YuleException;
	
	public List<CompanyUserPrivilegeVO> findCompanyPrivilegeVOList(Map<String, Object> map) throws YuleException;

	public int batchInsertCompanyPrivilege(List<CompanyPrivilege> companyPrivilege) throws YuleException;
	
	public List<CompanyPrivilege> findCompanyPrivilegesByParentId(String id)throws YuleException;
	
	public List<CompanyPrivilege> findCompanyPrivilegeListByUserId(Map<String, Object> map) throws YuleException;
}
