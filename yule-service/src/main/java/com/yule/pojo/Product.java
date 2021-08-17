package com.yule.pojo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * 产品
 */
public class Product implements Serializable {

	private static final long serialVersionUID = -2377505060159333121L;
	private String id;
	//企业类型id(外键)
	private String product_category_id;
	//企业id（外键）
	private String company_id;
	//可容纳人数
	private Integer person_num;
	//最低消费
	private BigDecimal min_expense;
	//状态
	private Integer status;
	//是否删除(0未删除,1删除)
	private Integer is_delete;
	//创建时间
	private Timestamp create_time;
	//更新时间
	private Timestamp update_time;

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getProduct_category_id() {
		return product_category_id;
	}
	public void setProduct_category_id(String product_category_id) {
		this.product_category_id = product_category_id;
	}
	public String getCompany_id() {
		return company_id;
	}
	public void setCompany_id(String company_id) {
		this.company_id = company_id;
	}
	public Integer getPerson_num() {
		return person_num;
	}
	public void setPerson_num(Integer person_num) {
		this.person_num = person_num;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
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
	public BigDecimal getMin_expense() {
		return min_expense;
	}
	public void setMin_expense(BigDecimal min_expense) {
		this.min_expense = min_expense;
	}
	public Product() {
		super();
		// TODO Auto-generated constructor stub
	}

}
