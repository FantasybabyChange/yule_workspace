package com.yule.mongo.company.service;

import java.util.List;

import com.yule.exception.YuleException;
import com.yule.mongo.company.vo.CompanyCommentPointVO;
import com.yule.mongo.pojo.CompanyCommentPoint;
import com.yule.mongo.query.CompanyCommentQuery;
import com.yule.vo.Page;

public interface ICompanyCommentPointMongo {
	public Page<CompanyCommentPointVO> findCompanyCommentPointList(CompanyCommentQuery  companyCommentQuery,int pageNo,int pageSize)throws YuleException;
	public List<CompanyCommentPoint> findCompanyCommentPoint(String order_num)throws YuleException;
}
