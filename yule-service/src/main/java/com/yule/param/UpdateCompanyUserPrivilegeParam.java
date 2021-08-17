package com.yule.param;

import java.io.Serializable;
import java.util.List;

public class UpdateCompanyUserPrivilegeParam implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8268603502694751729L;

	/**
	 * 
	 */

	private String company_user_id;

	private List<String> company_privilege_id;
	
	public String getCompany_user_id() {
		return company_user_id;
	}

	public void setCompany_user_id(String company_user_id) {
		this.company_user_id = company_user_id;
	}

	public List<String> getCompany_privilege_id() {
		return company_privilege_id;
	}

	public void setCompany_privilege_id(List<String> company_privilege_id) {
		this.company_privilege_id = company_privilege_id;
	}

	public UpdateCompanyUserPrivilegeParam() {
		super();
	}

}
