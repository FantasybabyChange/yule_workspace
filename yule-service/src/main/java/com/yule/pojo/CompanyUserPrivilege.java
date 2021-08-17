package com.yule.pojo;

import java.io.Serializable;

public class CompanyUserPrivilege implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5786571230576688836L;
	
	private String id;
	private String company_user_id;
	private String company_privilege_id;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getCompany_user_id() {
		return company_user_id;
	}
	public void setCompany_user_id(String company_user_id) {
		this.company_user_id = company_user_id;
	}
	public String getCompany_privilege_id() {
		return company_privilege_id;
	}
	public void setCompany_privilege_id(String company_privilege_id) {
		this.company_privilege_id = company_privilege_id;
	}
	

}
