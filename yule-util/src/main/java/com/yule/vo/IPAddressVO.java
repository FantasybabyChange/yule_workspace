package com.yule.vo;

import java.io.Serializable;

public class IPAddressVO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8516975258236909143L;
	
	// 国家
	private String country;
	// 国家ID
	private String country_id;
	// 区域
	private String area;
	// 区域ID
	private String area_id;
	// 省
	private String region;
	// 省ID
	private String region_id;
	// 城市
	private String city;
	// 城市ID
	private String city_id;
	// 区/县
	private String county;
	// 区/县ID
	private String county_id;
	// 运营商
	private String isp;
	
	public IPAddressVO() {
		
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getCountry_id() {
		return country_id;
	}

	public void setCountry_id(String country_id) {
		this.country_id = country_id;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getArea_id() {
		return area_id;
	}

	public void setArea_id(String area_id) {
		this.area_id = area_id;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public String getRegion_id() {
		return region_id;
	}

	public void setRegion_id(String region_id) {
		this.region_id = region_id;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCity_id() {
		return city_id;
	}

	public void setCity_id(String city_id) {
		this.city_id = city_id;
	}

	public String getCounty() {
		return county;
	}

	public void setCounty(String county) {
		this.county = county;
	}

	public String getCounty_id() {
		return county_id;
	}

	public void setCounty_id(String county_id) {
		this.county_id = county_id;
	}

	public String getIsp() {
		return isp;
	}

	public void setIsp(String isp) {
		this.isp = isp;
	}
	
}
