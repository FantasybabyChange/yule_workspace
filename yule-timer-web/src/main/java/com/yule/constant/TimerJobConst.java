package com.yule.constant;


public class TimerJobConst {
	
	/**
	 * 任务名称后缀
	 */
	public static final String QUARTZ_PATH = "/conf/applicationContext-quartz.xml";
	
	/**
	 * 任务名称后缀
	 */
	public static final String TRIGGER = "Trigger";
	
	/**
	 * 任务执行方式
	 */
	public static final String[] ASYNCS = {"同步","异步"};
	
	/**
	 * 同步
	 */
	public static final int ASYNC_ZERO = 0;
	
	/**
	 * 异步
	 */
	public static final int ASYNC_ONE = 1;
	
	/**
	 * 状态显示
	 */
	public static final String[] STATUS = {"启用","关闭"};
	
	/**
	 * 启用
	 */
	public static final int STATUS_TRUE = 0;
	/**
	 * 关闭
	 */
	public static final int STATUS_FALSE = 1;
	
	/**
	 * quart 异步
	 */
	public static final String METHOD_INVOKING_JOB = "MethodInvokingJob";
	
	/**
	 * quart 同步
	 */
	public static final String SATEFUL_METHOD_INVOKING_JOB = "StatefulMethodInvokingJob";
	
	
}
