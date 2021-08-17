package com.yule.company.vo;

import java.io.Serializable;

public class UserVO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6184738065366498337L;

	private String user_id;
	
	private int score_ratio;
	
	private String user_level_id;

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public int getScore_ratio() {
		return score_ratio;
	}

	public void setScore_ratio(int score_ratio) {
		this.score_ratio = score_ratio;
	}

	public String getUser_level_id() {
		return user_level_id;
	}

	public void setUser_level_id(String user_level_id) {
		this.user_level_id = user_level_id;
	}


}
