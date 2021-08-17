//package com.yule.mongo.test.company;
//
//import junit.framework.Test;
//import junit.framework.TestCase;
//import junit.framework.TestSuite;
//
//import org.springframework.context.ApplicationContext;
//
//import com.yule.constant.CustomBeanConst;
//import com.yule.constant.StatusConst;
//import com.yule.mongo.company.service.ICompanyCooperatorMongo;
//import com.yule.mongo.pojo.CompanyCooperator;
//import com.yule.util.CustomBeanFactory;
//
//public class CompanyCooperatorMongoTest extends TestCase {
//
//	private ICompanyCooperatorMongo companyCooperatorMongo = null;
//	
//	public CompanyCooperatorMongoTest(String name) {
//		super(name);
//		ApplicationContext context = CustomBeanFactory.getContext(CustomBeanConst.COMPANY_MONGO_PATHS);
//		companyCooperatorMongo = (ICompanyCooperatorMongo) context.getBean("companyCooperatorMongoImpl");
//	}
//
//	public static Test suite() {
//		TestSuite test = new TestSuite("AdminMessageManagerTest接口测试");
//		test.addTest(new CompanyCooperatorMongoTest("insertCompanyCooperatory"));
////		test.addTest(new AdminMessageMongoTest("insertAdminMessage"));
////		test.addTest(new AdminMessageMongoTest("findAdminMessageList"));
////		test.addTest(new AdminMessageMongoTest("deleteAdminMessageById"));
////		test.addTest(new AdminMessageMongoTest("insertAdminMessage"));
////		test.addTest(new AdminMessageMongoTest("findAdminMessagePage"));
//		return test;
//	}
//	
//	public void insertCompanyCooperatory()throws YuleException{
//		for (int i = 10; i < 20; i++) {
//			CompanyCooperator cp = new CompanyCooperator();
//			cp.setAddress("张磊之家"+i);
//			cp.setName("张磊有限公司"+i);
//			cp.setMail("www.jj.com"+i);
//			cp.setPhone("123"+i);
//			cp.setDetails("呵呵"+i);
//			cp.setStatus(StatusConst.IS_COOPERATOR_FALSE);
//			this.companyCooperatorMongo.insertCompanyCooperator(cp);
//		}
//	}
//	
//}
//
