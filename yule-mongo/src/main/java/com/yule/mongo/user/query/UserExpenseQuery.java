package com.yule.mongo.user.query;

import java.io.Serializable;

public class UserExpenseQuery implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5530897968216684527L;
	
	private String userId;
	
	private String startTime;
	
	private String endTime;
	
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public UserExpenseQuery() {
		super();
	}
	
}