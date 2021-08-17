//package com.yule.mongo.test.admin;
//
//import junit.framework.Test;
//import junit.framework.TestCase;
//import junit.framework.TestSuite;
//
//import org.springframework.context.ApplicationContext;
//
//import com.yule.constant.CustomBeanConst;
//import com.yule.mongo.admin.service.ICompanyCommentMongo;
//import com.yule.mongo.pojo.CompanyComment;
//import com.yule.mongo.query.CompanyCommentQuery;
//import com.yule.util.CustomBeanFactory;
//import com.yule.vo.Page;
//
//public class CompanyCommentMongoTest extends TestCase {
//
//	private ICompanyCommentMongo companyCommentMongoImpl = null;
//	
//	public CompanyCommentMongoTest(String name) {
//		super(name);
//		ApplicationContext context = CustomBeanFactory.getContext(CustomBeanConst.ADMIN_MONGO_PATHS);
//		companyCommentMongoImpl = (ICompanyCommentMongo) context.getBean("companyCommentMongoImpl");
//	}
//
//	public static Test suite() {
//		TestSuite test = new TestSuite("CompanyCommentManagerTest接口测试");
//		test.addTest(new CompanyCommentMongoTest("findCompanyCommentPage"));
////		test.addTest(new CompanyCommentMongoTest("insertCompanyComment"));
////		test.addTest(new CompanyCommentMongoTest("findCompanyCommentList"));
////		test.addTest(new CompanyCommentMongoTest("deleteCompanyCommentById"));
////		test.addTest(new CompanyCommentMongoTest("insertCompanyComment"));
////		test.addTest(new CompanyCommentMongoTest("findCompanyCommentPage"));
//		return test;
//	}
//	
//	public void findCompanyCommentPage() {
//		try {
//			CompanyCommentQuery companyCommentMongoQuery = new CompanyCommentQuery();
////			CompanyCommentMongoQuery.setStart_time("2014-06-26");
////			CompanyCommentMongoQuery.setEnd_time("2014-06-31");
////			CompanyComment.setTitle("二哥");
////			CompanyComment.setAdmin_user_id(2);
//			companyCommentMongoQuery.setUser_id("c659c7aa-a9cf-4f8c-909b-7869c57a87c5");
//			Page<CompanyComment> pages = companyCommentMongoImpl.findCompanyCommentPage(companyCommentMongoQuery, 10,1);
//			System.out.println(pages.getDatas().size());
//			for(CompanyComment companyComment :pages.getDatas()){
//				System.out.println(companyComment.getTitle());
//			}
//		} catch (YuleException e) {
//			e.printStackTrace();
//		}
//	}
//	
//}
