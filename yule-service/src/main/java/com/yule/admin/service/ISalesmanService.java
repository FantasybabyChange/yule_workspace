package com.yule.admin.service;

import com.yule.exception.YuleException;
import com.yule.pojo.Salesman;

public interface ISalesmanService {
	public Salesman findSalesmanById(String id)throws YuleException;
	
	public boolean updateSalesman(Salesman salesman )throws YuleException;

}
