package com.yule.mongo.pc.service.impl;

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
import com.yule.mongo.pc.service.ICompanyCommentMongo;
import com.yule.mongo.pc.vo.CompanyCommentVO;
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
		if(!StringUtils.isEmpty(CompanyCommentQuery.getOrder_num())){
			query.addCriteria(new Criteria("order_num").is(CompanyCommentQuery.getOrder_num()));
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

	public boolean insertCompanyComment(CompanyComment companyComment) throws YuleException {
		boolean flag = false;
		try{
			if(null==companyComment.getCreate_time()){
				companyComment.setCreate_time(DateUtil.getCurrentDate());
			}
			mongoTemplate.insert(companyComment);
			flag = true;
		}catch(Exception e){
			throw new YuleException(e);
		}
		return flag;
	}

	public CompanyCommentVO findCompanyCommentVO(String companyId) throws YuleException {
		CompanyCommentVO companyCommentVO = new CompanyCommentVO();
		Query query = new Query();
		if(!StringUtils.isEmpty(companyId)){
			query.addCriteria(new Criteria("company_id").is(companyId));
		}
		query.with(new Sort(new Order(Direction.DESC,"point")));  
		query.skip(0).limit(1); 
		long commentCount = this.mongoTemplate.count(query, CompanyComment.class);
		if(commentCount>0){
			companyCommentVO.setPoint(String.valueOf(this.mongoTemplate.findOne(query, CompanyComment.class).getPoint()));
			companyCommentVO.setCompany_comment_count(ConvertUtil.longToInteger(commentCount));
		}else{
			companyCommentVO.setPoint("暂无评分");
			companyCommentVO.setCompany_comment_count(0);
		}
		return companyCommentVO;
	}

}
