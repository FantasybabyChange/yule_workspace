package com.yule.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.yule.exception.YuleException;
import com.yule.util.FileUploadPropertiesUtil;

public class InitListener implements ServletContextListener  {
	
	public void contextDestroyed(ServletContextEvent sce) {
		
	}

	public void contextInitialized(ServletContextEvent sce) {
		try {
			FileUploadPropertiesUtil.getInstance();
		} catch (Exception e) {
			new YuleException("初始化发生错误!",e);
		}finally{
			System.gc();
		}
	}

}
