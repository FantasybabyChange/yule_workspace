package com.yule.sms;

import java.io.Serializable;

import com.yule.util.SmsUtil;

public class UserRegisterSuccessSms extends SmsUtil implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2048447426279582307L;

	public UserRegisterSuccessSms() throws Exception {
		super();
	}
	
	private Integer score;

	public Integer getScore() {
		return score;
	}

	public void setScore(Integer score) {
		this.score = score;
	}
	
}
