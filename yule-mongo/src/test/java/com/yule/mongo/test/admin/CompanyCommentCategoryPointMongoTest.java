//package com.yule.mongo.test.admin;
//
//import java.util.List;
//
//import junit.framework.Test;
//import junit.framework.TestCase;
//import junit.framework.TestSuite;
//
//import org.springframework.context.ApplicationContext;
//
//import com.yule.constant.CustomBeanConst;
//import com.yule.mongo.admin.query.CompanyCommentCategoryPointQuery;
//import com.yule.mongo.admin.service.ICompanyCommentCategoryPointMongo;
//import com.yule.mongo.admin.vo.CompanyCommentCategoryPointVO;
//import com.yule.mongo.pojo.CompanyCommentCategoryPoint;
//import com.yule.util.CustomBeanFactory;
//
//public class CompanyCommentCategoryPointMongoTest extends TestCase {
//
//	private ICompanyCommentCategoryPointMongo companyCommentCategoryPointMongoImpl = null;
//	
//	public CompanyCommentCategoryPointMongoTest(String name) {
//		super(name);
//		ApplicationContext context = CustomBeanFactory.getContext(CustomBeanConst.ADMIN_MONGO_PATHS);
//		companyCommentCategoryPointMongoImpl = (ICompanyCommentCategoryPointMongo) context.getBean("companyCommentCategoryPointMongoImpl");
//	}
//
//	public static Test suite() {
//		TestSuite test = new TestSuite("CompanyCommentCategoryPointManagerTest接口测试");
//		
////		test.addTest(new CompanyCommentCategoryPointMongoTest("insertCompanyCommentCategoryPoint"));
//		test.addTest(new CompanyCommentCategoryPointMongoTest("findCompanyCommentCategoryPointList"));
////		test.addTest(new CompanyCommentCategoryPointMongoTest("insertCompanyCommentCategoryPoint"));
////		test.addTest(new CompanyCommentCategoryPointMongoTest("findCompanyCommentCategoryPointById"));
////		test.addTest(new CompanyCommentCategoryPointMongoTest("insertCompanyCommentCategoryPoint"));
//		return test;
//	}
//	
//	public void findCompanyCommentCategoryPointById() {
//		try {
//			System.out.println(companyCommentCategoryPointMongoImpl.findCompanyCommentCategoryPointById(""));
//		} catch (YuleException e) {
//			e.printStackTrace();
//		}
//	}
//	
//	public void findCompanyCommentCategoryPointList() {
//		try {
//			CompanyCommentCategoryPointQuery companyCommentCategoryPointMongoQuery = new CompanyCommentCategoryPointQuery();
//			companyCommentCategoryPointMongoQuery.setCompany_id("1");
//			List<CompanyCommentCategoryPointVO> lists = companyCommentCategoryPointMongoImpl.findCompanyCommentCategoryPointVOList(companyCommentCategoryPointMongoQuery);
//			for(CompanyCommentCategoryPointVO companyCommentCategoryPoint :lists){
//				System.out.println(companyCommentCategoryPoint.getId());
////				System.out.println(companyCommentCategoryPoint.getCompany_comment_category_name());
//				System.out.println(companyCommentCategoryPoint.getPoint());
//			}
//		} catch (YuleException e) {
//			e.printStackTrace();
//		}
//	}
//	
//	
//	public void deleteCompanyCommentCategoryPointById() {
//		try {
//			System.out.println(companyCommentCategoryPointMongoImpl.deleteCompanyCommentCategoryPointById("1"));
//		} catch (YuleException e) {
//			e.printStackTrace();
//		}
//	}
//	
//	public void insertCompanyCommentCategoryPoint() {
//		CompanyCommentCategoryPoint companyCommentCategoryPoint = new CompanyCommentCategoryPoint();
//		companyCommentCategoryPoint.setCompany_id("1");
//		companyCommentCategoryPoint.setCompany_comment_category_id("1");
//		companyCommentCategoryPoint.setPoint(2.8);
//		try {
//			System.out.println(companyCommentCategoryPointMongoImpl.insertCompanyCommentCategoryPoint(companyCommentCategoryPoint));
//		} catch (YuleException e) {
//			e.printStackTrace();
//		}
//	}
//	
//}
//
