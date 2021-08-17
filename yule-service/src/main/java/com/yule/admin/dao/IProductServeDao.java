package com.yule.admin.dao;

import java.util.List;

import com.yule.exception.YuleException;
import com.yule.pojo.ProductServe;

public interface IProductServeDao {
	
	public int insertProductServe(ProductServe productServe) throws YuleException;

	public int updateProductServe(ProductServe productServe) throws YuleException;
	
	public int deleteProductServeById(String id) throws YuleException;
	
	public List<ProductServe> findProductServeList() throws YuleException;
	
	public int batchInsertProductServe(List<ProductServe> productServes) throws YuleException;
	
	public int batchUpdateProductServe(List<ProductServe> productServes) throws YuleException;

}
