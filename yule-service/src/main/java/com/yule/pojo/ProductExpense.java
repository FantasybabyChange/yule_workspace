package com.yule.pojo;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * 产品消费表
 */
public class ProductExpense implements Serializable {


	private static final long serialVersionUID = -1109694195975829257L;

	private String id;
	//名称
	private String name;
	//企业id
	private String product_id;
	//产品最低消费
	private Integer min_expense;
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
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getProduct_id() {
		return product_id;
	}
	public void setProduct_id(String product_id) {
		this.product_id = product_id;
	}
	public Integer getMin_expense() {
		return min_expense;
	}
	public void setMin_expense(Integer min_expense) {
		this.min_expense = min_expense;
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
	public ProductExpense() {
		super();
	}
}
