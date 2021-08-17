package com.yule.mongo.mobile.service;


import java.util.List;

import com.yule.exception.YuleException;
import com.yule.mongo.pojo.CompanyComment;
import com.yule.mongo.mobile.vo.CompanyCommentVO;
import com.yule.vo.Page;

public interface ICompanyCommentMongo {
	public List<CompanyComment> findCompanyComment(String companyId,int pageSize,int pageNo) throws YuleException ;
	
	public Page<CompanyComment> findCompanyCommentPage(String companyId,Integer type,int pageSize,int pageNo) throws YuleException ;
	//查询所有评论数量
	public int findCompanyCommentNum(String companyId) throws YuleException ;
	//查询最高分
	public CompanyComment findHighPoint(String companyId) throws YuleException ;
	
	public CompanyCommentVO findCompanyCommentVO(String companyId) throws YuleException;
}
