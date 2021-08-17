package com.yule.util;

import java.util.HashMap;
import java.util.Map;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;



public final class CustomBeanFactory {
	
	private static Map<String,ApplicationContext> contexts = new HashMap<String, ApplicationContext>();
	
	public static ApplicationContext getContext(String key) {
		if(!contexts.containsKey(key)){
			contexts.put(key, new ClassPathXmlApplicationContext(key));
		}
		return contexts.get(key);
	}

}
