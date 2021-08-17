package com.yule.param;

import java.io.Serializable;

public class OrdersParam implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3644235177618479629L;
	
	private String customer_name;
	// 手机号
	private String customer_phone;
	//邮件
	private String customer_mail;
	// 特殊要求
	private String desc;
	
	private String detail_to_order;
	
	private String estimate_arrive_time;
	
	private String last_arrive_time;
	
	private String user_area_city_id;
	
	private  String user_area_city_name;
	
	private Integer user_score;
	

	public String getCustomer_name() {
		return customer_name;
	}

	public void setCustomer_name(String customer_name) {
		this.customer_name = customer_name;
	}

	public String getCustomer_phone() {
		return customer_phone;
	}

	public void setCustomer_phone(String customer_phone) {
		this.customer_phone = customer_phone;
	}

	public String getCustomer_mail() {
		return customer_mail;
	}

	public void setCustomer_mail(String customer_mail) {
		this.customer_mail = customer_mail;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getDetail_to_order() {
		return detail_to_order;
	}

	public void setDetail_to_order(String detail_to_order) {
		this.detail_to_order = detail_to_order;
	}

	public String getEstimate_arrive_time() {
		return estimate_arrive_time;
	}

	public void setEstimate_arrive_time(String estimate_arrive_time) {
		this.estimate_arrive_time = estimate_arrive_time;
	}

	public String getUser_area_city_id() {
		return user_area_city_id;
	}

	public void setUser_area_city_id(String user_area_city_id) {
		this.user_area_city_id = user_area_city_id;
	}

	public String getUser_area_city_name() {
		return user_area_city_name;
	}

	public void setUser_area_city_name(String user_area_city_name) {
		this.user_area_city_name = user_area_city_name;
	}

	public Integer getUser_score() {
		return user_score;
	}

	public void setUser_score(Integer user_score) {
		this.user_score = user_score;
	}

	public String getLast_arrive_time() {
		return last_arrive_time;
	}

	public void setLast_arrive_time(String last_arrive_time) {
		this.last_arrive_time = last_arrive_time;
	}
	
	
}
