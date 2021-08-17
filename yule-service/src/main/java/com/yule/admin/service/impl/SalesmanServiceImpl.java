package com.yule.admin.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yule.admin.dao.ISalesmanDao;
import com.yule.admin.service.ISalesmanService;
import com.yule.exception.YuleException;
import com.yule.pojo.Salesman;
@Service("salesmanServiceImpl")
public class SalesmanServiceImpl implements ISalesmanService {
	@Autowired
	private ISalesmanDao salesmanDao;
	public Salesman findSalesmanById(String id) throws YuleException {
		return this.salesmanDao.findSalesmanById(id);
	}
	public boolean updateSalesman(Salesman salesman )throws YuleException{
		boolean flag = false;
		try {
			this.salesmanDao.updateSalesman(salesman);
			flag = true;
		} catch (Exception e) {
	    	throw new YuleException(e);
	    }
		return flag;
		
	}
	
}
