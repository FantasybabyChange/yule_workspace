package com.yule.admin.query;

import java.io.Serializable;

public class UserLoginQuery implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4527690686048081728L;
	
	private String id;
	// 用户名
	private String name;
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
	// 是否删除  0 删除 1 未删除
	private Integer is_delete;
	
	private Integer status;
	// 创建时间
	private String start_time;
	// 修改时间
	private String end_time;
	
	public UserLoginQuery() {
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

	public Integer getIs_delete() {
		return is_delete;
	}

	public void setIs_delete(Integer is_delete) {
		this.is_delete = is_delete;
	}

	public String getStart_time() {
		return start_time;
	}

	public void setStart_time(String start_time) {
		this.start_time = start_time;
	}

	public String getEnd_time() {
		return end_time;
	}

	public void setEnd_time(String end_time) {
		this.end_time = end_time;
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

}
