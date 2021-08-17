package com.yule.mongo.mobile.service;

import com.yule.exception.YuleException;
import com.yule.mongo.pojo.CompanyGallery;
import com.yule.vo.Page;

public interface ICompanyGalleryMongo {
	public Page<CompanyGallery> findCompanyGalleryPageByCompanyId(String companyId,int pageSize,int pageNo) throws YuleException;
}
