package com.yule.mongo.mobile.vo;

import java.io.Serializable;

public class OrdersHotAreaCityVO implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 663118326258637635L;

	private String area_city_id;
	
	private String area_city_name;

	public String getArea_city_id() {
		return area_city_id;
	}

	public void setArea_city_id(String area_city_id) {
		this.area_city_id = area_city_id;
	}

	public String getArea_city_name() {
		return area_city_name;
	}

	public void setArea_city_name(String area_city_name) {
		this.area_city_name = area_city_name;
	}

}
