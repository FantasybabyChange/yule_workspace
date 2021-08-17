package com.yule.mongo.salesman.vo;

import java.io.Serializable;

public class SaleVo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7082543805573937844L;
	/**
	 * 
	 */
	
	private String product_id;
	//产品名称
	private String product_name;
	//订单数总和
	private Integer product_order_count;
	//销售统计的日期
	private String day;
	//销售统计的消费总和
	private Double expense_count;

	public String getProduct_id() {
		return product_id;
	}

	public void setProduct_id(String product_id) {
		this.product_id = product_id;
	}

	public String getProduct_name() {
		return product_name;
	}

	public void setProduct_name(String product_name) {
		this.product_name = product_name;
	}

	public Integer getProduct_order_count() {
		return product_order_count;
	}

	public void setProduct_order_count(Integer product_order_count) {
		this.product_order_count = product_order_count;
	}

	public String getDay() {
		return day;
	}

	public void setDay(String day) {
		this.day = day;
	}

	public Double getExpense_count() {
		return expense_count;
	}

	public void setExpense_count(Double expense_count) {
		this.expense_count = expense_count;
	}

}
