package com.yule.mongo.pc.service;


import com.yule.exception.YuleException;
import com.yule.mongo.pc.vo.CompanyCommentVO;
import com.yule.mongo.pojo.CompanyComment;
import com.yule.mongo.query.CompanyCommentQuery;
import com.yule.vo.Page;

public interface ICompanyCommentMongo {
	
	public Page<CompanyComment> findCompanyCommentPage(CompanyCommentQuery comapnyCommentQuery,int pageSize,int pageNo) throws YuleException;
	
	public boolean insertCompanyComment(CompanyComment companyComment) throws YuleException;
	
	public CompanyCommentVO findCompanyCommentVO(String companyId) throws YuleException;
	
}
