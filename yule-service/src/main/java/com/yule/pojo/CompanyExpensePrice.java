package com.yule.pojo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

public class CompanyExpensePrice implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8461585186583687673L;

	private String id;
	//企业id
	private String company_id;
	
	private String name ;
	//价格
	private BigDecimal price;
	
	private Integer is_delete;
	
	//创建和修改时间
	private Timestamp create_time;
	
	private Timestamp update_time;

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

	
	public String getCompany_id() {
		return company_id;
	}

	public void setCompany_id(String company_id) {
		this.company_id = company_id;
	}

	public CompanyExpensePrice() {
		super();
	}
	
}
