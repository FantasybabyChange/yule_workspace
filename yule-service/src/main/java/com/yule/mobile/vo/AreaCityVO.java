package com.yule.mobile.vo;

import java.io.Serializable;

public class AreaCityVO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4927583577963503679L;
	
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
	public AreaCityVO() {
		super();
	}
}
