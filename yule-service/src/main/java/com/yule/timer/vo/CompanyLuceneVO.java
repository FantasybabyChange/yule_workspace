package com.yule.timer.vo;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * 企业
 */
public class CompanyLuceneVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4740112390257600547L;
	
	private String id;
	// 公司名称
	private String name;
	// 拼音名称
	private String pinyin;
	// 营业时间
	private String hours;
	// 折扣
	private String rebast;
	
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
	
	private String product_count;
	// 档次表主键id外键
	private String company_grade_id;
	//
	private String company_grade_name;
	// 行业id外键
	private String company_category_id;
	//
	private String company_category_name;
	// 创建时间
	private Timestamp create_time;
	
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
	public String getPinyin() {
		return pinyin;
	}
	public void setPinyin(String pinyin) {
		this.pinyin = pinyin;
	}
	public String getHours() {
		return hours;
	}
	public void setHours(String hours) {
		this.hours = hours;
	}
	public String getRebast() {
		return rebast;
	}
	public void setRebast(String rebast) {
		this.rebast = rebast;
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
	public void setArea_business_id(String area_business_id) {
		this.area_business_id = area_business_id;
	}
	public String getArea_business_name() {
		return area_business_name;
	}
	public void setArea_business_name(String area_business_name) {
		this.area_business_name = area_business_name;
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
	public String getCompany_grade_id() {
		return company_grade_id;
	}
	public void setCompany_grade_id(String company_grade_id) {
		this.company_grade_id = company_grade_id;
	}
	public String getCompany_grade_name() {
		return company_grade_name;
	}
	public void setCompany_grade_name(String company_grade_name) {
		this.company_grade_name = company_grade_name;
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
	public Timestamp getCreate_time() {
		return create_time;
	}
	public void setCreate_time(Timestamp create_time) {
		this.create_time = create_time;
	}
	
	public String getProduct_count() {
		return product_count;
	}
	public void setProduct_count(String product_count) {
		this.product_count = product_count;
	}
	public CompanyLuceneVO() {
		super();
	}
}
