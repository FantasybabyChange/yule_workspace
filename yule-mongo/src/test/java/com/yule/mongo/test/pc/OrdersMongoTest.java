//package com.yule.mongo.test.pc;
//
//import java.util.List;
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
//import com.yule.exception.YuleException;
//import com.yule.mongo.pc.service.IOrdersMongo;
//import com.yule.mongo.pc.vo.OrdersHotAreaCityVO;
//import com.yule.mongo.pojo.Orders;
//import com.yule.util.CustomBeanFactory;
//import com.yule.util.DateUtil;
//
//
//public class OrdersMongoTest extends TestCase {
//
//	private IOrdersMongo ordersMongoImpl = null;
//
//	public OrdersMongoTest(String name) {
//		super(name);
//		ApplicationContext context = CustomBeanFactory
//				.getContext(CustomBeanConst.PC_MONGO_PATHS);
//		ordersMongoImpl = (IOrdersMongo) context.getBean("ordersMongoImpl");
//	}
//
//
//	public static Test suite() {
//		TestSuite test = new TestSuite("OrdersManagerTest接口测试");
//		test.addTest(new OrdersMongoTest("findOrdersHotAreaCityVOList"));
//		//test.addTest(new OrdersManagerTest("insertOrders"));
//		return test;
//	}
//
//	public void findOrdersHotAreaCityVOList() {
//		try {
//			List<OrdersHotAreaCityVO> lists = ordersMongoImpl.findOrdersHotAreaCityVOList(1, 1);
//			System.out.println(lists.size());
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
//			orders.setUser_id("c659c7aa-a9cf-4f8c-909b-7869c57a87c5");
//			orders.setCustomer_phone("110");
//			String aa = null;
//			int date = new Random().nextInt(30)+1;
//			if (date<10) {
//				aa="0"+date;
//			}else {
//				aa=date+"";
//			}
//			orders.setCreate_time(DateUtil.StringToDate("2014-08-"+aa+" 00:00:00"));
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
