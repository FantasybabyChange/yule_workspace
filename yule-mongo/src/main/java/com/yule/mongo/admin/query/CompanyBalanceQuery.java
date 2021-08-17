package com.yule.mongo.admin.query;

import java.io.Serializable;

public class CompanyBalanceQuery implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7791166924659423782L;
	private String start_time;
	private String end_time;
	private String company_id;
	private String company_name;
	private Integer pay_status;
	
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

	public String getCompany_name() {
		return company_name;
	}

	public void setCompany_name(String company_name) {
		this.company_name = company_name;
	}

	public Integer getPay_status() {
		return pay_status;
	}

	public void setPay_status(Integer pay_status) {
		this.pay_status = pay_status;
	}

	public CompanyBalanceQuery() {
		super();
	}
	
}
