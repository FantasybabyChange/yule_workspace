//package com.yule.mongo.test.user;
//
//import java.util.Calendar;
//
//import junit.framework.Test;
//import junit.framework.TestCase;
//import junit.framework.TestSuite;
//
//import com.yule.constant.CustomBeanConst;
//import com.yule.enumerate.DateStyle;
//import com.yule.exception.YuleException;
//import com.yule.mongo.pojo.UserExpense;
//import com.yule.mongo.user.query.UserExpenseQuery;
//import com.yule.mongo.user.service.IUserExpenseMongo;
//import com.yule.mongo.user.vo.UserExpenseVO;
//import com.yule.util.CustomBeanFactory;
//import com.yule.util.DateUtil;
//
//public class UserExpenseMongoTest extends TestCase {
//
//	private IUserExpenseMongo userExpenseMongoImpl = null;
//	
//	public UserExpenseMongoTest(String name) {
//		super(name);
//		userExpenseMongoImpl = (IUserExpenseMongo)  CustomBeanFactory.getContext(CustomBeanConst.USER_MONGO_PATHS).getBean("userExpenseMongoImpl");
//	}
//
//	public static Test suite() {
//		TestSuite test = new TestSuite("TimerManagerTest接口测试");
//		test.addTest(new UserExpenseMongoTest("findUserExpenseVO"));
////		test.addTest(new UserExpenseMongoTest("insertUserExpense"));
//		return test;
//	}
//	
//	public void findUserExpenseVO() {
//		UserExpenseQuery userExpenseQuery = new UserExpenseQuery();
//		Calendar calendar = Calendar.getInstance();
//		calendar.setTimeInMillis(System.currentTimeMillis());
//		userExpenseQuery.setEndTime(DateUtil.DateToString(calendar.getTime(), DateStyle.YYYY_MM_DD_HH_MM_SS_EN));
//		calendar.add(Calendar.YEAR, -1);
//		userExpenseQuery.setStartTime(DateUtil.DateToString(calendar.getTime(), DateStyle.YYYY_MM_DD_HH_MM_SS_EN));
//		userExpenseQuery.setUserId("18846800-208a-41c4-8d7c-9dce2400b934");
//		try {
//			UserExpenseVO userExpenseVO = userExpenseMongoImpl.findUserExpenseVO(userExpenseQuery);
//			if(null!=userExpenseVO){
//				System.out.println(userExpenseVO.getRatio());
//				System.out.println(userExpenseVO.getMoney());
//			}
//		} catch (YuleException e) {
//			e.printStackTrace();
//		}
//	}
//	
//	public void insertUserExpense() throws YuleException{
//		for(int i=0;i<10;i++){
//			UserExpense userExpense = new UserExpense();
//			userExpense.setUser_id("test"+i);
//			userExpense.setMoney(1000+i);
//			userExpenseMongoImpl.insertUserExpense(userExpense);
//		}
//	}
//
//}
