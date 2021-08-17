package com.yule.admin.service;

import java.util.List;

import com.yule.exception.YuleException;
import com.yule.param.InsertProductParam;
import com.yule.pojo.Product;
import com.yule.vo.ProductVO;
public interface IProductService {

	public boolean insertProduct(Product product) throws YuleException;

	public boolean updateProduct(Product product) throws YuleException;

	public boolean deleteProductById(String id) throws YuleException;
	
	public Product findProductById(String id)throws YuleException;

	public boolean batchInsertAndUpdateProduct(InsertProductParam insertProductParam) throws YuleException;
	
	public List<ProductVO> findProductVOList(String companyId) throws YuleException;
	
}
