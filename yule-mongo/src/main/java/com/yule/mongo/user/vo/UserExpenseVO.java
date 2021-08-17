package com.yule.mongo.user.vo;

import java.io.Serializable;

public class UserExpenseVO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 292975159930666571L;
	
	private String ratio;
	
	private double money;

	public double getMoney() {
		return money;
	}

	public void setMoney(double money) {
		this.money = money;
	}

	public String getRatio() {
		return ratio;
	}

	public void setRatio(String ratio) {
		this.ratio = ratio;
	}
	
}
