package com.yule.instance;

import java.util.Properties;

public class SmsProperties {
	
	private static Properties properties = null;

	private static final String PATH = "sms.properties";

	public static Properties getInstance() throws Exception{
		if(properties==null){
			properties= new Properties(); 
			properties.load(Thread.currentThread().getContextClassLoader().getResourceAsStream(PATH));
		}
		return properties;
	}
}
