//package com.yule.test;
//
//import junit.framework.Test;
//import junit.framework.TestCase;
//import junit.framework.TestSuite;
//
//import com.yule.sms.UserRegisterCaptchaSms;
//
//public class SmsTest extends TestCase {
//
//	public SmsTest(String name) {
//		super(name);
//	}
//
//	public static Test suite() {
//		TestSuite test = new TestSuite("SMSTest接口测试");
//		test.addTest(new SmsTest("sendSms"));
//		return test;
//	}
//
//	public void sendSms() throws Exception {
//		UserRegisterCaptchaSms sms = new UserRegisterCaptchaSms();
//		sms.setCaptcha("586941");
//		try {
//			System.out.println(sms.send("18691821238",""));
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
//	
// }
