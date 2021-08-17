//package com.yule.mongo.test.admin;
//
//import java.util.List;
//
//import junit.framework.Test;
//import junit.framework.TestCase;
//import junit.framework.TestSuite;
//
//
//import org.springframework.context.ApplicationContext;
//
//import com.yule.constant.CustomBeanConst;
//import com.yule.constant.PageConst;
//import com.yule.mongo.admin.query.CompanyCooperatorQuery;
//import com.yule.mongo.admin.service.ICompanyCooperatorMongo;
//import com.yule.mongo.pojo.CompanyCooperator;
//import com.yule.util.CustomBeanFactory;
//import com.yule.vo.Page;
//
//
//public class CompanyCooperatorMongoTest extends TestCase {
//
//	private ICompanyCooperatorMongo companyCooperatorMongo = null;
//	
//	public CompanyCooperatorMongoTest(String name) {
//		super(name);
//		ApplicationContext context = CustomBeanFactory.getContext(CustomBeanConst.ADMIN_MONGO_PATHS);
//		companyCooperatorMongo = (ICompanyCooperatorMongo) context.getBean("companyCooperatorMongoImpl");
//	}
//
//	public static Test suite() {
//		TestSuite test = new TestSuite("AdminMessageManagerTest接口测试");
//		test.addTest(new CompanyCooperatorMongoTest("findCompanyCooperator"));
////		test.addTest(new AdminMessageMongoTest("insertAdminMessage"));
////		test.addTest(new AdminMessageMongoTest("findAdminMessageList"));
////		test.addTest(new AdminMessageMongoTest("deleteAdminMessageById"));
////		test.addTest(new AdminMessageMongoTest("insertAdminMessage"));
////		test.addTest(new AdminMessageMongoTest("findAdminMessagePage"));
//		return test;
//	}
//	
//	public void findCompanyCooperator() throws YuleException{
//		CompanyCooperatorQuery query = new CompanyCooperatorQuery();
//		query.setStatus("1");
//		Page<CompanyCooperator> page = this.companyCooperatorMongo.findCompanyCooperatorPage(query, PageConst.PAGE_SIZE_TEN, 1);
//		List<CompanyCooperator> datas = page.getDatas();
//		for (CompanyCooperator c : datas) {
//			System.out.println(c.getName());
//			System.out.println(c.getAddress());
//			System.out.println(c.getMail());
//		}
//		
//		
//	}
//	
//}
//
