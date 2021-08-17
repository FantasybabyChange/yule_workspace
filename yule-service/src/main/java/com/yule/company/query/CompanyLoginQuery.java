package com.yule.company.query;

import java.io.Serializable;

public class CompanyLoginQuery implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6560980422835805415L;
	
	private String id;
	private String password;
	private String account;
	private String captcha;
	private Integer code;
	public String getPassword() {
		return password;
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public String getCaptcha() {
		return captcha;
	}
	public void setCaptcha(String captcha) {
		this.captcha = captcha;
	}
	public Integer getCode() {
		return code;
	}
	public void setCode(Integer code) {
		this.code = code;
	}
}
