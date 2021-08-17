package com.yule.mongo.user.vo;

import java.io.Serializable;
import java.util.Date;
/**
 * 企业浏览记录
 */
public class UserBrowseVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8749548375952729472L;
	//企业id
	private String company_id;
	
	private String count;
	//企业名称
	private String company_name;
	
	private String company_category_name;
	
	private String company_grade_name;
	
	private String address_datail;
	
	private String area_county_name;
	
	private String area_city_name;
	//创建时间
	private Date create_time;
	
	
	public UserBrowseVO() {
		super();
	}
	
	public String getCompany_id() {
		return company_id;
	}
	public void setCompany_id(String company_id) {
		this.company_id = company_id;
	}
	public String getCompany_name() {
		return company_name;
	}
	public void setCompany_name(String company_name) {
		this.company_name = company_name;
	}
	public String getAddress_datail() {
		return address_datail;
	}
	public void setAddress_datail(String address_datail) {
		this.address_datail = address_datail;
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
	
	public String getCompany_category_name() {
		return company_category_name;
	}

	public void setCompany_category_name(String company_category_name) {
		this.company_category_name = company_category_name;
	}

	public String getCompany_grade_name() {
		return company_grade_name;
	}

	public void setCompany_grade_name(String company_grade_name) {
		this.company_grade_name = company_grade_name;
	}

	public Date getCreate_time() {
		return create_time;
	}
	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}

	public String getCount() {
		return count;
	}

	public void setCount(String count) {
		this.count = count;
	}
	
}
