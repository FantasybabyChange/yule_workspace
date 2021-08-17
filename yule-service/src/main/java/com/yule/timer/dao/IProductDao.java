package com.yule.timer.dao;

import java.util.Map;

import com.yule.exception.YuleException;
import com.yule.timer.vo.ProductLuceneVO;

public interface IProductDao {
	
	public ProductLuceneVO findProductLuceneVO(Map<String,Object> params) throws YuleException;
	
	public int findProductCount(Map<String,Object> params) throws YuleException;
	
}
