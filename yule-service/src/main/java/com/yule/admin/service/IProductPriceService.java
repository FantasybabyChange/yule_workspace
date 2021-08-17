package com.yule.admin.service;

import java.util.List;

import com.yule.admin.param.InsertProductPriceParam;
import com.yule.exception.YuleException;
import com.yule.pojo.ProductPrice;

public interface IProductPriceService {
	/**
	 * 查询当前product下面的所有价格
	 */
	public List<ProductPrice> findProductPriceListByProductId(String productId)throws YuleException;
	
	/**
	 * 插入product价格
	 */
	public boolean insertProductPrice(ProductPrice productPrice)throws YuleException;
	
	/**
	 * 删除product价格
	 */
	public boolean deleteProductPriceById(String id)throws YuleException;
	
	public boolean updateProductPrice(ProductPrice productPrice) throws YuleException;
	public boolean batchInsertAndUpdateProductPrice(InsertProductPriceParam insertProductPriceParam) throws YuleException;

}
