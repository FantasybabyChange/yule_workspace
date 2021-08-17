package com.yule.mongo.mobile.vo;

import java.io.Serializable;

public class CompanyHotVO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -306653127174002229L;
	private String company_id;
	private String company_name;
	private String orders_count;
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
	public String getOrders_count() {
		return orders_count;
	}
	public void setOrders_count(String orders_count) {
		this.orders_count = orders_count;
	}
	
}
