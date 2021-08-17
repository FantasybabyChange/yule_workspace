//package com.yule.mongo.test.admin;
//
//
//import java.util.ArrayList;
//import java.util.List;
//
//import junit.framework.Test;
//import junit.framework.TestCase;
//import junit.framework.TestSuite;
//
//import org.springframework.context.ApplicationContext;
//
//import com.yule.constant.CustomBeanConst;
//import com.yule.mongo.admin.query.CompanyLogQuery;
//import com.yule.mongo.admin.service.ICompanyLogMongo;
//import com.yule.mongo.pojo.CompanyLog;
//import com.yule.util.CustomBeanFactory;
//import com.yule.vo.Page;
//
//public class CompanyLogMongoTest extends TestCase {
//
//	private ICompanyLogMongo companyLogMongoImpl = null;
//	
//	public CompanyLogMongoTest(String name) {
//		super(name);
//		ApplicationContext context = CustomBeanFactory.getContext(CustomBeanConst.ADMIN_MONGO_PATHS);
//		companyLogMongoImpl = (ICompanyLogMongo) context.getBean("companyLogMongoImpl");
//	}
//
//	public static Test suite() {
//		TestSuite test = new TestSuite("CompanyLogMongoTest接口测试");
//		test.addTest(new CompanyLogMongoTest("findCompanyLogPage"));
////		test.addTest(new CompanyLogMongoTest("updateCompanyLog"));
////		test.addTest(new CompanyLogMongoTest("findCompanyLogList"));
////		test.addTest(new CompanyLogMongoTest("deleteCompanyLogById"));
////		test.addTest(new CompanyLogMongoTest("insertCompanyLog"));
////		test.addTest(new CompanyLogMongoTest("findCompanyLogPage"));
//		return test;
//	}
//	
//	public void findCompanyLogPage() {
//		try {
//			long start = System.currentTimeMillis();
//			CompanyLogQuery companyLogMongoQuery = new CompanyLogQuery();
////			CompanyLogMongoQuery.setStart_time("2014-06-26");
////			CompanyLogMongoQuery.setEnd_time("2014-06-31");
////			CompanyLog.setTitle("二哥");
////			companyLogMongoQuery.setCompany_id("2b959fb8-94e9-453f-bef1-a8f905a419da");
//			List<String> types = new ArrayList<String>();
//			types.add("6");
//			companyLogMongoQuery.setType(types);
//			Page<CompanyLog> pages = companyLogMongoImpl.findCompanyLogPage(companyLogMongoQuery, 10,1);
//			System.out.println(pages.getDatas().size());
//			for(CompanyLog CompanyLog :pages.getDatas()){
//				System.out.println(CompanyLog.getName());
//			}
//			System.out.println(System.currentTimeMillis()-start);
//		} catch (YuleException e) {
//			e.printStackTrace();
//		}
//	}
//
//	
//}
//
