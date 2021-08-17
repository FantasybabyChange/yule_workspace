package com.yule.salesman.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yule.exception.YuleException;
import com.yule.salesman.dao.IProductDao;
import com.yule.salesman.service.IProductService;
import com.yule.vo.ProductVO;


@Service("productServiceImpl")
public class ProductServiceImpl implements IProductService {

	@Autowired
	private IProductDao productDao;

	public List<ProductVO> findSimpleProductVOList(String companyId)throws YuleException {
		return this.productDao.findSimpleProductVOList(companyId);
	}
	
}
