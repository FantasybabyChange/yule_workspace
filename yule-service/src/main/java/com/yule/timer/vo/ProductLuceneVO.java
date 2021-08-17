package com.yule.timer.vo;

import java.io.Serializable;

/**
 * 企业
 */
public class ProductLuceneVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4740112390257600547L;
	
	private String id;
	
	private String min_expense;
	
	private String name;
	
	private String person_num;
	
	private String is_seat;
	
	private String product_count;
	
	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public String getMin_expense() {
		return min_expense;
	}


	public void setMin_expense(String min_expense) {
		this.min_expense = min_expense;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}
	
	public String getPerson_num() {
		return person_num;
	}


	public void setPerson_num(String person_num) {
		this.person_num = person_num;
	}


	public String getIs_seat() {
		return is_seat;
	}


	public void setIs_seat(String is_seat) {
		this.is_seat = is_seat;
	}
	
	public String getProduct_count() {
		return product_count;
	}


	public void setProduct_count(String product_count) {
		this.product_count = product_count;
	}


	public ProductLuceneVO() {
		super();
	}
}
