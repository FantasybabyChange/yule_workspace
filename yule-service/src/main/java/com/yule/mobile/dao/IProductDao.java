package com.yule.mobile.dao;

import java.util.List;

import com.yule.exception.YuleException;
import com.yule.vo.ProductVO;

public interface IProductDao {
	public List<ProductVO> findProductVOList(String companyId)throws YuleException;
}
