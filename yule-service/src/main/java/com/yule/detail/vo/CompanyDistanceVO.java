package com.yule.detail.vo;

import java.io.Serializable;

public class CompanyDistanceVO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7013921779256091303L;
	private String id;
	private String name;
	private Double distance;
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
	public Double getDistance() {
		return distance;
	}
	public void setDistance(Double distance) {
		this.distance = distance;
	}
}
