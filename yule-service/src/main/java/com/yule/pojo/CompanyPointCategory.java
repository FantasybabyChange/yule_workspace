package com.yule.pojo;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * 企业评论类型
 */
public class CompanyPointCategory implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4020555949461311750L;
	
	private String id;
	// 分类名称
	private String name;
	// 分值
	private Integer point;
	// 是否删除
	private Integer is_delete;
	// 创建时间
	private Timestamp create_time;
	// 更新时间
	private Timestamp update_time;
	
	public CompanyPointCategory() {
		super();
	}
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
	public Integer getPoint() {
		return point;
	}
	public void setPoint(Integer point) {
		this.point = point;
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
	
}
