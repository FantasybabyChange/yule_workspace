package com.yule.mongo.admin.service;

import java.util.List;

import com.yule.exception.YuleException;
import com.yule.mongo.pojo.CompanyCommentPoint;

public interface ICompanyCommentPointMongo {
	public List<CompanyCommentPoint> findCompanyCommentPoint(String order_num)throws YuleException;
}
