package com.yule.admin.dao;

import java.util.List;
import java.util.Map;

import com.yule.exception.YuleException;
import com.yule.pojo.ProductCategory;
public interface IProductCategoryDao {

	public int insertProductCategory(ProductCategory productCategory) throws YuleException;
	
	public int updateProductCategory(ProductCategory productCategory) throws YuleException;
	
	public int deleteProductCategoryById(String id) throws YuleException;
	
	public int deleteProductCategoryAll() throws YuleException;

	public List<ProductCategory> findProductCategoryList() throws YuleException;
		
	/**
	 * 获取产品分页总数据量
	 */
	public int findProductCategoryCount(Map<String, Object> params) throws YuleException;
	
	/**
	 * 获取产品分页信息
	 */	
	public List<ProductCategory> findProductCategoryList(Map<String, Object> params) throws YuleException;
	
	public int batchUpdateProductCategory(List<ProductCategory> productCategory) throws YuleException;
	
	public int batchInsertProductCategory(List<ProductCategory> productCategory) throws YuleException;
	   
}
