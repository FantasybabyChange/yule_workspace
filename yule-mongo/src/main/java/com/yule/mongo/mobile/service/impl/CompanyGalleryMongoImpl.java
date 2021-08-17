package com.yule.mongo.mobile.service.impl;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.yule.constant.DeleteConst;
import com.yule.exception.YuleException;
import com.yule.mongo.mobile.service.ICompanyGalleryMongo;
import com.yule.mongo.pojo.CompanyGallery;
import com.yule.util.ConvertUtil;
import com.yule.vo.Page;

@Service("companyGalleryMongoImpl")
public class CompanyGalleryMongoImpl implements ICompanyGalleryMongo{
	@Autowired  
    private MongoTemplate mongoTemplate;
	public CompanyGallery findCompanyGalleryById(String id) throws YuleException {
		return this.mongoTemplate.findById(new ObjectId(id), CompanyGallery.class);
	}

	public Page<CompanyGallery> findCompanyGalleryPageByCompanyId(String companyId,int pageSize, int pageNo) throws YuleException {
		Query query=new Query();
		if (!StringUtils.isEmpty(companyId)) {
			query.addCriteria(new Criteria("company_id").is(companyId));
		}
		query.addCriteria(new Criteria("is_delete").is(DeleteConst.IS_DELETE_TRUE));
		query.skip((pageNo-1)*pageSize).limit(pageSize); 
		Page<CompanyGallery> page = new Page<CompanyGallery>();
		query.with(new Sort(new Order(Direction.DESC,"create_time")));  
		page.setDatas(this.mongoTemplate.find(query, CompanyGallery.class));
		page.setPageNo(pageNo);
		page.setRowCount(ConvertUtil.longToInteger(this.mongoTemplate.count(query, CompanyGallery.class)));
		return page;
	}

}
