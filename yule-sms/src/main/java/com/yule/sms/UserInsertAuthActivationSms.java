package com.yule.sms;

import java.io.Serializable;

import com.yule.util.SmsUtil;

public class UserInsertAuthActivationSms extends SmsUtil implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6190867716687507976L;

	public UserInsertAuthActivationSms() throws Exception {
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
