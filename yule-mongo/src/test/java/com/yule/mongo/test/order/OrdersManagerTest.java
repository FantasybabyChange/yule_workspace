//package com.yule.mongo.test.order;
//
//import java.util.Random;
//import java.util.UUID;
//
//import junit.framework.Test;
//import junit.framework.TestCase;
//import junit.framework.TestSuite;
//
//import org.springframework.context.ApplicationContext;
//
//import com.yule.constant.CustomBeanConst;
//import com.yule.constant.StatusConst;
//import com.yule.mongo.orders.service.IOrdersMongo;
//import com.yule.mongo.pojo.Orders;
//import com.yule.mongo.query.OrdersQuery;
//import com.yule.util.CustomBeanFactory;
//import com.yule.util.DateUtil;
//import com.yule.vo.Page;
//
//public class OrdersManagerTest extends TestCase {
//
//	private IOrdersMongo ordersMongoImpl = null;
//
//	public OrdersManagerTest(String name) {
//		super(name);
//		ApplicationContext context = CustomBeanFactory
//				.getContext(CustomBeanConst.ORDER_MONGO_PATHS);
//		ordersMongoImpl = (IOrdersMongo) context.getBean("ordersMongoImpl");
//	}
//
//
//	public static Test suite() {
//		TestSuite test = new TestSuite("OrdersManagerTest接口测试");
//		test.addTest(new OrdersManagerTest("insertOrders"));
//		////test.addTest(new OrdersManagerTest("updateOrders"));
//		//test.addTest(new OrdersManagerTest("insertOrders"));
//		return test;
//	}
//
//	public void findOrdersPage() {
//		try {
//			OrdersQuery ordersQuery = new OrdersQuery();
//			ordersQuery.setCompany_id("02273571-bb7d-44e0-99c6-d7ec6d252fb2");
//
//			long start = System.currentTimeMillis();
//			Page<Orders> pages = ordersMongoImpl.findOrdersPage(ordersQuery,
//					10, 1);
//			System.out.println(pages.getRowCount());
//			System.out.println(System.currentTimeMillis() - start);
//
//		} catch (YuleException e) {
//			e.printStackTrace();
//		}
//	}
//
//	public void updateOrders() {
//		Orders orders = new Orders();
//		orders.setOrder_num("123456789");
//		try {
//			System.out.println(ordersMongoImpl.updateOrders(orders));
//		} catch (YuleException e) {
//			e.printStackTrace();
//		}
//	}
//
//	public void insertOrders() {
//		for (int i = 0; i < 100; i++) {
//			Orders orders = new Orders();
//			orders.setOrder_num(UUID.randomUUID().toString());
//			orders.setDesc("desc" + i);
//			orders.setCompany_name("admin");
//			orders.setCompany_id("925bb45f-6bc9-44b2-b63d-91abebf5bff1");
//			orders.setCompany_name("admin");
//			orders.setExpense_money(new Random().nextInt(1000));
//			orders.setCustomer_name("张二磊");
//			orders.setProduct_name("豪华大包");
//			orders.setProduct_id("06b5cd1c-9bd0-4cef-99e2-94ff1065682d");
//			orders.setIs_delete(StatusConst.IS_DELETE_TRUE);
//			orders.setStatus(StatusConst.ORDERS_STATUS_THREE);
//			orders.setUser_id("c659c7aa-a9cf-4f8c-909b-7869c57a87c5");
//			orders.setCustomer_phone("110");
//			String aa = null;
//			int date = new Random().nextInt(30)+1;
//			if (date<10) {
//				aa="0"+date;
//			}else {
//				aa=date+"";
//			}
//			orders.setCreate_time(DateUtil.StringToDate("2014-09-"+aa+" 00:00:00"));
//			
//			try {
//				System.out.println(ordersMongoImpl.insertOrders(orders));
//			} catch (YuleException e) {
//				e.printStackTrace();
//			}
//		}
//	}
//
//}
