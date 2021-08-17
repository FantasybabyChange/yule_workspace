package com.yule.pojo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

public class CompanyExpense implements Serializable{

	/**
	 * 企业消费
	 */
	private static final long serialVersionUID = -7119145166812090046L;
	
	private String id;
	//企业id
	private String company_id;
	//分类id
	private String expense_category_id;

	private String name ;
	//价格
	private BigDecimal price;
	
	private Integer is_delete;
	
	//创建和修改时间
	private Timestamp create_time;
	
	private Timestamp update_time;

	public CompanyExpense() {
		super();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCompany_id() {
		return company_id;
	}

	public void setCompany_id(String company_id) {
		this.company_id = company_id;
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

	public Integer getIs_delete() {
		return is_delete;
	}

	public void setIs_delete(Integer is_delete) {
		this.is_delete = is_delete;
	}

	public Timestamp getCreate_time() {
		return create_time;
	}

	public void setCreate_time(Timestamp create_time) {
		this.create_time = create_time;
	}

	public Timestamp getUpdate_time() {
		return update_time;
	}

	public void setUpdate_time(Timestamp update_time) {
		this.update_time = update_time;
	}

	public String getExpense_category_id() {
		return expense_category_id;
	}

	public void setExpense_category_id(String expense_category_id) {
		this.expense_category_id = expense_category_id;
	}

	
}
