package com.yule.mongo.timer.service;

import com.yule.exception.YuleException;


public interface ICompanyGalleryMongo {

	public int findCompanyGalleryCountByCompanyId(String companyId) throws YuleException;
	
}
