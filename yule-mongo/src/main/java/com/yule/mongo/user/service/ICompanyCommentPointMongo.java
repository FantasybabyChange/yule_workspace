package com.yule.mongo.user.service;

import java.util.List;

import com.yule.exception.YuleException;
import com.yule.mongo.pojo.CompanyCommentPoint;

public interface ICompanyCommentPointMongo {
	public boolean insertCompanyPoint(List<CompanyCommentPoint> companyPoint)throws YuleException;
}
