//package com.yule.mongo.test.company;
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
//import com.yule.constant.StatusConst;
//import com.yule.mongo.company.query.AdminMessageQuery;
//import com.yule.mongo.company.service.IAdminMessageMongo;
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
//		ApplicationContext context = CustomBeanFactory.getContext(CustomBeanConst.COMPANY_MONGO_PATHS);
//		adminMessageMongoImpl = (IAdminMessageMongo) context.getBean("adminMessageMongoImpl");
//	}
//
//	public static Test suite() {
//		TestSuite test = new TestSuite("AdminMessageManagerTest接口测试");
//		test.addTest(new AdminMessageMongoTest("findAdminMessagePage"));
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
//			adminMessageMongoQuery.setCompany_id("925bb45f-6bc9-44b2-b63d-91abebf5bff1");
//			adminMessageMongoQuery.setIs_read(StatusConst.IS_READ_FALSE);
//			Page<AdminMessage> pages = adminMessageMongoImpl.findAdminMessagePageByType(adminMessageMongoQuery,10 , 1);
//			List<AdminMessage> datas = pages.getDatas();
//			int i =0;
//			for (AdminMessage adminMessage : datas) {
//				System.out.println(adminMessage.getContent());
//				System.out.println(adminMessage.getSend_id());
//				System.out.println(adminMessage.getCreate_time());
//				i++;
//			}
//			System.out.println(i);
//			System.out.println(pages.getRowCount()+"------------");
//			for(AdminMessage adminMessage :pages.getDatas()){
//				System.out.println(adminMessage.getTitle());
//				System.out.println(adminMessage.getCreate_time());
//				System.out.println("++++++++++++++");
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
//		adminMessage.setTitle("123456789");
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
