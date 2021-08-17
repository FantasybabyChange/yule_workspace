package com.yule.timer.vo;

import java.io.Serializable;

public class CompanyVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6198140869922148809L;

	private String id;

	private String company_name;

	private double commision;

	public CompanyVO() {
		super();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCompany_name() {
		return company_name;
	}

	public void setCompany_name(String company_name) {
		this.company_name = company_name;
	}

	public double getCommision() {
		return commision;
	}

	public void setCommision(double commision) {
		this.commision = commision;
	}

}
