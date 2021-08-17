package com.yule.pojo;

import java.io.Serializable;

/**
 * 省
 */
public class AreaProvince implements Serializable {

	private static final long serialVersionUID = -6620152461323232694L;

	private String id;
	// 省名称
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
	
	public AreaProvince() {
		super();
	}

}
