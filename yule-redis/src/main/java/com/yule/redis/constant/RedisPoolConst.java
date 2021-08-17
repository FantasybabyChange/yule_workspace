package com.yule.redis.constant;

public class RedisPoolConst {

	/**
	 * 地址
	 */
	public final static String HOST = "redis.yuleing.com";
	
	/**
	 * 端口
	 */
	public final static int PORT = 6379;
	
	/**
	 * 密码
	 */
	public final static String PASSWORD = "yuleing";
	
	/**
	 * 默认连接超时
	 */
	public final static int TIME_OUT = 60000;
	
	/**
	 * 最大连接
	 */
	public final static int MAX_ACTIVE = 512;
	
	/**
	 * 最大空闲
	 */
	public final static int MAX_IDLE = 100;
	
	/**
	 * 最大等待
	 */
	public final static int MAX_WAIT = 100000;
	
	/**
	 * 测试连接
	 */
	public final static boolean TEST_ON_BORROW = true;
	
	/**
	 * 测试返回
	 */
	public final static boolean TEST_ON_RETURN = true;
	
	/**
	 * 默认
	 */
	public final static int DEFAULT_EXPIRE = 60 * 60 * 24;
	
}
