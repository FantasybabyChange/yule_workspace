package com.yule.detail.vo;

import java.io.Serializable;

public class CompanyCountVO implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 2885737430506159988L;
	private Integer province_count;
	private Integer city_count;
	private Integer country_count;
	public Integer getProvince_count() {
		return province_count;
	}
	public void setProvince_count(Integer province_count) {
		this.province_count = province_count;
	}
	public Integer getCity_count() {
		return city_count;
	}
	public void setCity_count(Integer city_count) {
		this.city_count = city_count;
	}
	public Integer getCountry_count() {
		return country_count;
	}
	public void setCountry_count(Integer country_count) {
		this.country_count = country_count;
	}
	
	
}
