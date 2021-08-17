package com.yule.mail.user;

import java.io.Serializable;

import com.yule.util.MailUtil;

public class UserAuthSuccessMail extends MailUtil implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4635547063474441084L;

	public UserAuthSuccessMail() throws Exception {
		super();
	}
	
	private String name;
	
	private String mail;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}
	
}
