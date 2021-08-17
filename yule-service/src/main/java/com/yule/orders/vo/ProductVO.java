package com.yule.orders.vo;

import java.io.Serializable;
import java.math.BigDecimal;

public class ProductVO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6947316681389781509L;

	private String product_id ;
	
	private String product_name ;
	
	private String product_face;
	//可容纳人数
	private Integer person_num;
	//最低消费
	private BigDecimal min_expense;
	//企业id
	private String company_id;
	//企业名
	private String company_name;
	//企业头像
	private String company_face;
	
	private String area_business_name;
	
	private String company_area_address;
	
	private String company_serve;

	public String getProduct_id() {
		return product_id;
	}

	public void setProduct_id(String product_id) {
		this.product_id = product_id;
	}

	public Integer getPerson_num() {
		return person_num;
	}

	public void setPerson_num(Integer person_num) {
		this.person_num = person_num;
	}

	public BigDecimal getMin_expense() {
		return min_expense;
	}

	public void setMin_expense(BigDecimal min_expense) {
		this.min_expense = min_expense;
	}

	public String getCompany_id() {
		return company_id;
	}

	public void setCompany_id(String company_id) {
		this.company_id = company_id;
	}

	public String getCompany_name() {
		return company_name;
	}

	public void setCompany_name(String company_name) {
		this.company_name = company_name;
	}

	public String getArea_business_name() {
		return area_business_name;
	}

	public void setArea_business_name(String area_business_name) {
		this.area_business_name = area_business_name;
	}

	public String getCompany_area_address() {
		return company_area_address;
	}

	public void setCompany_area_address(String company_area_address) {
		this.company_area_address = company_area_address;
	}

	public String getProduct_name() {
		return product_name;
	}

	public void setProduct_name(String product_name) {
		this.product_name = product_name;
	}

	public ProductVO() {
		super();
	}

	public String getProduct_face() {
		return product_face;
	}

	public void setProduct_face(String product_face) {
		this.product_face = product_face;
	}

	public String getCompany_face() {
		return company_face;
	}

	public void setCompany_face(String company_face) {
		this.company_face = company_face;
	}

	public String getCompany_serve() {
		return company_serve;
	}

	public void setCompany_serve(String company_serve) {
		this.company_serve = company_serve;
	}
	
}

