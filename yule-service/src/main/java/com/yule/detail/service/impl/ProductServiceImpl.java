package com.yule.detail.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yule.detail.dao.IProductDao;
import com.yule.detail.service.IProductService;
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
