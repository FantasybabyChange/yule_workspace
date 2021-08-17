package com.yule.pojo;

import java.io.Serializable;

/**
 * 企业服务设施关联
 */
public class CompanyServeRelevant implements Serializable {
	private static final long serialVersionUID = -4682122815425420861L;
	
	private String id;
	//服务设施id
	private String company_serve_id;
	//企业id	
	private String company_id;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getCompany_id() {
		return company_id;
	}
	public void setCompany_id(String company_id) {
		this.company_id = company_id;
	}
	public String getCompany_serve_id() {
		return company_serve_id;
	}
	public void setCompany_serve_id(String company_serve_id) {
		this.company_serve_id = company_serve_id;
	}
	public CompanyServeRelevant() {
		super();
	}
}
