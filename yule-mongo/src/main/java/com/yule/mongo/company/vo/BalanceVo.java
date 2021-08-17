package com.yule.mongo.company.vo;

import java.io.Serializable;

public class BalanceVo implements Serializable{

	/**
	 * 结算
	 */
	private static final long serialVersionUID = 8154596825901188794L;

	private String month;
	//订单总数
	private Integer orders_count;
	//订单收入
	private double orders_expense_count;
	//提成
	private double  commision;
	//需要支付
	private double pay;
	public String getMonth() {
		return month;
	}
	public void setMonth(String month) {
		this.month = month;
	}
	public Integer getOrders_count() {
		return orders_count;
	}
	public void setOrders_count(Integer orders_count) {
		this.orders_count = orders_count;
	}
	public double getOrders_expense_count() {
		return orders_expense_count;
	}
	public void setOrders_expense_count(double orders_expense_count) {
		this.orders_expense_count = orders_expense_count;
	}

	public double getCommision() {
		return commision;
	}
	public void setCommision(double commision) {
		this.commision = commision;
	}
	public double getPay() {
		return pay;
	}
	public void setPay(double pay) {
		this.pay = pay;
	}
	
}
