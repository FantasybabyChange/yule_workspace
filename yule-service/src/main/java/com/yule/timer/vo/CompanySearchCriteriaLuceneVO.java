package com.yule.timer.vo;

import java.io.Serializable;

/**
 * 企业搜索
 */
public class CompanySearchCriteriaLuceneVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3780880346123971855L;
	
	private String id;
	// 公司名称
	private String name;
	
	private String province_name;
	
	private String city_name;
	
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
	public String getProvince_name() {
		return province_name;
	}
	public void setProvince_name(String province_name) {
		this.province_name = province_name;
	}
	public String getCity_name() {
		return city_name;
	}
	public void setCity_name(String city_name) {
		this.city_name = city_name;
	}
	public CompanySearchCriteriaLuceneVO() {
		super();
	}

}
