package com.yule.mail.user;

import java.io.Serializable;

import com.yule.util.MailUtil;

public class UserRegisterSuccessMail extends MailUtil implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2048447426279582307L;

	public UserRegisterSuccessMail() throws Exception {
		super();
	}
	
	private String name;
	
	private String mail;
	
	private Integer score;
	
	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getScore() {
		return score;
	}

	public void setScore(Integer score) {
		this.score = score;
	}
	
}
