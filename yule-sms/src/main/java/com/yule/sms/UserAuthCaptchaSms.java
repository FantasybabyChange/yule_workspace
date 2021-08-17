package com.yule.sms;

import java.io.Serializable;

import com.yule.util.SmsUtil;

public class UserAuthCaptchaSms extends SmsUtil implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6117777746696408354L;

	public UserAuthCaptchaSms() throws Exception {
		super();
	}
	
	private String captcha;
	
	public String getCaptcha() {
		return captcha;
	}

	public void setCaptcha(String captcha) {
		this.captcha = captcha;
	}
	
//	public static void main(String[] args) throws Exception {
//		UserAuthCaptchaSms userAuthCaptchaSms = new UserAuthCaptchaSms();
//		userAuthCaptchaSms.setCaptcha("1254875");
//		SendSmsRunnableUtil.sendSms(userAuthCaptchaSms, "18691821238");
//	}

}
