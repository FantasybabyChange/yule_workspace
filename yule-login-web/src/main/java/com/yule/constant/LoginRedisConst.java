package com.yule.constant;

public class LoginRedisConst {
	
	/**
	 * 用户注册信息
	 */
	public final static String USER_REGISTER = "user_register_";
	
	/**
	 * 注册验证码下次发起验证时间
	 */
	public final static String REGISTER_AUTH_TIME = "register_auth_time_";
	
	/**
	 * 用户注册邮箱 验证时用到
	 */
	public final static String USER_REGISTER_MAIL = "user_register_mail_";
	
	/**
	 * 忘记密码验证码下次发起验证时间
	 */
	public final static String FORGET_PASSWORD_AUTH_TIME = "forget_password_auth_time_";
	
	/**
	 * 用户手机修改密码信息
	 */
	public static final String USER_UPDATE_PASSWORD = "user_update_password";
	
	/**
	 * 用户找回密码session名称
	 */
	public static final String USER_FORGET_PASSWORD = "user_forget_password";
	
	/**
	 * 用户找回密码ID
	 */
	public final static String USER_UPDATE_PASSWORD_ID = "user_update_password_id";
	
	/**
	 * 用户找回密码CAPTCHA
	 */
	public final static String USER_UPDATE_PASSWORD_CAPTCHA = "user_update_password_captcha";
	
}
