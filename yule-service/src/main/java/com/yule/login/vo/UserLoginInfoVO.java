package com.yule.login.vo;

import java.io.Serializable;

/**
 * 前台用户登录
 */
public class UserLoginInfoVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1107040690871524174L;
	//用户ID
	private String id;
	// 用户名
	private String name;
	// 电话号码
	private String phone;
	// 电子邮件
	private String mail;
	// 邮箱认证
	private Integer mail_auth;
	// 手机认证
	private Integer phone_auth;
	
	private Integer status;
	
	public UserLoginInfoVO() {
		super();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public Integer getMail_auth() {
		return mail_auth;
	}

	public void setMail_auth(Integer mail_auth) {
		this.mail_auth = mail_auth;
	}

	public Integer getPhone_auth() {
		return phone_auth;
	}

	public void setPhone_auth(Integer phone_auth) {
		this.phone_auth = phone_auth;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

}
