package com.yule.admin.service;

import java.util.List;

import com.yule.admin.param.InsertProductServeParam;
import com.yule.exception.YuleException;
import com.yule.pojo.ProductServe;

/**
 * 产品服务设施
 */
public interface IProductServeService {
	
	public boolean insertProductServe(ProductServe productServe) throws YuleException;

	public boolean updateProductServe(ProductServe productServe) throws YuleException;

	public boolean deleteProductServeById(String id) throws YuleException;
	
	public List<ProductServe> findProductServeList() throws YuleException;

	public boolean batchUpdateAndInsertProductServe(InsertProductServeParam productServeParam)throws YuleException;
    
}