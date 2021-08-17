//package com.yule.mongo.test.user;
//
//import junit.framework.Test;
//import junit.framework.TestCase;
//import junit.framework.TestSuite;
//
//import com.yule.constant.CustomBeanConst;
//import com.yule.exception.YuleException;
//import com.yule.mongo.user.service.IUserBrowseMongo;
//import com.yule.util.CustomBeanFactory;
//
//public class UserBrowseMongoTest extends TestCase {
//
//	private IUserBrowseMongo userBrowseMongoImpl = null;
//	
//	public UserBrowseMongoTest(String name) {
//		super(name);
//		userBrowseMongoImpl = (IUserBrowseMongo)  CustomBeanFactory.getContext(CustomBeanConst.USER_MONGO_PATHS).getBean("userBrowseMongoImpl");
//	}
//
//	public static Test suite() {
//		TestSuite test = new TestSuite("TimerManagerTest接口测试");
//		test.addTest(new UserBrowseMongoTest("findUserBrowseVO"));
//		return test;
//	}
//	
//	public void findUserBrowseVO() throws YuleException {
//		userBrowseMongoImpl.findUserBrowsePageByUserId("6d0f5b0e-f34f-45df-9513-56cd541984ae", Integer.MAX_VALUE, 1);
//	}
//	
//
//}
