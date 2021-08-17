//package com.yule.mongo.test.admin;
//
//import junit.framework.Test;
//import junit.framework.TestCase;
//import junit.framework.TestSuite;
//
//import org.springframework.context.ApplicationContext;
//
//import com.yule.constant.CustomBeanConst;
//import com.yule.mongo.admin.query.AdminMessageQuery;
//import com.yule.mongo.admin.service.IAdminMessageMongo;
//import com.yule.mongo.pojo.AdminMessage;
//import com.yule.util.CustomBeanFactory;
//import com.yule.vo.Page;
//
//public class AdminMessageMongoTest extends TestCase {
//
//	private IAdminMessageMongo adminMessageMongoImpl = null;
//	
//	public AdminMessageMongoTest(String name) {
//		super(name);
//		ApplicationContext context = CustomBeanFactory.getContext(CustomBeanConst.ADMIN_MONGO_PATHS);
//		adminMessageMongoImpl = (IAdminMessageMongo) context.getBean("adminMessageMongoImpl");
//	}
//
//	public static Test suite() {
//		TestSuite test = new TestSuite("AdminMessageManagerTest接口测试");
//		test.addTest(new AdminMessageMongoTest("insertAdminMessage"));
////		test.addTest(new AdminMessageMongoTest("insertAdminMessage"));
////		test.addTest(new AdminMessageMongoTest("findAdminMessageList"));
////		test.addTest(new AdminMessageMongoTest("deleteAdminMessageById"));
////		test.addTest(new AdminMessageMongoTest("insertAdminMessage"));
////		test.addTest(new AdminMessageMongoTest("findAdminMessagePage"));
//		return test;
//	}
//	
//	public void findAdminMessageById() {
//		try {
//			System.out.println(adminMessageMongoImpl.findAdminMessageById("53b4c807fc8730c68a191d2b"));
//		} catch (YuleException e) {
//			e.printStackTrace();
//		}
//	}
//	
//	public void findAdminMessagePage() {
//		try {
//			AdminMessageQuery adminMessageMongoQuery = new AdminMessageQuery();
////			adminMessageMongoQuery.setStart_time("2014-06-26");
////			adminMessageMongoQuery.setEnd_time("2014-06-31");
////			adminMessage.setTitle("二哥");
////			adminMessage.setAdmin_user_id(2);
//			Page<AdminMessage> pages = adminMessageMongoImpl.findAdminMessagePage(adminMessageMongoQuery, 10,1);
//			System.out.println(pages.getDatas().size());
//			for(AdminMessage adminMessage :pages.getDatas()){
//				System.out.println(adminMessage.getTitle());
//			}
//		} catch (YuleException e) {
//			e.printStackTrace();
//		}
//	}
//	
//	public void updateAdminMessageByOrderNum() {
//		AdminMessage AdminMessage = new AdminMessage();
//		AdminMessage.setTitle("123456789");
//		try {
//			System.out.println(adminMessageMongoImpl.updateAdminMessage(AdminMessage));
//		} catch (YuleException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
//	
//	public void insertAdminMessage() {
//		AdminMessage adminMessage = new AdminMessage();
//		adminMessage.setTitle("小呀小张磊");
//		adminMessage.setContent("肖三磊");
//		adminMessage.setReceive_id("925bb45f-6bc9-44b2-b63d-91abebf5bff1");
//		adminMessage.setSend_id("8a0e14de-6118-409b-8c7a-8931ab2ff836");
//		adminMessage.setIs_read(1);
//		adminMessage.setIs_delete(0);
//		
//		try {
//			System.out.println(adminMessageMongoImpl.insertAdminMessage(adminMessage));
////			System.out.println(adminMessageManager.deleteAdminMessageById(adminMessage.getId().toString()));
//		} catch (YuleException e) {
//			e.printStackTrace();
//		}
//	}
//	
//}
//
