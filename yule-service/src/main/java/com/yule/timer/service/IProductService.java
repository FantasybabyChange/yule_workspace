package com.yule.timer.service;

import com.yule.exception.YuleException;
import com.yule.timer.vo.ProductLuceneVO;

public interface IProductService {
	
	public ProductLuceneVO findProductLuceneVO(String companyId) throws YuleException;
	
	public int findProductCount(String companyId) throws YuleException;

}
