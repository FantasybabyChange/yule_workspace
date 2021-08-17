package com.yule.vo;

import java.io.Serializable;

/**
 * 前台用户登录
 */
public class UserLoginVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8180846155676647014L;
	// 用户ID
	private String id;
	// 微信ID
	private String openid;
	// 用户名
	private String name;
	// 用户头像地址
	private String image_path;
	// 密码
	private String password;
	// 电话号码
	private String phone;
	// 电子邮件
	private String mail;
	// 邮箱认证
	private Integer mail_auth;
	// 手机认证
	private Integer phone_auth;
	// 登录时间
	private String login_time;
	
	private Integer status;
	
	public UserLoginVO() {
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
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
	
	public String getLogin_time() {
		return login_time;
	}

	public void setLogin_time(String login_time) {
		this.login_time = login_time;
	}

	public String getImage_path() {
		return image_path;
	}

	public void setImage_path(String image_path) {
		this.image_path = image_path;
	}

	public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}
	
}
