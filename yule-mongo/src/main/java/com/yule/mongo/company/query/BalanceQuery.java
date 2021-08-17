package com.yule.mongo.company.query;

import java.io.Serializable;

public class BalanceQuery implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3193977797295790434L;
	private String month;
	private String start_time;
	private String end_time;
	private String company_id;

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

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public BalanceQuery() {
		super();
	}
	
}
