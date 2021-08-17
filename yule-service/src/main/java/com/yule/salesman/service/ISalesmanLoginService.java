package com.yule.salesman.service;

import com.yule.exception.YuleException;
import com.yule.pojo.SalesmanLogin;
import com.yule.salesman.vo.SalesmanVO;
public interface ISalesmanLoginService {
	public boolean updateSalesmanLogin(SalesmanLogin salesmanLogin)throws YuleException;
	
	public SalesmanLogin findSalesmanLoginByAccount(String account) throws YuleException;
	
	public SalesmanVO findSalesmanLoginById(String id) throws YuleException;
	
	public SalesmanVO findSalesmanVOByAccount(String account) throws YuleException;
}
