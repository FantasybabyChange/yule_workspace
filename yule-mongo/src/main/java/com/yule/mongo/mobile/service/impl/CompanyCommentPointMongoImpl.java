package com.yule.mongo.mobile.service.impl;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.yule.exception.YuleException;
import com.yule.mongo.constant.CollectionConst;
import com.yule.mongo.mobile.service.ICompanyCommentPointMongo;
import com.yule.mongo.mobile.vo.CompanyCommentPointVO;
import com.yule.mongo.pojo.CompanyCommentPoint;
import com.yule.util.DateUtil;

@Repository("companyPointMongoImpl")
public  class CompanyCommentPointMongoImpl implements ICompanyCommentPointMongo{
	@Autowired  
    private MongoTemplate mongoTemplate;

	public List<CompanyCommentPointVO> findCompanyPointAvg(String companyId)throws YuleException {
		Aggregation aggregation = Aggregation.newAggregation(CompanyCommentPoint.class,
				Aggregation.project("company_id","point","company_comment_category_name","company_comment_category_id"),
				Aggregation.match(
	               Criteria.where("company_id").is(companyId)	
	            ),
	            Aggregation.group("company_comment_category_name").avg("point").as("point")
	            .last("company_comment_category_name").as("name")
			);
			AggregationResults<CompanyCommentPointVO> results = mongoTemplate.aggregate(aggregation,CollectionConst.COMPANY_COMMENT_POINT,CompanyCommentPointVO.class);
			List<CompanyCommentPointVO> companyCommentPointVOs = null;
			if (null!=results.getMappedResults()) {
				companyCommentPointVOs = new ArrayList<CompanyCommentPointVO>(results.getMappedResults());
			}
		return companyCommentPointVOs;
	
	}

	public boolean insertCompanyPoint(CompanyCommentPoint companyPoint)throws YuleException{
		boolean flag = false;
		try {
			if(StringUtils.isEmpty(companyPoint.getCreate_time())){
				companyPoint.setCreate_time(DateUtil.getCurrentDate());
			}
			this.mongoTemplate.save(companyPoint);
			flag = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}
}
