package com.yule.mobile.service;

import java.util.List;

import com.yule.exception.YuleException;
import com.yule.vo.ProductVO;
public interface IProductService {
	public List<ProductVO> findProductVOList(String companyId) throws YuleException;
	
}
