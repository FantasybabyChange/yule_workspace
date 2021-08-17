package com.yule.admin.service;

import java.util.List;

import com.yule.admin.param.InsertProductCategoryParam;
import com.yule.exception.YuleException;
import com.yule.pojo.ProductCategory;

public interface IProductCategoryService {

	public boolean insertProductCategory(ProductCategory productCategory) throws YuleException;

	public boolean updateProductCategory(ProductCategory productCategory) throws YuleException;

	public boolean deleteProductCategoryById(String id) throws YuleException;
	
	public boolean deleteProductCategoryAll() throws YuleException;

	public List<ProductCategory> findProductCategoryList(String companyCategoryId)throws YuleException;
	
    public boolean batchInsertAndUpdateProductCategory(InsertProductCategoryParam insertProductCategoryParam) throws YuleException;
    
    public boolean batchInsertProductCategory(List<ProductCategory> productCategorys) throws YuleException;

}
