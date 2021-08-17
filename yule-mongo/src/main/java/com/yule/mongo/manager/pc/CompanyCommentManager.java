//package com.yule.mongo.manager.pc;
//
//import java.util.Date;
//
//import org.bson.types.ObjectId;
//import org.mongodb.morphia.Datastore;
//import org.mongodb.morphia.query.Query;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.util.StringUtils;
//
//import com.yule.enumerate.DateStyle;
//import com.yule.mongo.dao.MongoDao;
//import com.yule.mongo.pc.query.CompanyCommentQuery;
//import com.yule.mongo.pojo.CompanyComment;
//import com.yule.util.DateUtil;
//import com.yule.vo.Page;
//
//public class CompanyCommentManager extends MongoDao<CompanyComment, ObjectId>{
//
//    public CompanyCommentManager(Datastore ds) {
//        super(ds);
//        this.entityClazz = CompanyComment.class;
//    }
//    
//    public Page<CompanyComment> findCompanyCommentPage(CompanyCommentQuery companyCommentQuery,int pageSize,int pageNo) throws YuleException{
//		Query<CompanyComment> query = this.ds.createQuery(CompanyComment.class);
//		if(!StringUtils.isEmpty(companyCommentQuery.getCompany_id())){
//			query.field("company_id").equal(companyCommentQuery.getCompany_id());
//		}
//		if(!StringUtils.isEmpty(companyCommentQuery.getCategory_id())){
//			query.field("category_id").equal(companyCommentQuery.getCategory_id());
//		}
//		if(!StringUtils.isEmpty(companyCommentQuery.getUser_id())){
//			query.field("user_id").equal(companyCommentQuery.getUser_id());
//		}
//		Page<CompanyComment> page = new Page<CompanyComment>();
//		page.setDatas(query.offset((pageNo-1)*pageSize).limit(pageSize*pageNo).order("create_time").asList());
//		page.setPageNo(pageNo);
//		page.setRowCount(Integer.parseInt(String.valueOf(this.count(query))));
//		return page;
//	}
//	
//	public boolean insertCompanyComment(CompanyComment companyComment)  throws YuleException {
//		boolean flag = false;
//		try{
//			if(StringUtils.isEmpty(companyComment.getCreate_time())){
//				companyComment.setCreate_time(DateUtil.DateToString(new Date(), DateStyle.YYYY_MM_DD_HH_MM_EN));
//			}
//			this.save(companyComment);
//			flag = true;
//		}catch(YuleException e){
//			new YuleException(e);
//			throw e;
//		}
//		return flag;
//	}
//	
//
//}
