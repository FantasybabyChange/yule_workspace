package com.yule.mongo.company.service.impl;

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

import com.yule.constant.DeleteConst;
import com.yule.constant.OrdersConst;
import com.yule.enumerate.DateStyle;
import com.yule.exception.YuleException;
import com.yule.mongo.company.query.BalanceQuery;
import com.yule.mongo.company.service.ICompanyBalanceMongo;
import com.yule.mongo.company.vo.CompanyBalanceVO;
import com.yule.mongo.constant.CollectionConst;
import com.yule.mongo.pojo.Orders;
import com.yule.mongo.timer.vo.CompanyOrdersBalanceVO;
import com.yule.util.ConvertUtil;
import com.yule.util.DateUtil;
import com.yule.vo.Page;

@Repository("companyBalanceMongoImpl")
public class CompanyBalanceMongoImpl implements ICompanyBalanceMongo{
	
	@Autowired  
    private MongoTemplate mongoTemplate; 

/*	public CompanyBalanceVO findCompanyBalanceByCompanyId(String companyId)throws YuleException {
		Query query = new Query();
		if(!StringUtils.isEmpty(companyId)){
			query.addCriteria(new Criteria("company_id").is(companyId));
		}
		query.with(new Sort(new Order(Direction.DESC,"create_time"))); 
		query.skip(0);
		query.limit(1);
		return this.mongoTemplate.findOne(query, CompanyBalanceVO.class);
	}*/

	public Page<CompanyBalanceVO> findHistryCompanyBalancePage(BalanceQuery balanceQuery,int pageSize,int pageNo) throws YuleException{
		Query query = new Query();
		if(!StringUtils.isEmpty(balanceQuery.getCompany_id())){
			query.addCriteria(new Criteria("company_id").is(balanceQuery.getCompany_id()));
		}
		Criteria createTime = null;
		if(!StringUtils.isEmpty(balanceQuery.getStart_time())){
			if(createTime==null){
				createTime = new Criteria("create_time");
			}
			createTime.gte(DateUtil.StringToDate(balanceQuery.getStart_time(),DateStyle.YYYY_MM_DD_HH_MM_SS_EN));
		}
		if(!StringUtils.isEmpty(balanceQuery.getEnd_time())){
			if(createTime==null){
				createTime = new Criteria("create_time");
			}
			createTime.lte(DateUtil.StringToDate(balanceQuery.getEnd_time(),DateStyle.YYYY_MM_DD_HH_MM_SS_EN));
		}
		if(null!=createTime){
			query.addCriteria(createTime);
		}
		query.with(new Sort(new Order(Direction.DESC,"create_time")));  
		query.skip((pageNo-1)*pageSize).limit(pageSize); 
		Page<CompanyBalanceVO> page = new Page<CompanyBalanceVO>();
		page.setDatas(this.mongoTemplate.find(query, CompanyBalanceVO.class));
		page.setPageNo(pageNo);
		page.setRowCount(ConvertUtil.longToInteger(this.mongoTemplate.count(query, CompanyBalanceVO.class)));
		return page;
	}

	public CompanyOrdersBalanceVO findNowWeekCompanyBalanceByQuery(String companyId ,String start_time,String end_time) throws YuleException {
		Aggregation aggregation = Aggregation.newAggregation(Orders.class,
				Aggregation.project("company_id","status","is_delete","orders_count","user_score","expense_money","create_time"),
				Aggregation.match(
	               Criteria.where("company_id").is(companyId).and("status").is(OrdersConst.ORDERS_STATUS_THREE).and("is_delete").is(DeleteConst.IS_DELETE_TRUE)
	               .and("create_time").gte(DateUtil.StringToDate(start_time)).lte(DateUtil.StringToDate(end_time,DateStyle.YYYY_MM_DD_HH_MM_SS_EN))
	            ),
	            Aggregation.group("company_id").count().as("orders_count").sum("expense_money").as("orders_expense_sum")
	            .sum("user_score").as("orders_score_sum").last("company_id").as("company_id")
			);
		AggregationResults<CompanyOrdersBalanceVO> results = mongoTemplate.aggregate(aggregation,CollectionConst.ORDERS,CompanyOrdersBalanceVO.class);
		return results.getUniqueMappedResult();
	}

}
