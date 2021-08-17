package com.yule.mongo.user.query;

import java.io.Serializable;

public class OrdersQuery implements Serializable {


	/**
	 * 
	 */
	private static final long serialVersionUID = 4052433522538342104L;
	
	private String name;

	private Integer dateType;
	
	private Integer status;
	
	private String userId;
	
	public Integer getDateType() {
		return dateType;
	}

	public void setDateType(Integer dateType) {
		this.dateType = dateType;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public OrdersQuery() {
		super();
	}
	
}