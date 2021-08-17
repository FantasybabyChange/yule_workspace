package com.yule.mongo.user.service;


import com.yule.exception.YuleException;
import com.yule.mongo.pojo.CompanyComment;

public interface ICompanyCommentMongo {
	public boolean insertCompanyComment(CompanyComment companyComment) throws YuleException;
	
	
}
