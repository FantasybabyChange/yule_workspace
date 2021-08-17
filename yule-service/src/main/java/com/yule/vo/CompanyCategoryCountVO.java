package com.yule.vo;

import java.io.Serializable;

public class CompanyCategoryCountVO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8444070621635477303L;
	
	private String id;
	
	private String name;
	
	private Integer company_count;
	
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

	public Integer getCompany_count() {
		return company_count;
	}

	public void setCompany_count(Integer company_count) {
		this.company_count = company_count;
	}
	
	
}
