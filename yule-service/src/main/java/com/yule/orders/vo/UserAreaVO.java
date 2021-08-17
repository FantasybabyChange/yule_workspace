package com.yule.orders.vo;

import java.io.Serializable;

public class UserAreaVO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8861790057154400385L;
	
	private String user_area_city_id;
	
	private  String user_area_city_name;
	
	public String getUser_area_city_id() {
		return user_area_city_id;
	}
	public void setUser_area_city_id(String user_area_city_id) {
		this.user_area_city_id = user_area_city_id;
	}
	public String getUser_area_city_name() {
		return user_area_city_name;
	}
	public void setUser_area_city_name(String user_area_city_name) {
		this.user_area_city_name = user_area_city_name;
	}
	public UserAreaVO() {
		super();
	}
	
}
