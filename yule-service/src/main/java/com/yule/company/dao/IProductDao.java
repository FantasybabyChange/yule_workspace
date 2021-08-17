package com.yule.company.dao;

import java.util.List;
import java.util.Map;

import com.yule.exception.YuleException;
import com.yule.pojo.Product;
import com.yule.vo.ProductVO;


public interface IProductDao {

	public int insertProduct(Product product) throws YuleException;
	
	public int updateProduct(Product product) throws YuleException;
	
	public int deleteProductById(String id) throws YuleException;

	public int findProductCount(Map<String, Object> params) throws YuleException;
		
	public List<Product> findProductList(Map<String, Object> params) throws YuleException;
	
	public int batchInsertProduct(List<Product> products) throws YuleException;
	
	public int batchUpdateProduct(List<Product> products) throws YuleException;
	
	public List<ProductVO> findProductVOList(String companyId) throws YuleException;
	
	public List<ProductVO> findSimpleProductVOList(String companyId) throws YuleException;
	
	public List<ProductVO> findOtherCompanyProductList(Map<String, Object> params) throws YuleException;
	
	public int findOtherCompanyProductCount(Map<String, Object> params) throws YuleException;
}
