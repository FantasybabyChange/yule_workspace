package com.yule.mongo.company.service.impl;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.aggregation.MatchOperation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.yule.exception.YuleException;
import com.yule.mongo.company.service.ICompanyCommentPointMongo;
import com.yule.mongo.company.vo.CompanyCommentPointVO;
import com.yule.mongo.constant.CollectionConst;
import com.yule.mongo.pojo.CompanyCommentPoint;
import com.yule.mongo.query.CompanyCommentQuery;
import com.yule.mongo.vo.CountVO;
import com.yule.vo.Page;

@Repository("companyCommentPointMongoImpl")
public  class CompanyCommentPointMongoImpl implements ICompanyCommentPointMongo{
	@Autowired  
    private MongoTemplate mongoTemplate;
	public Page<CompanyCommentPointVO> findCompanyCommentPointList(CompanyCommentQuery  companyCommentQuery,int pageNo,int pageSize)throws YuleException {
		MatchOperation matchOperation =new MatchOperation(Criteria.where("company_id").is(companyCommentQuery.getCompany_id()).and("user_name").regex(companyCommentQuery.getUser_name()));
		Aggregation aggregation = Aggregation.newAggregation(
				CompanyCommentPointVO.class,
				Aggregation.project("company_id","order_num","point","create_time","user_name","user_id","company_comment_category_id","company_comment_category_name"),
				matchOperation,
	            Aggregation.group("order_num").push("company_comment_category_name").as("name")
	            .push("point").as("point")
	            .last("order_num").as("order_num")
	            .last("user_name").as("user_name")
	            .last("user_id").as("user_id")
	            .last("create_time").as("create_time"),
	            Aggregation.skip((pageNo-1)*pageSize),
		        Aggregation.limit(pageSize)
		);
		AggregationResults<CompanyCommentPointVO> results = mongoTemplate.aggregate(aggregation,CollectionConst.COMPANY_COMMENT_POINT,CompanyCommentPointVO.class);
		Aggregation count = Aggregation.newAggregation(CompanyCommentPointVO.class,
				Aggregation.project("company_id","order_num","user_name"),
				matchOperation,
	            Aggregation.group("order_num").count().as("count")
			);
		Page<CompanyCommentPointVO> page =  new Page<CompanyCommentPointVO>();
		AggregationResults<CountVO> counts = mongoTemplate.aggregate(count,CollectionConst.COMPANY_COMMENT_POINT,CountVO.class);
		if (results.getMappedResults() != null) {
			page.setDatas(new ArrayList<CompanyCommentPointVO>(results.getMappedResults()));
			page.setPageSize(pageSize);
			page.setPageNo(pageNo);
			page.setRowCount(counts.getMappedResults().size());
			results = null;
			counts =null;
		}
			return page;
	}
	public List<CompanyCommentPoint> findCompanyCommentPoint(String order_num)throws YuleException {
		Query q = new Query();
		q.addCriteria(new Criteria("order_num").is((order_num)));
		return this.mongoTemplate.find(q, CompanyCommentPoint.class);
		}
	}
