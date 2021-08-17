//package com.yule.mongo.test.admin;
//
//import junit.framework.Test;
//import junit.framework.TestCase;
//import junit.framework.TestSuite;
//
//import org.springframework.context.ApplicationContext;
//
//import com.yule.constant.CustomBeanConst;
//import com.yule.enumerate.DateStyle;
//import com.yule.exception.YuleException;
//import com.yule.mongo.admin.query.AdminLogQuery;
//import com.yule.mongo.admin.service.IAdminLogMongo;
//import com.yule.mongo.pojo.AdminLog;
//import com.yule.util.CustomBeanFactory;
//import com.yule.util.DateUtil;
//import com.yule.vo.Page;
//
//public class AdminLogMongoTest extends TestCase {
//
//	private IAdminLogMongo adminLogMongoImpl = null;
//	
//	public AdminLogMongoTest(String name) {
//		super(name);
//		ApplicationContext context = CustomBeanFactory.getContext(CustomBeanConst.ADMIN_MONGO_PATHS);
//		adminLogMongoImpl = (IAdminLogMongo) context.getBean("adminLogMongoImpl");
//	}
//
//	public static Test suite() {
//		TestSuite test = new TestSuite("AdminLogMongoTest接口测试");
//		test.addTest(new AdminLogMongoTest("findAdminLogPage"));
////		test.addTest(new AdminLogMongoTest("updateAdminLog"));
////		test.addTest(new AdminLogMongoTest("findAdminLogList"));
////		test.addTest(new AdminLogMongoTest("deleteAdminLogById"));
////		test.addTest(new AdminLogMongoTest("insertAdminLog"));
//		return test;
//	}
//	
//	public void findAdminLogPage() {
//		try {
//			long start = System.currentTimeMillis();
//			AdminLogQuery adminLogQuery = new AdminLogQuery();
//			adminLogQuery.setStart_time("2014-08-31 11:00:00");
//			adminLogQuery.setEnd_time("2014-08-31 12:00:00");
////			adminLogQuery.setTitle("二哥");
////			adminLogQuery.setAdmin_user_id(2);
////			List<String> types = new ArrayList<String>();
////			types.add("3");
////			adminLogQuery.setType(types);
//			Page<AdminLog> pages = adminLogMongoImpl.findAdminLogPage(adminLogQuery, Integer.MAX_VALUE,1);
//			System.out.println(pages.getDatas().size());
//			for(AdminLog adminLog :pages.getDatas()){
//				System.out.println(DateUtil.DateToString(adminLog.getCreate_time(), DateStyle.YYYY_MM_DD_HH_MM_SS_EN));
//			}
//			System.out.println(System.currentTimeMillis()-start);
//		} catch (YuleException e) {
//			e.printStackTrace();
//		}
//	}
//	
//	public void insertAdminLog() {
//		try {
//			AdminLog adminLog = new AdminLog();
//			adminLog.setName("insert");
//			adminLog.setCreate_time(DateUtil.StringToDate("2014-08-10 11:11:11",DateStyle.YYYY_MM_DD_HH_MM_SS_EN));
//			System.out.println(adminLogMongoImpl.insertAdminLog(adminLog));
////			System.out.println(AdminLogManager.deleteAdminLogById(AdminLog.getId().toString()));
//		} catch (YuleException e) {
//			e.printStackTrace();
//		}
//	}
//	
//}
