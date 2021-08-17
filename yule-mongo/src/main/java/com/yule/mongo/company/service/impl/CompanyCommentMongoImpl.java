package com.yule.mongo.company.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.yule.enumerate.DateStyle;
import com.yule.exception.YuleException;
import com.yule.mongo.company.service.ICompanyCommentMongo;
import com.yule.mongo.pojo.CompanyComment;
import com.yule.mongo.query.CompanyCommentQuery;
import com.yule.util.ConvertUtil;
import com.yule.util.DateUtil;
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
			query.addCriteria(new Criteria("title").regex(CompanyCommentQuery.getTitle()));
		}
		if(!StringUtils.isEmpty(CompanyCommentQuery.getTitle())){
			query.addCriteria(new Criteria("user_name").regex(CompanyCommentQuery.getUser_name()));
		}
		if(!StringUtils.isEmpty(CompanyCommentQuery.getUser_id())){
			query.addCriteria(new Criteria("user_id").is(CompanyCommentQuery.getUser_id()));
		}
		Criteria createTime = null;
		if(!StringUtils.isEmpty(CompanyCommentQuery.getStart_time())){
			if(createTime==null){
				createTime = new Criteria("create_time");
			}
			createTime.gte(DateUtil.StringToDate(CompanyCommentQuery.getStart_time(),DateStyle.YYYY_MM_DD_HH_MM_SS_EN));
		}
		if(!StringUtils.isEmpty(CompanyCommentQuery.getEnd_time())){
			if(createTime==null){
				createTime = new Criteria("create_time");
			}
			createTime.lte(DateUtil.StringToDate(CompanyCommentQuery.getEnd_time(),DateStyle.YYYY_MM_DD_HH_MM_SS_EN));
		}
		if(null!=createTime){
			query.addCriteria(createTime);
		}
		query.with(new Sort(new Order(Direction.DESC,"create_time")));  
		query.skip((pageNo-1)*pageSize).limit(pageSize); 
		Page<CompanyComment> page = new Page<CompanyComment>();
		page.setDatas(this.mongoTemplate.find(query, CompanyComment.class));
		page.setPageNo(pageNo);
		page.setRowCount(ConvertUtil.longToInteger(this.mongoTemplate.count(query, CompanyComment.class)));
		return page;
	}
	public List<CompanyComment> findCompanyComment(String companyId,int pageSize,int pageNo) throws YuleException {
		Query query = new Query();
		if(!StringUtils.isEmpty(companyId)){
			query.addCriteria(new Criteria("company_id").is(companyId));
		}
		query.with(new Sort(new Order(Direction.DESC,"create_time")));
		query.skip((pageNo-1)*pageSize).limit(pageSize); 
		return  this.mongoTemplate.find(query, CompanyComment.class);
	}
	
	public CompanyComment findCompanyCommentByOrderNum(String order_num)throws YuleException {
		Query query = new Query();
		query.addCriteria(new Criteria("order_num").is(order_num));
		return  this.mongoTemplate.findOne(query, CompanyComment.class);
	}
}
