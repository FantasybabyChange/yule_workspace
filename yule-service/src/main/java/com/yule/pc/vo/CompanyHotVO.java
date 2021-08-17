package com.yule.pc.vo;

import java.io.Serializable;

public class CompanyHotVO implements Serializable{
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 6130526284964225458L;
	
	private String id;

	private String name;
	
	private String company_grade_name;
	
	private String address_detail;
	
	private String area_county_name;
	
	private String area_city_name;
	
	private Integer rebast;
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String getCompany_grade_name() {
		return company_grade_name;
	}

	public void setCompany_grade_name(String company_grade_name) {
		this.company_grade_name = company_grade_name;
	}

	public String getAddress_detail() {
		return address_detail;
	}

	public void setAddress_detail(String address_detail) {
		this.address_detail = address_detail;
	}

	public String getArea_county_name() {
		return area_county_name;
	}

	public void setArea_county_name(String area_county_name) {
		this.area_county_name = area_county_name;
	}

	public String getArea_city_name() {
		return area_city_name;
	}

	public void setArea_city_name(String area_city_name) {
		this.area_city_name = area_city_name;
	}

	public Integer getRebast() {
		return rebast;
	}

	public void setRebast(Integer rebast) {
		this.rebast = rebast;
	}
	
}
