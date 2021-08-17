package com.yule.mongo.pc.vo;

import java.io.Serializable;
import java.util.Date;

public class OrdersHotVO implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1765936878502981115L;
	
	private String orders_count;
	
	private Date create_time;

	public String getOrders_count() {
		return orders_count;
	}

	public void setOrders_count(String orders_count) {
		this.orders_count = orders_count;
	}

	public Date getCreate_time() {
		return create_time;
	}

	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}

}
