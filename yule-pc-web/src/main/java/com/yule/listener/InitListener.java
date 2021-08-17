package com.yule.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class InitListener implements ServletContextListener  {
	

	public void contextDestroyed(ServletContextEvent sce) {
		
	}

	public void contextInitialized(ServletContextEvent sce) {
//		try {
//			AreaUtil.init();
//		} catch (Exception e) {
//			new YuleException(e);
//		}finally{
//			System.gc();
//		}
	}

}
