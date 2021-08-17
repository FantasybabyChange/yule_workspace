package com.yule.orders.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yule.exception.YuleException;
import com.yule.orders.dao.IProductOrderDao;
import com.yule.orders.service.IProductService;

@Service("productServiceImpl")
public class ProductServiceImpl implements IProductService {

	@Autowired
	private IProductOrderDao productDao;

	public int findProductstatusById(String id) throws YuleException{
		return this.productDao.findProductstatusById(id);
	}
	
}
