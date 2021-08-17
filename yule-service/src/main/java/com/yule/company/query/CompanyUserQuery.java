package com.yule.company.query;

import java.io.Serializable;

public class CompanyUserQuery implements Serializable {
	private static final long serialVersionUID = 860116214479944413L;
	
	private String company_id;
	
	private String account;
	// 状态0 启用1未启用
	private Integer status;

	private String start_time;

	private String end_time;

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
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

	public String getCompany_id() {
		return company_id;
	}

	public void setCompany_id(String company_id) {
		this.company_id = company_id;
	}
	
	
}
