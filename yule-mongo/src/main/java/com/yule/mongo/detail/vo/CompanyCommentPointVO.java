package com.yule.mongo.detail.vo;

import java.io.Serializable;

public class CompanyCommentPointVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3104328093368024001L;
	
	private String name;
	private float point;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public float getPoint() {
		return point;
	}
	public void setPoint(float point) {
		this.point = point;
	}
}
