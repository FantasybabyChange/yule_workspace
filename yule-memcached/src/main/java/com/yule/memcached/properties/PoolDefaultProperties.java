package com.yule.memcached.properties;

import java.util.Properties;

public class PoolDefaultProperties extends Properties{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1155455265119299750L;

	public PoolDefaultProperties() {  
        super();  
        initDefault();  
    }  
      
    private void initDefault() {  
        initConn();  
        initMainSleep();  
        initTCP();  
//        initFailover();  
//        initAliveCheck();  
    }  
    
    protected void initConn() {  
        setProperty("initConn", "10");  //设置开始时每个cache服务器的可用连接数
        setProperty("minConn", "10");  //设置每个服务器最少可用连接数
        setProperty("maxConn", "20");  //设置每个服务器最大可用连接数
        //设置连接池维护线程的睡眠时间,设置为0，维护线程不启动.
        setProperty("maxIdle", String.valueOf(1000 * 60 * 30));  
    }  
    
    protected void initMainSleep() {  
        setProperty("maintSleep", String.valueOf(1000 * 5));  //主线程的睡眠时间
    }  
      
    protected void initTCP() {  
        setProperty("nagle", "false");  
        setProperty("socketTO", String.valueOf(1000 * 3));  //设置socket的读取等待超时值
        setProperty("socketConnectTO", String.valueOf(1000 * 3));   //设置socket的连接等待超时值
    }  
      
//    protected void initFailover() {  
//        setProperty("failover", "true");//设置容错开关  
//        setProperty("failback", "true"); //设置连接失败恢复开关
//    }  
      
//    protected void initAliveCheck() {  
//        setProperty("aliveCheck", "true");  
//    }  
	
}
