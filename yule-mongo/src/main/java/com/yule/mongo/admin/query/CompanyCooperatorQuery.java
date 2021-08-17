package com.yule.mongo.admin.query;

import java.io.Serializable;

public class CompanyCooperatorQuery implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -4896347174561136733L;
	// 商家名称
	private String name;
	// 手机号
	private String phone;
	// 邮箱
	private String mail;
	// 合作状态
	private Integer status;
	private String start_time;
	private String end_time;
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
