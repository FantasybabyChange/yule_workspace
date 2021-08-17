package com.yule.admin.param;

import java.io.Serializable;
import java.util.List;

public class InsertCompanyPhoneParam implements Serializable {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4393520241178827910L;
	
	
	private List<String> id;
	// 企业id
	private String company_id;
	// 电话名称
	private List<String> name;
	// 电话
	private List<String> phone;
	
	public List<String> getId() {
		return id;
	}

	public void setId(List<String> id) {
		this.id = id;
	}
	
	public String getCompany_id() {
		return company_id;
	}

	public void setCompany_id(String company_id) {
		this.company_id = company_id;
	}

	public List<String> getName() {
		return name;
	}

	public void setName(List<String> name) {
		this.name = name;
	}

	public List<String> getPhone() {
		return phone;
	}

	public void setPhone(List<String> phone) {
		this.phone = phone;
	}

	public InsertCompanyPhoneParam() {
		super();
	}
	
}
