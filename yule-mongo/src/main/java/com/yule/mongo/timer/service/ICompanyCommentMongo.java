package com.yule.mongo.timer.service;



import com.yule.mongo.pojo.CompanyComment;
import com.yule.exception.YuleException;
import com.yule.mongo.timer.vo.CompanyCommentVO;

public interface ICompanyCommentMongo {
	
	public CompanyCommentVO findCompanyCommentVO(String companyId) throws YuleException;
	
	public CompanyComment findHighPoint(String companyId) throws Exception ;
	
}
