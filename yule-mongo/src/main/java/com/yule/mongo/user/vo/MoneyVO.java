package com.yule.mongo.user.vo;

import java.io.Serializable;

/**
 * 用户消费总计
 */
public class MoneyVO implements Serializable {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 399696359609343296L;
	
	private Double money;

	public Double getMoney() {
		return money;
	}

	public void setMoney(Double money) {
		this.money = money;
	}

}
