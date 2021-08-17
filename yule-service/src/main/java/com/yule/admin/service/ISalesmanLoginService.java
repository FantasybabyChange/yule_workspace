package com.yule.admin.service;

import java.util.List;

import com.yule.admin.query.SalesmanQuery;
import com.yule.admin.vo.SalesmanVO;
import com.yule.exception.YuleException;
import com.yule.pojo.SalesmanLogin;
import com.yule.vo.Page;
public interface ISalesmanLoginService {
	public boolean insertSalesmanLogin(SalesmanLogin salesmanLogin,Integer commision)throws YuleException;
	
	public boolean deleteSalesmanLogin(SalesmanLogin salesmanLogin)throws YuleException;
	
	public boolean updateSalesmanLoginStatus(SalesmanLogin salesmanLogin)throws YuleException;
	
	public boolean updateSalesmanLoginPassword(SalesmanLogin salesmanLogin)throws YuleException;
	
	public Page<SalesmanVO> findSalesmanVOPage(SalesmanQuery salesmanQuery,int pageSize,int pageNo) throws YuleException;
	
	public boolean findSalesmanLoginByAccount(String account) throws YuleException;

	public List<SalesmanLogin> findSalesmanLoginList()throws YuleException;
}
