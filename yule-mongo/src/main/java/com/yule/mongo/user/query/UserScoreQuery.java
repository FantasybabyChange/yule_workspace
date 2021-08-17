package com.yule.mongo.user.query;

import java.io.Serializable;

public class UserScoreQuery implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5530897968216684527L;

	private Integer type;
	
	private String userId;
	
	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public UserScoreQuery() {
		super();
	}
	
}