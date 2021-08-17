package com.yule.detail.vo;

import java.io.Serializable;
import java.math.BigDecimal;

public class CompanyExpenseVO implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 3829017571779163597L;
	private String id;
	private String parent_id;
	private String name;
	private BigDecimal price;
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
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
}
