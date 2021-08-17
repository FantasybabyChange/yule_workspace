package com.yule.mongo.detail.service;

import java.util.List;

import com.yule.exception.YuleException;
import com.yule.mongo.detail.vo.CompanyCommentPointVO;
import com.yule.mongo.pojo.CompanyCommentPoint;

public interface ICompanyCommentPointMongo {
	
	public List<CompanyCommentPointVO> findCompanyPointAvg(String companyId) throws YuleException;

	public boolean insertCompanyPoint(CompanyCommentPoint companyPoint)throws YuleException;
}
