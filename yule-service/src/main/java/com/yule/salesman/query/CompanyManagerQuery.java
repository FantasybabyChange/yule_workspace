package com.yule.salesman.query;

import java.io.Serializable;

public class CompanyManagerQuery implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7639202368324830379L;
	private String id;
	//企业名称
	private String name;
	// 帐号
	private String account;
	
	private String salesman_id;
	// 密码
	private String password;
	// 企业CODE
	private String code;
	// 登录时间
	private String login_time;
	// 状态0 启用1未启用
	private Integer status;
	// 是否删除0删除1未删除
	private Integer is_delete;

	private String start_time;

	private String end_time;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
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
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getLogin_time() {
		return login_time;
	}
	public void setLogin_time(String login_time) {
		this.login_time = login_time;
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
	public Integer getIs_delete() {
		return is_delete;
	}
	public void setIs_delete(Integer is_delete) {
		this.is_delete = is_delete;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public CompanyManagerQuery() {
		super();
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSalesman_id() {
		return salesman_id;
	}
	public void setSalesman_id(String salesman_id) {
		this.salesman_id = salesman_id;
	}
}
