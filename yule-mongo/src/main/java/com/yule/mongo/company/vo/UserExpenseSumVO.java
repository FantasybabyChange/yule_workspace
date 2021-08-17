package com.yule.mongo.company.vo;

import java.io.Serializable;

public class UserExpenseSumVO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8080940689384106767L;

	private double user_expense_sum;

	public double getUser_expense_sum() {
		return user_expense_sum;
	}

	public void setUser_expense_sum(double user_expense_sum) {
		this.user_expense_sum = user_expense_sum;
	}
	
	
}
