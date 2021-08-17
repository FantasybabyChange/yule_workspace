package com.yule.search.query;

import java.io.Serializable;
import java.util.List;


/**
 * 企业
 */
public class CompanySearchQuery implements Serializable {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2253942079860261458L;
	
	// 公司名称
	private String name;
	
	private String area_city_id;
	
	private String area_city_name;
	
	private List<String> area_county;
	
	private List<String> area_business;
	// 档次表主键id外键
	private List<String> company_grade;
	// 行业id外键
	private List<String> company_category;
	
	private Double company_point_category;
	
	private Boolean sort_company_grade;
	
	private Boolean sort_company_point;

	public CompanySearchQuery() {
		super();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getArea_city_id() {
		return area_city_id;
	}

	public void setArea_city_id(String area_city_id) {
		this.area_city_id = area_city_id;
	}

	public List<String> getArea_county() {
		return area_county;
	}

	public void setArea_county(List<String> area_county) {
		this.area_county = area_county;
	}

	public List<String> getArea_business() {
		return area_business;
	}

	public void setArea_business(List<String> area_business) {
		this.area_business = area_business;
	}

	public List<String> getCompany_grade() {
		return company_grade;
	}

	public void setCompany_grade(List<String> company_grade) {
		this.company_grade = company_grade;
	}

	public List<String> getCompany_category() {
		return company_category;
	}

	public void setCompany_category(List<String> company_category) {
		this.company_category = company_category;
	}

	public Double getCompany_point_category() {
		return company_point_category;
	}

	public void setCompany_point_category(Double company_point_category) {
		this.company_point_category = company_point_category;
	}

	public Boolean getSort_company_grade() {
		return sort_company_grade;
	}

	public Boolean getSort_company_point() {
		return sort_company_point;
	}


	public void setSort_company_grade(Boolean sort_company_grade) {
		this.sort_company_grade = sort_company_grade;
	}


	public void setSort_company_point(Boolean sort_company_point) {
		this.sort_company_point = sort_company_point;
	}

	public String getArea_city_name() {
		return area_city_name;
	}

	public void setArea_city_name(String area_city_name) {
		this.area_city_name = area_city_name;
	}

	
}
