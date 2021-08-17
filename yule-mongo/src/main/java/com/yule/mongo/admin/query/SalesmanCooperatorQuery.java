package com.yule.mongo.admin.query;

import java.io.Serializable;

public class SalesmanCooperatorQuery implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1316622548624700827L;
	// 商家名称
	private String account;
	// 手机号
	private String phone;
	// 邮箱
	private String mail;
	// 合作状态
	private Integer status;
	private String start_time;
	private String end_time;
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
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
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
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
	
}
