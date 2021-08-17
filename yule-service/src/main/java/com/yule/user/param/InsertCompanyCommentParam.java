package com.yule.user.param;

import java.io.Serializable;
import java.util.List;

public class InsertCompanyCommentParam implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1874977326797951747L;
	private String company_id;
	private String order_num;
	private String advice;
	private String comment;
	private List<String> commentCategory_name;
	private List<String> commentCategory_id;
	private List<Integer> companyPointCategory_point;
	private int commentType;
	public String getAdvice() {
		return advice;
	}
	public void setAdvice(String advice) {
		this.advice = advice;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	
	public List<String> getCommentCategory_name() {
		return commentCategory_name;
	}
	public void setCommentCategory_name(List<String> commentCategory_name) {
		this.commentCategory_name = commentCategory_name;
	}
	public List<String> getCommentCategory_id() {
		return commentCategory_id;
	}
	public void setCommentCategory_id(List<String> commentCategory_id) {
		this.commentCategory_id = commentCategory_id;
	}
	
	public List<Integer> getCompanyPointCategory_point() {
		return companyPointCategory_point;
	}
	public void setCompanyPointCategory_point(
			List<Integer> companyPointCategory_point) {
		this.companyPointCategory_point = companyPointCategory_point;
	}
	public int getCommentType() {
		return commentType;
	}
	public void setCommentType(int commentType) {
		this.commentType = commentType;
	}
	public String getCompany_id() {
		return company_id;
	}
	public void setCompany_id(String company_id) {
		this.company_id = company_id;
	}
	public String getOrder_num() {
		return order_num;
	}
	public void setOrder_num(String order_num) {
		this.order_num = order_num;
	}
	
}
