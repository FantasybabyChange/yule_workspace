//package com.yule.mongo.test.timer;
//
//import junit.framework.Test;
//import junit.framework.TestCase;
//import junit.framework.TestSuite;
//
//import org.springframework.context.ApplicationContext;
//
//import com.yule.constant.CustomBeanConst;
//import com.yule.exception.YuleException;
//import com.yule.mongo.timer.service.IOrdersMongo;
//import com.yule.util.CustomBeanFactory;
//
//public class OrdersMongoTest extends TestCase {
//
//	private IOrdersMongo ordersMongoImpl = null;
//	
//	public OrdersMongoTest(String name) {
//		super(name);
//		ApplicationContext context = CustomBeanFactory.getContext(CustomBeanConst.TIMER_MONGO_PATHS);
//		ordersMongoImpl = (IOrdersMongo) context.getBean("ordersMongoImpl");
//	}
//
//	public static Test suite() {
//		TestSuite test = new TestSuite("OrdersManagerTest接口测试");
////		test.addTest(new OrdersMongoTest("findOrdersCount"));
//		test.addTest(new OrdersMongoTest("findOrdersByCompanyId"));
//		return test;
//	}
//	
//	
//	public void findOrdersByCompanyId() {
//		try {
//			System.out.println(ordersMongoImpl.findOrdersByCompanyId("c4a1d516-e61b-40f2-80f5-61a1a9eea4b0", "2012-11-12 00:00:00", "2014-12-12 23:59:59"));
//		} catch (YuleException e) {
//			e.printStackTrace();
//		}
//	}
//	
//	public void findOrdersCount() {
//		try {
//			System.out.println(ordersMongoImpl.findOrdersCount(""));
//			
//		} catch (YuleException e) {
//			e.printStackTrace();
//		}
//	}
//
//}
