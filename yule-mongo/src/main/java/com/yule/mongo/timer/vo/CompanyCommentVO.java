package com.yule.mongo.timer.vo;

import java.io.Serializable;

public class CompanyCommentVO implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3802997768161203095L;
	
	private double point;

	private int company_comment_count;

	public double getPoint() {
		return point;
	}

	public void setPoint(double point) {
		this.point = point;
	}

	public int getCompany_comment_count() {
		return company_comment_count;
	}
	public void setCompany_comment_count(int company_comment_count) {
		this.company_comment_count = company_comment_count;
	}


}
