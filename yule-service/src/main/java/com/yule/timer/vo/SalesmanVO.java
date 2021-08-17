package com.yule.timer.vo;

import java.io.Serializable;

public class SalesmanVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8847923274916534880L;
	
	private String id;

	private String name;

	private double commision;

	public SalesmanVO() {
		super();
	}

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

	public double getCommision() {
		return commision;
	}

	public void setCommision(double commision) {
		this.commision = commision;
	}
	
}
