package com.yule.timer.vo;

import java.io.Serializable;

/**
 * 企业任务
 */
public class CompanyTaskVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6951669090758400014L;
	private String id;
	// 公司名称
	private String name;
	//企业服务总数
	private Integer company_serve_count;
	//企业优惠总数
	private Integer company_favorable_count;
	//企业电话总数
	private Integer company_phone_count;
	//企业产品总数
	private Integer product_count;
	//企业消费总数
	private Integer company_expense_count;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getCompany_serve_count() {
		return company_serve_count;
	}
	public void setCompany_serve_count(Integer company_serve_count) {
		this.company_serve_count = company_serve_count;
	}
	public Integer getCompany_favorable_count() {
		return company_favorable_count;
	}
	public void setCompany_favorable_count(Integer company_favorable_count) {
		this.company_favorable_count = company_favorable_count;
	}
	public Integer getCompany_phone_count() {
		return company_phone_count;
	}
	public void setCompany_phone_count(Integer company_phone_count) {
		this.company_phone_count = company_phone_count;
	}
	public Integer getProduct_count() {
		return product_count;
	}
	public void setProduct_count(Integer product_count) {
		this.product_count = product_count;
	}
	public Integer getCompany_expense_count() {
		return company_expense_count;
	}
	public void setCompany_expense_count(Integer company_expense_count) {
		this.company_expense_count = company_expense_count;
	}
	
}