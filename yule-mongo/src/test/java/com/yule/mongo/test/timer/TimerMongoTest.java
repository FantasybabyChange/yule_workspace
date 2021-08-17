//package com.yule.mongo.test.timer;
//
//import junit.framework.Test;
//import junit.framework.TestCase;
//import junit.framework.TestSuite;
//
//import org.springframework.context.ApplicationContext;
//
//import com.yule.constant.CustomBeanConst;
//import com.yule.mongo.timer.service.ITimerMongo;
//import com.yule.util.CustomBeanFactory;
//
//public class TimerManagerTest extends TestCase {
//
//	private ITimerMongo timerMongoImpl = null;
//	
//	public TimerManagerTest(String name) {
//		super(name);
//		ApplicationContext context = CustomBeanFactory.getContext(CustomBeanConst.TIMER_MONGO_PATHS);
//		timerMongoImpl = (ITimerMongo) context.getBean("timerMongoImpl");
//	}
//
//	public static Test suite() {
//		TestSuite test = new TestSuite("TimerManagerTest接口测试");
//		test.addTest(new TimerManagerTest("findTimerPage"));
//		return test;
//	}
//	
//	public void findTimerPage() {
//		try {
//			timerMongoImpl.findTimerPage(10, 1);
//		} catch (YuleException e) {
//			e.printStackTrace();
//		}
//	}
//
//}
