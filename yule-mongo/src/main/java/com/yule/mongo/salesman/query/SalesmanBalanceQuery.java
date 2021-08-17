package com.yule.mongo.salesman.query;

import java.io.Serializable;
import java.util.List;

public class SalesmanBalanceQuery implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3861079170609050541L;

	private String salesman_id;
	private String start_time;
	private String end_time;
	private List<String> company_ids;
	
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

	public List<String> getCompany_ids() {
		return company_ids;
	}

	public void setCompany_ids(List<String> company_ids) {
		this.company_ids = company_ids;
	}

}

