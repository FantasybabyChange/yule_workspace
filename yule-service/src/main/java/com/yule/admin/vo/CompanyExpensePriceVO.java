package com.yule.admin.vo;

import java.io.Serializable;
import java.math.BigDecimal;

public class CompanyExpensePriceVO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1152674887062701977L;
	/**
	 * 
	 */

	private String id;
	//企业id
	private String company_id;
	
	private String company_name;
	
	private String name ;
	//价格
	private BigDecimal price;
	
	private Integer is_delete;
	
	//创建和修改时间
	private String create_time;
	
	private String update_time;

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

	public String getCreate_time() {
		return create_time;
	}

	public void setCreate_time(String create_time) {
		this.create_time = create_time;
	}

	public String getUpdate_time() {
		return update_time;
	}

	public void setUpdate_time(String update_time) {
		this.update_time = update_time;
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

	public CompanyExpensePriceVO() {
		super();
	}
	
	
}
