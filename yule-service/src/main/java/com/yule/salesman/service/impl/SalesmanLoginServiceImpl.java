package com.yule.salesman.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.druid.util.StringUtils;
import com.yule.exception.YuleException;
import com.yule.pojo.SalesmanLogin;
import com.yule.salesman.dao.ISalesmanLoginDao;
import com.yule.salesman.service.ISalesmanLoginService;
import com.yule.salesman.vo.SalesmanVO;
import com.yule.util.EncryptUtil;
@Service("salesmanLoginServiceImpl")
public class SalesmanLoginServiceImpl implements ISalesmanLoginService {
	@Autowired
	private ISalesmanLoginDao salesmanLoginDao;
	/**
	 * 用于
	 */
	public  SalesmanLogin findSalesmanLoginByAccount(String account)throws YuleException {
		return this.salesmanLoginDao.findSalesmanLoginByAccount(account);
	
	}
	public boolean updateSalesmanLogin(SalesmanLogin salesmanLogin)throws YuleException {
		boolean flag = false;
		try {
			if (!StringUtils.isEmpty(salesmanLogin.getPassword())) {
				salesmanLogin.setPassword(EncryptUtil.encryptToMD5(salesmanLogin.getPassword()));
			}
			this.salesmanLoginDao.updateSalesmanLogin(salesmanLogin);
			flag = true;
		} catch (Exception e) {
	    	throw new YuleException(e);
	    }
		return flag;
	}
	public SalesmanVO findSalesmanLoginById(String id) throws YuleException{
		return this.salesmanLoginDao.findSalesmanLoginById(id);
	}
	public SalesmanVO findSalesmanVOByAccount(String account) throws YuleException {
		return  this.salesmanLoginDao.findSalesmanVOByAccount(account);
	}

}
