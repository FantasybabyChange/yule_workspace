package com.yule.mongo.company.service;


import java.util.List;

import com.yule.exception.YuleException;
import com.yule.mongo.pojo.CompanyComment;
import com.yule.mongo.query.CompanyCommentQuery;
import com.yule.vo.Page;

public interface ICompanyCommentMongo {
	
	public Page<CompanyComment> findCompanyCommentPage(CompanyCommentQuery comapnyCommentQuery,int pageSize,int pageNo) throws YuleException;
	
	public List<CompanyComment> findCompanyComment(String companyId,int pageSize,int pageNo) throws YuleException ;
	
	public CompanyComment findCompanyCommentByOrderNum(String order_num) throws YuleException ;
	
}
