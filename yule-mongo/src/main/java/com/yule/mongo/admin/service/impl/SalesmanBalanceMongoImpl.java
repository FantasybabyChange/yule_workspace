package com.yule.mongo.admin.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.yule.enumerate.DateStyle;
import com.yule.exception.YuleException;
import com.yule.mongo.admin.query.SalesmanBalanceQuery;
import com.yule.mongo.admin.service.ISalesmanBalanceMongo;
import com.yule.mongo.admin.vo.SalesmanBalanceVO;
import com.yule.mongo.pojo.SalesmanBalance;
import com.yule.util.ConvertUtil;
import com.yule.util.DateUtil;
import com.yule.vo.Page;



@Repository("salesmanBalanceMongoImpl")
public class SalesmanBalanceMongoImpl implements ISalesmanBalanceMongo{

	@Autowired  
    private MongoTemplate mongoTemplate;
	


	public Page<SalesmanBalanceVO> findSalesmanBalancePageByQuery(SalesmanBalanceQuery salesmanBalanceQuery,int pageSize, int pageNo)throws YuleException{
		Query query = new Query();
		if(!StringUtils.isEmpty(salesmanBalanceQuery.getSalesman_id())){
			query.addCriteria(new Criteria("salesman_id").is(salesmanBalanceQuery.getSalesman_id()));
		}
		if(null!=salesmanBalanceQuery.getPay_status()){
			query.addCriteria(new Criteria("pay_status").is(salesmanBalanceQuery.getPay_status()));
		}
	
		Criteria createTime = null;
		if(!StringUtils.isEmpty(salesmanBalanceQuery.getStart_time())){
			if(createTime==null){
				createTime = new Criteria("create_time");
			}
			createTime.gte(DateUtil.StringToDate(salesmanBalanceQuery.getStart_time(),DateStyle.YYYY_MM_DD_HH_MM_SS_EN));
		}
		if(!StringUtils.isEmpty(salesmanBalanceQuery.getEnd_time())){
			if(createTime==null){
				createTime = new Criteria("create_time");
			}
			createTime.lte(DateUtil.StringToDate(salesmanBalanceQuery.getEnd_time(),DateStyle.YYYY_MM_DD_HH_MM_SS_EN));
		}
		if(null!=createTime){
			query.addCriteria(createTime);
		}
		query.with(new Sort(new Order(Direction.DESC,"create_time")));  
		query.skip((pageNo-1)*pageSize).limit(pageSize); 
		Page<SalesmanBalanceVO> page = new Page<SalesmanBalanceVO>();
		page.setDatas(this.mongoTemplate.find(query, SalesmanBalanceVO.class));
		page.setPageNo(pageNo);
		page.setRowCount(ConvertUtil.longToInteger(this.mongoTemplate.count(query, SalesmanBalanceVO.class)));
		return page;
	}

	public boolean updateSalesmanBalance(SalesmanBalance salesmanBalance)throws YuleException {
		boolean flag = false;
		Query query = new Query();  
		query.addCriteria(new Criteria("_id").is(salesmanBalance.getId()));  
        Update update = new Update();  
		if(null!=salesmanBalance.getPay_status()){
			update.set("pay_status", salesmanBalance.getPay_status());
			update.set("pay_time", salesmanBalance.getPay_time());
		}
		try{
			this.mongoTemplate.updateFirst(query, update, SalesmanBalance.class);  
			flag = true;
		}catch(Exception e){
			throw new YuleException(e);
		}
		return flag;
	}

}
