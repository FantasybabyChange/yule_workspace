package com.yule.pojo;

import java.io.Serializable;

/**
 * 商圈
 */
public class AreaBusiness implements Serializable {

	private static final long serialVersionUID = 1729538136831487113L;

	private String id;
	// 所属市id(外键)
	private String area_city_id;
	// 名称
	private String name;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getArea_city_id() {
		return area_city_id;
	}

	public void setArea_city_id(String area_city_id) {
		this.area_city_id = area_city_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public AreaBusiness() {
		super();
	}

}
