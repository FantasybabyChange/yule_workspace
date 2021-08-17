package com.yule.pojo;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * 前台用户登录
 */
public class UserLogin implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -9038692679142885852L;
	//用户ID
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
	// 登录时间
	private Timestamp login_time;
	// 是否删除  0 删除 1 未删除
	private Integer is_delete;
	
	private Integer status;
	// 创建时间
	private Timestamp create_time;
	// 修改时间
	private Timestamp update_time;
	//最后一次登陆时间
	
	public UserLogin() {
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

	public Timestamp getCreate_time() {
		return create_time;
	}

	public void setCreate_time(Timestamp create_time) {
		this.create_time = create_time;
	}

	public Timestamp getUpdate_time() {
		return update_time;
	}

	public void setUpdate_time(Timestamp update_time) {
		this.update_time = update_time;
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

	public Timestamp getLogin_time() {
		return login_time;
	}

	public void setLogin_time(Timestamp login_time) {
		this.login_time = login_time;
	}
	
}
