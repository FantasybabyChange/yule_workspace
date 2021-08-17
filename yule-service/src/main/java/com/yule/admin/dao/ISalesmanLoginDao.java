package com.yule.admin.dao;

import java.util.List;
import java.util.Map;

import com.yule.admin.vo.SalesmanVO;
import com.yule.exception.YuleException;
import com.yule.pojo.SalesmanLogin;
public interface ISalesmanLoginDao {

	public int insertSalesmanLogin(SalesmanLogin salesmanLogin)throws YuleException;
	
	public int updateSalesmanLogin(SalesmanLogin salesmanLogin)throws YuleException;
	
	public List<SalesmanVO> findSalesmanVOPage(Map<String, Object> map) throws YuleException;
	
	public int  findSalesmanVOCount(Map<String, Object> map) throws YuleException;
	
	public int findSalesmanLoginByAccount(String account) throws YuleException;
	
	public List<SalesmanLogin> findSalesmanLoginList()throws YuleException;

}
