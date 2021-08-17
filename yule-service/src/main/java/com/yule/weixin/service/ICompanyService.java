package com.yule.weixin.service;

import com.yule.exception.YuleException;

public interface ICompanyService {
	
	public boolean updateCompanyOpenId(String companyId,String openId) throws YuleException;
	
}
