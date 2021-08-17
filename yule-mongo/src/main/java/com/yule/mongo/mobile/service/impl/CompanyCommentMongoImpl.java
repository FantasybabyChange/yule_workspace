package com.yule.mongo.mobile.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.yule.exception.YuleException;
import com.yule.mongo.constant.CollectionConst;
import com.yule.mongo.mobile.service.ICompanyCommentMongo;
import com.yule.mongo.pojo.CompanyComment;
import com.yule.mongo.mobile.vo.CompanyCommentVO;
import com.yule.util.ConvertUtil;
import com.yule.vo.Page;

@Repository("companyCommentMongoImpl")
public class CompanyCommentMongoImpl implements ICompanyCommentMongo{
	
	@Autowired  
    private MongoTemplate mongoTemplate; 
	
	public List<CompanyComment> findCompanyComment(String companyId,int pageSize,int pageNo) throws YuleException {
		Query query = new Query();
		if(!StringUtils.isEmpty(companyId)){
			query.addCriteria(new Criteria("company_id").is(companyId));
		}
		if(pageSize != 0){
		query.with(new Sort(new Order(Direction.DESC,"create_time")));
		query.skip((pageNo-1)*pageSize).limit(pageSize);
		}
		return  this.mongoTemplate.find(query, CompanyComment.class);
	}
	
	public int findCompanyCommentNum(String companyId)throws YuleException {
		Query query = new Query();
		query.addCriteria(new Criteria("company_id").is(companyId));
		return ConvertUtil.longToInteger(this.mongoTemplate.count(query, CompanyComment.class));
	}
	
	public CompanyComment findHighPoint(String companyId) throws YuleException {
		Query query = new Query();
		if(!StringUtils.isEmpty(companyId)){
			query.addCriteria(new Criteria("company_id").is(companyId));
		}
		query.with(new Sort(new Order(Direction.DESC,"point")));
		return  this.mongoTemplate.findOne(query, CompanyComment.class);
	}
	
	public Page<CompanyComment> findCompanyCommentPage(String companyId,Integer type,
			int pageSize, int pageNo) throws YuleException {
		Query query = new Query();
		if(!StringUtils.isEmpty(companyId)){
			query.addCriteria(new Criteria("company_id").is(companyId));
		}
		if(!StringUtils.isEmpty(type)){
			query.addCriteria(new Criteria("type").is(type));
		}
		query.with(new Sort(new Order(Direction.DESC,"create_time")));
		query.skip((pageNo-1)*pageSize).limit(pageSize);
		Page<CompanyComment> page = new Page<CompanyComment>();
		page.setPageNo(pageNo);
		page.setPageSize(pageSize);
		page.setDatas(this.mongoTemplate.find(query, CompanyComment.class));
		page.setRowCount(ConvertUtil.longToInteger(this.mongoTemplate.count(query, CompanyComment.class)));
		return page;
	}
	public CompanyCommentVO findCompanyCommentVO(String companyId) throws YuleException {
		Aggregation aggregation = Aggregation.newAggregation(CompanyCommentVO.class,
				Aggregation.project("company_id","point"),
				Aggregation.match(
	               Criteria.where("company_id").is(companyId)	
	            ),
	            Aggregation.group("company_id").count().as("count").max("point").as("point")
			);
			AggregationResults<CompanyCommentVO> results = mongoTemplate.aggregate(aggregation,CollectionConst.COMPANY_COMMENT,CompanyCommentVO.class);
			CompanyCommentVO companyCommentVO = new CompanyCommentVO();
			if (null!=results&&null!=results.getUniqueMappedResult()) {
				companyCommentVO= results.getUniqueMappedResult();
			}
		return companyCommentVO;
	}
}
