package com.yule.pojo;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * 系统管理用户
 */
public class AdminUser implements Serializable {
	
	private static final long serialVersionUID = 3861220163233740141L;

	private String id;
	// 用户角色ID
	private String admin_role_id;
	// 用户名
	private String account;
	// 密码
	private String password;
	// 登陆时间
	private Timestamp login_time;
	// 状态ID
	private Integer status;
	//
	private Integer is_delete;
	// 创建时间
	private Timestamp create_time;
	// 修改时间
	private Timestamp update_time;
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
	public Timestamp getLogin_time() {
		return login_time;
	}
	public void setLogin_time(Timestamp login_time) {
		this.login_time = login_time;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
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
	public AdminUser() {
		super();
	}
}
