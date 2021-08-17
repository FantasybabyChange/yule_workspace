package com.yule.admin.service;

import java.util.List;

import com.yule.exception.YuleException;
import com.yule.pojo.CompanyPrivilege;
import com.yule.vo.CompanyUserPrivilegeVO;


public interface ICompanyPrivilegeService {
	public boolean insertCompanyPrivilege(CompanyPrivilege companyPrivilege) throws YuleException;
	
	public List<CompanyPrivilege> findCompanyPrivilegeList(String categoryId,String parent_id) throws YuleException;
	
	public boolean deleteCompanyPrivilegeById(String id) throws YuleException;
	
	public boolean deleteCompanyPrivilegeByAll() throws YuleException;
	
	public CompanyPrivilege findCompanyPrivilegeById(String id) throws YuleException;
	
	/**
	 * 通过企业ID和用户ID查询权限
	 */
	public List<CompanyUserPrivilegeVO> findCompanyPrivilegeVOList(String companId,String userId) throws YuleException;
	
	public boolean batchInsertCompanyPrivilege(List<CompanyPrivilege> companyPrivilege) throws YuleException;
	
	public List<CompanyPrivilege> findCompanyPrivilegeListByCompanyUserId(String companyUserId,Integer isShow) throws YuleException;
	
}
