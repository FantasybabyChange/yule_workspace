package com.yule.admin.param;

import java.io.Serializable;
import java.util.List;

public class UpdateCompanyServeCheckParam implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8963962032061209318L;

	private String company_id;

	private List<String> company_serve_id;
	
	public String getCompany_id() {
		return company_id;
	}

	public void setCompany_id(String company_id) {
		this.company_id = company_id;
	}

	public List<String> getCompany_serve_id() {
		return company_serve_id;
	}

	public void setCompany_serve_id(List<String> company_serve_id) {
		this.company_serve_id = company_serve_id;
	}

	public UpdateCompanyServeCheckParam() {
		super();
	}

}
