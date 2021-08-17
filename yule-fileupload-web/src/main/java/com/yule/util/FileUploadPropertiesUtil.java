package com.yule.util;

import java.io.IOException;
import java.util.Properties;

public class FileUploadPropertiesUtil {
	
	//private static ReadWriteLock readWriteLock = new ReadWriteLock();
	
	private static Properties properties = null;

	private static final String PATH = "fileupload.properties";

	public static Properties getInstance() {
		//logger.info("执行getInstance方法");
		if(properties==null){
			properties = getProperties();
		}
		return properties;
	}

	private static Properties getProperties() {
		//readWriteLock.readLock();
		try {
			properties= new Properties(); 
			properties.load(Thread.currentThread().getContextClassLoader().getResourceAsStream(PATH));
		} catch (IOException e) {
			e.printStackTrace();
		} 
		return properties;
	}
}
