package com.yule.admin.dao;

import java.util.List;

import com.yule.exception.YuleException;
import com.yule.pojo.Product;
import com.yule.vo.ProductVO;


public interface IProductDao {

	public int insertProduct(Product product) throws YuleException;
	
	public int updateProduct(Product product) throws YuleException;
	
	public int deleteProductById(String id) throws YuleException;

	public Product findProductById(String id) throws YuleException;

	public int batchInsertProduct(List<Product> products) throws YuleException;
	
	public int batchUpdateProduct(List<Product> products) throws YuleException;
	
	public List<ProductVO> findProductVOList(String company_id) throws YuleException;
	
}
