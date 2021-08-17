package com.yule.vo;

import java.io.Serializable;

public class CompanyGradeVO implements Serializable{
	


	/**
	 * 
	 */
	private static final long serialVersionUID = 2580795165936645276L;

	private String id;
	
	private String name;

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
	
}
