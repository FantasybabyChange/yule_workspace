package com.yule.pojo;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * 产品分类表
 */
public class ProductCategory implements Serializable {


	/**
	 * 
	 */
	private static final long serialVersionUID = 7982802243962260910L;
	
	private String id;
	//企业分类
	private String company_category_id;
	//产品分类名称
	private String name;
	// 排序好
	private Integer order;
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
	public String getCompany_category_id() {
		return company_category_id;
	}
	public void setCompany_category_id(String company_category_id) {
		this.company_category_id = company_category_id;
	}
	public Integer getOrder() {
		return order;
	}
	public void setOrder(Integer order) {
		this.order = order;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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

	public ProductCategory() {
		super();
	}
}
