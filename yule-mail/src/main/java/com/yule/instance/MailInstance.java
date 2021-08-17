package com.yule.instance;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;


public class MailInstance {
	
	private static Map<String, Properties> mails = new HashMap<String, Properties>();
	
	public static Properties getInstance(String resouce) throws Exception{
	if(!mails.containsKey(resouce)){
		InputStream in = null;
		try{
			Properties mail = new Properties();  
			in = MailInstance.class.getClassLoader().getResourceAsStream(resouce);
			mail.load(in);
			mails.put(resouce, mail);
		}finally{
			if(null!=in){
				in.close();
				}
			}
		}
		return mails.get(resouce);
	}
}
