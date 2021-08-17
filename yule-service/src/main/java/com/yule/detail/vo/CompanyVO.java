package com.yule.detail.vo;

import java.io.Serializable;

public class CompanyVO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8576922865988630782L;
	private String id;
	//企业介绍
	private String details;
	//温馨提示
	private String warm_prompt;
	
	private String country_id;
	//街道名称
	private String country_name;
	//商业区名称
	private String business_name;
	
	private String city_id;
	//城市名称
	private String city_name;
	
	private String province_id ;
	//省份名称
	private String province_name ;
	//企业名称
	private String company_name;
	//企业详细地址
	private String address_detail;
	//营业时间
	private String hours;
	//支付类型
	private Integer pay_type;
	//支付类型名称
	private String pay_type_name;
	//企业档次
	private String company_grade_id;
	private String company_grade_name;
	private String company_category_id;
	private String company_category_name;
	//企业头像
	private String company_image_path;
	private Double lng;
	private Double lat;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getDetails() {
		return details;
	}
	public void setDetails(String details) {
		this.details = details;
	}
	public String getWarm_prompt() {
		return warm_prompt;
	}
	public void setWarm_prompt(String warm_prompt) {
		this.warm_prompt = warm_prompt;
	}
	public String getCountry_name() {
		return country_name;
	}
	public void setCountry_name(String country_name) {
		this.country_name = country_name;
	}
	public String getBusiness_name() {
		return business_name;
	}
	public void setBusiness_name(String business_name) {
		this.business_name = business_name;
	}
	public String getCity_name() {
		return city_name;
	}
	public void setCity_name(String city_name) {
		this.city_name = city_name;
	}
	public String getProvince_name() {
		return province_name;
	}
	public void setProvince_name(String province_name) {
		this.province_name = province_name;
	}
	public String getCompany_name() {
		return company_name;
	}
	public void setCompany_name(String company_name) {
		this.company_name = company_name;
	}
	public String getAddress_detail() {
		return address_detail;
	}
	public void setAddress_detail(String address_detail) {
		this.address_detail = address_detail;
	}
	public String getHours() {
		return hours;
	}
	public void setHours(String hours) {
		this.hours = hours;
	}
	public Integer getPay_type() {
		return pay_type;
	}
	public void setPay_type(Integer pay_type) {
		this.pay_type = pay_type;
	}
	public String getPay_type_name() {
		return pay_type_name;
	}
	public void setPay_type_name(String pay_type_name) {
		this.pay_type_name = pay_type_name;
	}
	public String getCompany_grade_name() {
		return company_grade_name;
	}
	public void setCompany_grade_name(String company_grade_name) {
		this.company_grade_name = company_grade_name;
	}
	public String getCompany_image_path() {
		return company_image_path;
	}
	public void setCompany_image_path(String company_image_path) {
		this.company_image_path = company_image_path;
	}
	public String getCountry_id() {
		return country_id;
	}
	public void setCountry_id(String country_id) {
		this.country_id = country_id;
	}
	public String getCity_id() {
		return city_id;
	}
	public void setCity_id(String city_id) {
		this.city_id = city_id;
	}
	public String getProvince_id() {
		return province_id;
	}
	public void setProvince_id(String province_id) {
		this.province_id = province_id;
	}
	public Double getLng() {
		return lng;
	}
	public void setLng(Double lng) {
		this.lng = lng;
	}
	public Double getLat() {
		return lat;
	}
	public void setLat(Double lat) {
		this.lat = lat;
	}
	public String getCompany_grade_id() {
		return company_grade_id;
	}
	public void setCompany_grade_id(String company_grade_id) {
		this.company_grade_id = company_grade_id;
	}
	public String getCompany_category_id() {
		return company_category_id;
	}
	public void setCompany_category_id(String company_category_id) {
		this.company_category_id = company_category_id;
	}
	public String getCompany_category_name() {
		return company_category_name;
	}
	public void setCompany_category_name(String company_category_name) {
		this.company_category_name = company_category_name;
	}
	
	
}
