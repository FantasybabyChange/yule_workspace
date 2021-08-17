package com.yule.mongo.timer.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.yule.constant.DeleteConst;
import com.yule.exception.YuleException;
import com.yule.mongo.pojo.CompanyGallery;
import com.yule.mongo.timer.service.ICompanyGalleryMongo;
import com.yule.util.ConvertUtil;

@Service("companyGalleryMongoImpl")
public class CompanyGalleryMongoImpl implements ICompanyGalleryMongo{

	@Autowired  
    private MongoTemplate mongoTemplate;

	public int findCompanyGalleryCountByCompanyId(String companyId) throws YuleException {
		Query query=new Query();
		if (!StringUtils.isEmpty(companyId)) {
			query.addCriteria(new Criteria("company_id").is(companyId));
		}
		query.addCriteria(new Criteria("is_delete").is(DeleteConst.IS_DELETE_TRUE));
		return ConvertUtil.longToInteger(this.mongoTemplate.count(query, CompanyGallery.class));
	}

}
