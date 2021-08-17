package com.yule.mongo.company.service;

import com.yule.exception.YuleException;
import com.yule.mongo.param.InsertProductGalleryParam;
import com.yule.mongo.pojo.ProductGallery;
import com.yule.vo.Page;

public interface IProductGalleryMongo {
	
	public boolean insertProductGallery(ProductGallery productGallery) throws YuleException;
	
	public String batchInsertProductGallery(InsertProductGalleryParam insertProductGalleryParam) throws YuleException;

	public Page<ProductGallery> findProductGalleryPageByProductId(String product_id,int pageSize,int pageNo) throws YuleException;

	public boolean deleteProductGalleryById(String id) throws YuleException;
}
