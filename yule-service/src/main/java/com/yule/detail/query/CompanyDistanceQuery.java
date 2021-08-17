package com.yule.detail.query;

import java.io.Serializable;

public class CompanyDistanceQuery implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3487474345349915992L;
	
	private String id;
	private String city_id;
	private String lng;
	private String lat;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getCity_id() {
		return city_id;
	}
	public void setCity_id(String city_id) {
		this.city_id = city_id;
	}
	public String getLng() {
		return lng;
	}
	public void setLng(String lng) {
		this.lng = lng;
	}
	public String getLat() {
		return lat;
	}
	public void setLat(String lat) {
		this.lat = lat;
	}
}
