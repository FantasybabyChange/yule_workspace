package com.yule.mail.user;

import java.io.Serializable;

import com.yule.util.MailUtil;

public class UserRegisterActivationMail extends MailUtil implements Serializable {

	public UserRegisterActivationMail() throws Exception {
		super();
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 4575023878583650768L;
	
	private String mail;
	
	private String captcha;
	
	public String getCaptcha() {
		return captcha;
	}

	public void setCaptcha(String captcha) {
		this.captcha = captcha;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}
	
}
