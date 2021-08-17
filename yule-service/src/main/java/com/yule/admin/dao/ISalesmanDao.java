package com.yule.admin.dao;

import com.yule.exception.YuleException;
import com.yule.pojo.Salesman;

public interface ISalesmanDao {
	public int insertSalesman(Salesman salesman)throws YuleException;
	
	public int updateSalesman(Salesman salesman)throws YuleException;
	
	public Salesman findSalesmanById(String id)throws YuleException;
	
}
