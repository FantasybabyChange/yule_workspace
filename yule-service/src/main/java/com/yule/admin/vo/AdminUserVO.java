package com.yule.admin.vo;

import java.io.Serializable;

/**
 * 用户VO
 */
public class AdminUserVO implements Serializable {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3450090643363244936L;
	
	private String id;
	// 用户角色ID
	private String admin_role_id;
	// 是否为内置用户
	private Integer is_admin;
	// 用户名
	private String account;
	// 密码
	private String password;
	// 登陆时间
	private String login_time;
	// 状态ID
	private Integer status;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getAdmin_role_id() {
		return admin_role_id;
	}
	public void setAdmin_role_id(String admin_role_id) {
		this.admin_role_id = admin_role_id;
	}
	public Integer getIs_admin() {
		return is_admin;
	}
	public void setIs_admin(Integer is_admin) {
		this.is_admin = is_admin;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getLogin_time() {
		return login_time;
	}
	public void setLogin_time(String login_time) {
		this.login_time = login_time;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public AdminUserVO() {
		super();
	}
}
