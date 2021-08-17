package com.yule.util;

import com.yule.runnable.SendMailRunnable;


public class SendMailRunnableUtil {

	public static void sendMail(MailUtil mailUtil,String toMail,String... files) throws Exception{
		Thread t = new Thread(new SendMailRunnable(mailUtil, toMail,files));
		t.start();
	}
	
}
