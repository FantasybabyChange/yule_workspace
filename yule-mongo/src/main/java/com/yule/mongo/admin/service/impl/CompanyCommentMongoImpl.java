package com.yule.mongo.admin.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.yule.exception.YuleException;
import com.yule.mongo.admin.service.ICompanyCommentMongo;
import com.yule.mongo.pojo.CompanyComment;
import com.yule.mongo.query.CompanyCommentQuery;
import com.yule.util.ConvertUtil;
import com.yule.vo.Page;

@Repository("companyCommentMongoImpl")
public class CompanyCommentMongoImpl implements ICompanyCommentMongo{

	@Autowired  
    private MongoTemplate mongoTemplate;  
	
	public Page<CompanyComment> findCompanyCommentPage(CompanyCommentQuery CompanyCommentQuery,int pageSize,int pageNo) throws YuleException{
		Query query = new Query();
		if(!StringUtils.isEmpty(CompanyCommentQuery.getCompany_id())){
			query.addCriteria(new Criteria("company_id").is(CompanyCommentQuery.getCompany_id()));
		}
		if(!StringUtils.isEmpty(CompanyCommentQuery.getTitle())){
			query.addCriteria(new Criteria("title").in(CompanyCommentQuery.getTitle()));
		}
		if(!StringUtils.isEmpty(CompanyCommentQuery.getUser_id())){
			query.addCriteria(new Criteria("user_id").is(CompanyCommentQuery.getUser_id()));
		}
		if(!StringUtils.isEmpty(CompanyCommentQuery.getStart_time())){
			query.addCriteria(new Criteria("create_time").gte(CompanyCommentQuery.getStart_time()));
		}
		if(!StringUtils.isEmpty(CompanyCommentQuery.getEnd_time())){
			query.addCriteria(new Criteria("create_time").lte(CompanyCommentQuery.getEnd_time()));
		}
		query.with(new Sort(new Order(Direction.DESC,"create_time")));  
		query.skip((pageNo-1)*pageSize).limit(pageSize); 
		Page<CompanyComment> page = new Page<CompanyComment>();
		page.setDatas(this.mongoTemplate.find(query, CompanyComment.class));
		page.setPageNo(pageNo);
		page.setRowCount(ConvertUtil.longToInteger(this.mongoTemplate.count(query, CompanyComment.class)));
		return page;
	}

}
