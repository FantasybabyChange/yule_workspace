package com.yule.pojo;

import java.io.Serializable;

public class CompanyExpenseCategory implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2895137558321022293L;

	private String id;

	private String parent_id;

	private String name;

	public CompanyExpenseCategory() {
		super();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getParent_id() {
		return parent_id;
	}

	public void setParent_id(String parent_id) {
		this.parent_id = parent_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
}
