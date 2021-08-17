package com.yule.company.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yule.company.dao.IAdminRoleDao;
import com.yule.company.service.IAdminRoleService;
import com.yule.exception.YuleException;
import com.yule.pojo.AdminRole;

@Service("adminRoleServiceImpl")
public class AdminRoleServiceImpl implements IAdminRoleService {

	@Autowired
	private IAdminRoleDao adminRoleDao;

	public List<AdminRole> findAdminRoleList() throws YuleException {
		return this.adminRoleDao.findAdminRoleList();
	
	}

}
