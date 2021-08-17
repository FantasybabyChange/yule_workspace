package com.yule.mongo.pc.vo;

import java.io.Serializable;

public class CompanyCommentVO implements Serializable{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1510565828071621355L;

	private String point;
	
	private String point_name;
	
	private Integer company_comment_count;

	public String getPoint() {
		return point;
	}

	public void setPoint(String point) {
		this.point = point;
	}

	public Integer getCompany_comment_count() {
		return company_comment_count;
	}

	public void setCompany_comment_count(Integer company_comment_count) {
		this.company_comment_count = company_comment_count;
	}

	public String getPoint_name() {
		return point_name;
	}

	public void setPoint_name(String point_name) {
		this.point_name = point_name;
	}
	
}
