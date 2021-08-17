package com.yule.user.service;

import com.yule.user.vo.CompanyVO;
import com.yule.exception.YuleException;

public interface ICompanyService {
	public CompanyVO findCompanyVOById(String id)throws YuleException;
}
