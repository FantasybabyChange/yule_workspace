package com.yule.util;

import com.yule.runnable.SendSmsRunnable;

public class SendSmsRunnableUtil {
	
	public static void sendSms(SmsUtil smsUtil,String toPhone,String stime) throws Exception{
		Thread t = new Thread(new SendSmsRunnable(smsUtil, toPhone,stime));
		t.start();
	}
	
	public static void sendSms(SmsUtil smsUtil,String toPhone) throws Exception{
		Thread t = new Thread(new SendSmsRunnable(smsUtil, toPhone , ""));
		t.start();
	}
	
}
