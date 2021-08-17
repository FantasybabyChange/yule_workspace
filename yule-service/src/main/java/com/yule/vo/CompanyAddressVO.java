package com.yule.vo;

import java.io.Serializable;

/**
 * 企业地址
 */
public class CompanyAddressVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6163195108233617697L;
	
	private String id;
	
	private String area_county_id;
	
	private String area_county_name;
	
	private String area_province_id;
	
	private String area_province_name;
	
	private String area_city_id;
	
	private String area_city_name;
	
	private String area_business_id;
	
	private String area_business_name;
	// 详细地址
	private String address_detail;
	// 经度
	private String lng;
	// 纬度
	private String lat;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getAddress_detail() {
		return address_detail;
	}

	public void setAddress_detail(String address_detail) {
		this.address_detail = address_detail;
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

	public String getArea_county_id() {
		return area_county_id;
	}

	public void setArea_county_id(String area_county_id) {
		this.area_county_id = area_county_id;
	}

	public String getArea_county_name() {
		return area_county_name;
	}

	public void setArea_county_name(String area_county_name) {
		this.area_county_name = area_county_name;
	}

	public String getArea_province_id() {
		return area_province_id;
	}

	public void setArea_province_id(String area_province_id) {
		this.area_province_id = area_province_id;
	}

	public String getArea_province_name() {
		return area_province_name;
	}

	public void setArea_province_name(String area_province_name) {
		this.area_province_name = area_province_name;
	}

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

	public String getArea_business_id() {
		return area_business_id;
	}

	public void setArea_business_id(String  area_business_id) {
		this.area_business_id = area_business_id;
	}

	public String getArea_business_name() {
		return area_business_name;
	}

	public void setArea_business_name(String area_business_name) {
		this.area_business_name = area_business_name;
	}

	public CompanyAddressVO() {
		super();
	}

}
