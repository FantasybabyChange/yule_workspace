//package com.yule.mongo.test.company;
//
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
//import com.yule.exception.YuleException;
//import com.yule.mongo.company.service.ICompanyCommentMongo;
//import com.yule.mongo.company.vo.CompanyCommentPointVO;
//import com.yule.util.CustomBeanFactory;
//
//public class CompanyCommentMongoTest extends TestCase {
//
//	private ICompanyCommentMongo companyCommentMongoImpl = null;
//	
//	public CompanyCommentMongoTest(String name) {
//		super(name);
//		ApplicationContext context = CustomBeanFactory.getContext(CustomBeanConst.COMPANY_MONGO_PATHS);
//		companyCommentMongoImpl = (ICompanyCommentMongo) context.getBean("companyCommentMongoImpl");
//	}
//
//	public static Test suite() {
//		TestSuite test = new TestSuite("AdminMessageManagerTest接口测试");
//		test.addTest(new CompanyCommentMongoTest("findTest"));
////		test.addTest(new AdminMessageMongoTest("insertAdminMessage"));
////		test.addTest(new AdminMessageMongoTest("findAdminMessageList"));
////		test.addTest(new AdminMessageMongoTest("deleteAdminMessageById"));
////		test.addTest(new AdminMessageMongoTest("insertAdminMessage"));
////		test.addTest(new AdminMessageMongoTest("findAdminMessagePage"));
//		return test;
//	}
//	
//	public void findTest()throws YuleException{
//		List<CompanyCommentPointVO> companyCommentPointVOs = companyCommentMongoImpl.findTest();
//		for(CompanyCommentPointVO companyCommentPointVO:companyCommentPointVOs){
//			List<String> lists = companyCommentPointVO.getName();
//			int i=0;
//			for(String s :lists){
//				System.out.println(s);
//				System.out.println(companyCommentPointVO.getPoint().get(i));
//				i++;
//			}
//			System.out.println(companyCommentPointVO.getOrder_num());
//		}
//	}
////	public void insertCompanyComment()throws YuleException{
////		for (int i = 0; i < 11; i++) {
////		CompanyComment companyComment = new CompanyComment();
////		companyComment.setUser_id("c659c7aa-a9cf-4f8c-909b-7869c57a87c5");
////		companyComment.setUser_name("张磊"+i);
////		companyComment.setCompany_id("925bb45f-6bc9-44b2-b63d-91abebf5bff1");
////		companyComment.setAdvice("张磊二得很");
////		companyComment.setComment("我真的很二哦");
////		companyComment.setTitle("小二磊");
////		this.companyCommentMongoImpl.insertCompanyComment(companyComment);
////		}		
////	}
////	
////	public void findCompanyComment()throws YuleException{
////		List<CompanyComment> list = this.companyCommentMongoImpl.findCompanyCommentFirstPage("925bb45f-6bc9-44b2-b63d-91abebf5bff1", PageConst.PAGE_SIZE_TEN);
////		for (CompanyComment c : list) {
////			System.out.println(c.getUser_name());
////			System.out.println(c.getComment());
////			System.out.println(c.getTitle());
////		}
////	}
//	
//	
//}
//
