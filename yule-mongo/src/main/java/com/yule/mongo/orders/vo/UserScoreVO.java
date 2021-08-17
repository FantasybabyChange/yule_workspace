package com.yule.mongo.orders.vo;

import java.io.Serializable;

public class UserScoreVO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6299813983957131987L;

	private int user_score_sum;

	private int user_score_status;

	public int getUser_score_sum() {
		return user_score_sum;
	}

	public void setUser_score_sum(int user_score_sum) {
		this.user_score_sum = user_score_sum;
	}

	public int getUser_score_status() {
		return user_score_status;
	}

	public void setUser_score_status(int user_score_status) {
		this.user_score_status = user_score_status;
	}

	public UserScoreVO() {
		super();
	}

	@Override
	public String toString() {
		return "UserScoreVO [user_score_sum=" + user_score_sum
				+ ", user_score_status=" + user_score_status + "]";
	}
	
}
