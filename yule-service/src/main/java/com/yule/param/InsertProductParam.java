package com.yule.param;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
public class InsertProductParam implements Serializable {
	
	private static final long serialVersionUID = 794065867438379903L;
	
	private List<String> id;
	//企业类型id(外键)
	private List<String> product_category_id;
	//企业id（外键）
	private String company_id;
	//可容纳人数
	private List<Integer> person_num;
	//最低消费
	private List<BigDecimal> min_expense;
	
	public List<String> getId() {
		return id;
	}

	public void setId(List<String> id) {
		this.id = id;
	}

	public List<String> getProduct_category_id() {
		return product_category_id;
	}

	public void setProduct_category_id(List<String> product_category_id) {
		this.product_category_id = product_category_id;
	}

	public String getCompany_id() {
		return company_id;
	}

	public void setCompany_id(String company_id) {
		this.company_id = company_id;
	}

	public List<Integer> getPerson_num() {
		return person_num;
	}

	public void setPerson_num(List<Integer> person_num) {
		this.person_num = person_num;
	}

	public List<BigDecimal> getMin_expense() {
		return min_expense;
	}

	public void setMin_expense(List<BigDecimal> min_expense) {
		this.min_expense = min_expense;
	}

	public InsertProductParam() {
		super();
		// TODO Auto-generated constructor stub
	}
}
