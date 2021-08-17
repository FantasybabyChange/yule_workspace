package com.yule.mongo.timer.vo;

import java.io.Serializable;

public class CompanyOrdersBalanceVO implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1298365862559908263L;

	private double orders_expense_sum;

	private int orders_score_sum;
	
	private int orders_count;

	public double getOrders_expense_sum() {
		return orders_expense_sum;
	}

	public void setOrders_expense_sum(double orders_expense_sum) {
		this.orders_expense_sum = orders_expense_sum;
	}
	
	public int getOrders_score_sum() {
		return orders_score_sum;
	}

	public void setOrders_score_sum(int orders_score_sum) {
		this.orders_score_sum = orders_score_sum;
	}

	public int getOrders_count() {
		return orders_count;
	}

	public void setOrders_count(int orders_count) {
		this.orders_count = orders_count;
	}
	
}
