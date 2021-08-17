package com.yule.sms;

import java.io.Serializable;

import com.yule.util.SmsUtil;

public class UserForgetPasswordSms extends SmsUtil implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6076362412639064224L;

	public UserForgetPasswordSms() throws Exception {
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
