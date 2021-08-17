package com.yule.mail.user;

import java.io.Serializable;

import com.yule.util.MailUtil;

public class UserAuthActivationMail extends MailUtil implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1497168383842312921L;

	public UserAuthActivationMail() throws Exception {
		super();
	}
	
	private String name;
	
	private String captcha;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCaptcha() {
		return captcha;
	}

	public void setCaptcha(String captcha) {
		this.captcha = captcha;
	}
	
}
