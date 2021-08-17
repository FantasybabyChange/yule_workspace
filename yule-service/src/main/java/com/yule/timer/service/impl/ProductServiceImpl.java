package com.yule.timer.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yule.exception.YuleException;
import com.yule.timer.dao.IProductDao;
import com.yule.timer.service.IProductService;
import com.yule.timer.vo.ProductLuceneVO;

@Service("productServiceImpl")
public class ProductServiceImpl implements IProductService {

	@Autowired
	private IProductDao productLuceneDao;
	
	public ProductLuceneVO findProductLuceneVO(String companyId) throws YuleException{
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("companyId", companyId);
		return this.productLuceneDao.findProductLuceneVO(params);
	}
	
	public int findProductCount(String companyId) throws YuleException{
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("companyId", companyId);
		return this.productLuceneDao.findProductCount(params);
	}
	
}
