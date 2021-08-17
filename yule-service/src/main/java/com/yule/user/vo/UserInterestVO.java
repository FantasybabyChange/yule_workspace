package com.yule.user.vo;

import java.io.Serializable;

/**
 * 用户兴趣爱好VO
 */
public class UserInterestVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4689807496148000153L;
	// 企业分类ID
	private String company_category_id;
	// 企业档次ID
	private String company_grade_id;
	// 企业分类名称
	private String company_category_name;
	// 企业档次名称
	private String company_grade_name;
	
	public UserInterestVO() {
		super();
	}

	public String getCompany_category_name() {
		return company_category_name;
	}

	public void setCompany_category_name(String company_category_name) {
		this.company_category_name = company_category_name;
	}

	public String getCompany_grade_name() {
		return company_grade_name;
	}

	public void setCompany_grade_name(String company_grade_name) {
		this.company_grade_name = company_grade_name;
	}

	public String getCompany_category_id() {
		return company_category_id;
	}

	public void setCompany_category_id(String company_category_id) {
		this.company_category_id = company_category_id;
	}

	public String getCompany_grade_id() {
		return company_grade_id;
	}

	public void setCompany_grade_id(String company_grade_id) {
		this.company_grade_id = company_grade_id;
	}
	
}
