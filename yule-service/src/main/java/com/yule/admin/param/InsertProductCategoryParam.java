package com.yule.admin.param;

import java.io.Serializable;
import java.util.List;

public class InsertProductCategoryParam implements Serializable {
	
	private static final long serialVersionUID = 2916203037700731514L;
	
	private List<String> id;
    
	private String company_category_id;
	
	private List<String> name;
	
	private List<Integer> order;
	
	public List<String> getId() {
		return id;
	}

	public void setId(List<String> id) {
		this.id = id;
	}
	
	public String getCompany_category_id() {
		return company_category_id;
	}

	public void setCompany_category_id(String company_category_id) {
		this.company_category_id = company_category_id;
	}

	public List<String> getName() {
		return name;
	}

	public void setName(List<String> name) {
		this.name = name;
	}

	public List<Integer> getOrder() {
		return order;
	}

	public void setOrder(List<Integer> order) {
		this.order = order;
	}

	public InsertProductCategoryParam() {
		super();
	}

}
