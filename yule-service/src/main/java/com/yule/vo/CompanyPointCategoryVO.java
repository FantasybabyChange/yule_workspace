package com.yule.vo;

import java.io.Serializable;

public class CompanyPointCategoryVO implements Serializable{
	

	/**
	 * 
	 */
	private static final long serialVersionUID = -171113193158726124L;

	private String id;
	
	private String name;
	
	private String point;

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

	public String getPoint() {
		return point;
	}

	public void setPoint(String point) {
		this.point = point;
	}
	
}
