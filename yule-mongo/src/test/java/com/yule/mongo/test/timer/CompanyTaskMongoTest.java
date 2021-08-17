package com.yule.mongo.test.timer;
//package com.yule.mongo.test.timer;
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
//import com.yule.mongo.pojo.CompanyTask;
//import com.yule.mongo.timer.service.ICompanyTaskMongo;
//import com.yule.util.CustomBeanFactory;
//
//public class CompanyTaskManagerTest extends TestCase {
//
//	private ICompanyTaskMongo companyTaskMongoImpl = null;
//	
//	public CompanyTaskManagerTest(String name) {
//		super(name);
//		ApplicationContext context = CustomBeanFactory.getContext(CustomBeanConst.TIMER_MONGO_PATHS);
//		companyTaskMongoImpl = (ICompanyTaskMongo) context.getBean("companyTaskMongoImpl");
//	}
//
//	public static Test suite() {
//		TestSuite test = new TestSuite("CompanyTaskManagerTest接口测试");
//		test.addTest(new CompanyTaskManagerTest("batchInsertCompanyTask"));
//		return test;
//	}
//	
//	public void batchInsertCompanyTask() {
//		List<CompanyTask> lists = new ArrayList<CompanyTask>();
//		CompanyTask companyTask = new CompanyTask();
//		companyTask.setName("test");
//		companyTask.setUrl("test");
//		companyTask.setCompany_id("1");
//		lists.add(companyTask);
//		try {
//			companyTaskMongoImpl.batchInsertCompanyTask(lists);
//		} catch (YuleException e) {
//			e.printStackTrace();
//		}
//	}
//
//}
