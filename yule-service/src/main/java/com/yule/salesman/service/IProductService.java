package com.yule.salesman.service;

import java.util.List;

import com.yule.exception.YuleException;
import com.yule.vo.ProductVO;
public interface IProductService {

	public List<ProductVO> findSimpleProductVOList(String companyId) throws YuleException;
}
