package com.yule.sms;

import java.io.Serializable;

import com.yule.util.SmsUtil;

public class OrdersBookSuccessSms extends SmsUtil implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -480809818297737511L;

	public OrdersBookSuccessSms() throws Exception {
		super();
	}
	
	private String name;
	
	private String order_num;
	
	private String company_name;
	
	private String product_name;
	
	private String last_arrive_time;
	
	private String captcha;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getOrder_num() {
		return order_num;
	}

	public void setOrder_num(String order_num) {
		this.order_num = order_num;
	}

	public String getCompany_name() {
		return company_name;
	}

	public void setCompany_name(String company_name) {
		this.company_name = company_name;
	}

	public String getProduct_name() {
		return product_name;
	}

	public void setProduct_name(String product_name) {
		this.product_name = product_name;
	}

	public String getLast_arrive_time() {
		return last_arrive_time;
	}

	public void setLast_arrive_time(String last_arrive_time) {
		this.last_arrive_time = last_arrive_time;
	}

	public String getCaptcha() {
		return captcha;
	}

	public void setCaptcha(String captcha) {
		this.captcha = captcha;
	}
	
}
