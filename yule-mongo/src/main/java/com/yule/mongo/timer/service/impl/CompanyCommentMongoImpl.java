package com.yule.mongo.timer.service.impl;

import org.apache.commons.lang.StringUtils;
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

import com.yule.exception.YuleException;
import com.yule.mongo.constant.CollectionConst;
import com.yule.mongo.pojo.CompanyComment;
import com.yule.mongo.timer.service.ICompanyCommentMongo;
import com.yule.mongo.timer.vo.CompanyCommentVO;

@Repository("companyCommentMongoImpl")
public class CompanyCommentMongoImpl implements ICompanyCommentMongo{

	@Autowired  
    private MongoTemplate mongoTemplate;  
	
	public CompanyCommentVO findCompanyCommentVO(String companyId) throws YuleException {
		Aggregation aggregation = Aggregation.newAggregation(CompanyCommentVO.class,
				Aggregation.project("company_id","point"),
				Aggregation.match(
	               Criteria.where("company_id").is(companyId)	
	            ),
	            Aggregation.group("company_id").count().as("company_comment_count").max("point").as("point")
			);
			AggregationResults<CompanyCommentVO> results = mongoTemplate.aggregate(aggregation,CollectionConst.COMPANY_COMMENT,CompanyCommentVO.class);
			CompanyCommentVO companyCommentVO = new CompanyCommentVO();
			if (null!=results&&null!=results.getUniqueMappedResult()) {
				companyCommentVO= results.getUniqueMappedResult();
			}
		return companyCommentVO;
	}
	
	public CompanyComment findHighPoint(String companyId) throws Exception {
		Query query = new Query();
		if(!StringUtils.isEmpty(companyId)){
			query.addCriteria(new Criteria("company_id").is(companyId));
		}
		query.with(new Sort(new Order(Direction.DESC,"point")));
		return  this.mongoTemplate.findOne(query, CompanyComment.class);
	}

}
