package com.yule.salesman.dao;

import java.util.List;

import com.yule.exception.YuleException;
import com.yule.vo.ProductVO;


public interface IProductDao {
	
	public List<ProductVO> findSimpleProductVOList(String companyId) throws YuleException;
	
}
