package com.yule.mobile.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yule.mobile.dao.IProductDao;
import com.yule.mobile.service.IProductService;
import com.yule.exception.YuleException;
import com.yule.vo.ProductVO;

@Service("productServiceImpl")
public class ProductServiceImpl implements IProductService {
	@Autowired
	private IProductDao productDao;
	public List<ProductVO> findProductVOList(String companyId) throws YuleException {
		return this.productDao.findProductVOList(companyId);
	}
	
}
