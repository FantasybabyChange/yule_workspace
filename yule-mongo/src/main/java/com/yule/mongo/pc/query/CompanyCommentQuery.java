package com.yule.mongo.pc.query;

import java.io.Serializable;

public class CompanyCommentQuery implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8249523331890214814L;
	
	private String company_id;
	// 企业评论分类ID
	private String category_id;
	// 用户ID
	private String user_id;
	
	public String getCompany_id() {
		return company_id;
	}
	public void setCompany_id(String company_id) {
		this.company_id = company_id;
	}
	public String getCategory_id() {
		return category_id;
	}
	public void setCategory_id(String category_id) {
		this.category_id = category_id;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	
}
