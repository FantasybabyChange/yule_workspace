package com.yule.mongo.admin.query;

import java.io.Serializable;

public class SalesmanBalanceQuery implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6697715383994764388L;
	private String salesman_id;
	private String start_time;
	private String end_time;
	private Integer pay_status;
	
	public SalesmanBalanceQuery() {
		super();
	}

	public String getSalesman_id() {
		return salesman_id;
	}

	public void setSalesman_id(String salesman_id) {
		this.salesman_id = salesman_id;
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

	public Integer getPay_status() {
		return pay_status;
	}

	public void setPay_status(Integer pay_status) {
		this.pay_status = pay_status;
	}

}

