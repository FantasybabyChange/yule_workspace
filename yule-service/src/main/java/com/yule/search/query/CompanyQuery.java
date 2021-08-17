package com.yule.search.query;

import java.io.Serializable;

/**
 * 企业
 */
public class CompanyQuery implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1938925774224179342L;
	private String company_id;
	// 公司名称
	private String name;
	// 拼音名称
	private String pinyin;
	
	private String area_county_id;
	
	private String area_province_id;
	
	private String area_city_id;
	
	private String area_business_id;
	// 档次表主键id外键
	private String company_grade_id;
	// 行业id外键
	private String company_category_id;
	// 创建时间
	private String create_time;
	
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


	public String getArea_county_id() {
		return area_county_id;
	}


	public void setArea_county_id(String area_county_id) {
		this.area_county_id = area_county_id;
	}


	public String getArea_province_id() {
		return area_province_id;
	}


	public void setArea_province_id(String area_province_id) {
		this.area_province_id = area_province_id;
	}


	public String getCompany_id() {
		return company_id;
	}

	public void setCompany_id(String company_id) {
		this.company_id = company_id;
	}

	public String getArea_city_id() {
		return area_city_id;
	}


	public void setArea_city_id(String area_city_id) {
		this.area_city_id = area_city_id;
	}


	public String getArea_business_id() {
		return area_business_id;
	}


	public void setArea_business_id(String area_business_id) {
		this.area_business_id = area_business_id;
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


	public String getCreate_time() {
		return create_time;
	}


	public void setCreate_time(String create_time) {
		this.create_time = create_time;
	}


	public CompanyQuery() {
		super();
	}
}
