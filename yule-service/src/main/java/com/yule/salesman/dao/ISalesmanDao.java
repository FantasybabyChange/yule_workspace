package com.yule.salesman.dao;

import java.util.List;
import java.util.Map;

import com.yule.exception.YuleException;
import com.yule.pojo.Salesman;

public interface ISalesmanDao {
	public int insertSalesman(Salesman salesman)throws YuleException;
	
	public int updateSalesman(Salesman salesman)throws YuleException;
	
	public Salesman findSalesmanById(String id)throws YuleException;

	public List<Salesman> findSalesmanVoListByParentId(Map<String, Object> params) throws YuleException;
	
}
