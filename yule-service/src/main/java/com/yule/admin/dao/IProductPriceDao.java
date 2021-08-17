package com.yule.admin.dao;

import java.util.List;

import com.yule.exception.YuleException;
import com.yule.pojo.ProductPrice;

public interface IProductPriceDao {
	
	public List<ProductPrice> findProductPriceList(String productId) throws YuleException;
	
	public int insertProductPrice(ProductPrice productPrice) throws YuleException;
	
	public int deleteProductPriceById(String id) throws YuleException;
	
	public int updateProductPrice(ProductPrice productPrice) throws YuleException;
	
	public int batchUpdateProductPrice(List<ProductPrice> productPrice) throws YuleException;
	
	public int batchInsertProductPrice(List<ProductPrice> productPrice) throws YuleException;
}
