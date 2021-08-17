package com.yule.mongo.company.service;

import com.yule.exception.YuleException;
import com.yule.mongo.param.InsertCompanyGalleryParam;
import com.yule.mongo.pojo.CompanyGallery;
import com.yule.vo.Page;

public interface ICompanyGalleryMongo {

	public String batchInsertCompanyGallery(InsertCompanyGalleryParam insertCompanyGalleryParam) throws YuleException;
	
	public boolean deleteCompanyGalleryById(String id) throws YuleException;

	public CompanyGallery findCompanyGalleryById(String id) throws YuleException;

	public Page<CompanyGallery> findCompanyGalleryPageByCompanyId(String companyId,int pageSize,int pageNo) throws YuleException;
	
}
