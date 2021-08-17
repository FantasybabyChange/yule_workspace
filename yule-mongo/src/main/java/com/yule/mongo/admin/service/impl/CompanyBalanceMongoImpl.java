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
import com.yule.mongo.admin.query.CompanyBalanceQuery;
import com.yule.mongo.admin.service.ICompanyBalanceMongo;
import com.yule.mongo.admin.vo.CompanyBalanceVO;
import com.yule.mongo.pojo.CompanyBalance;
import com.yule.util.ConvertUtil;
import com.yule.util.DateUtil;
import com.yule.vo.Page;


@Repository("companyBalanceMongoImpl")
public class CompanyBalanceMongoImpl implements ICompanyBalanceMongo{
	
	@Autowired  
    private MongoTemplate mongoTemplate; 



	public Page<CompanyBalanceVO> findHistryCompanyBalancePage(CompanyBalanceQuery balanceQuery,int pageSize,int pageNo) throws YuleException{
		Query query = new Query();
		if(!StringUtils.isEmpty(balanceQuery.getCompany_id())){
			query.addCriteria(new Criteria("company_id").is(balanceQuery.getCompany_id()));
		}
		if(null!=balanceQuery.getPay_status()){
			query.addCriteria(new Criteria("pay_status").is(balanceQuery.getPay_status()));
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



	public boolean updateCompanyBalance(CompanyBalance companyBalance) throws YuleException{
		boolean flag = false;
		Query query = new Query();  
		query.addCriteria(new Criteria("_id").is(companyBalance.getId()));  
        Update update = new Update();  
		if(null!=companyBalance.getPay_status()){
			update.set("pay_status", companyBalance.getPay_status());
			update.set("pay_time", companyBalance.getPay_time());
		}
		try{
			this.mongoTemplate.updateFirst(query, update, CompanyBalance.class);  
			flag = true;
		}catch(Exception e){
			throw new YuleException(e);
		}
		return flag;
	}

}
