package com.yule.sms;

import java.io.Serializable;

import com.yule.util.SmsUtil;

public class UserRegisterCaptchaSms extends SmsUtil implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2048447426279582307L;

	public UserRegisterCaptchaSms() throws Exception {
		super();
	}
	
	private String captcha;

	public String getCaptcha() {
		return captcha;
	}

	public void setCaptcha(String captcha) {
		this.captcha = captcha;
	}

}
