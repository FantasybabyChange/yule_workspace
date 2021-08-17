package com.yule.salesman.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yule.exception.YuleException;
import com.yule.pojo.Salesman;
import com.yule.salesman.dao.ISalesmanDao;
import com.yule.salesman.service.ISalesmanService;
import com.yule.vo.Page;

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
	public Page<Salesman> findSalesmanVoPageByParentId(String id,int pageSize, int pageNo) throws YuleException {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("pageStart", (pageNo - 1) * pageSize);
		params.put("pageEnd", pageSize);
		Page<Salesman> page =new Page<Salesman>();
		return page;
	}
	
}
