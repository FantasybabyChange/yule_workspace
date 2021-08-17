package com.yule.admin.vo;

import java.io.Serializable;

/**
 * 权限VO
 */
public class AdminPrivilegeVO implements Serializable {

	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3342746342538733166L;
	
	private String id;
	// 权限名称
	private String name;
	// 父id
	private String parent_id;
	// 是否选中
	private Integer is_check;
	
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
	public Integer getIs_check() {
		return is_check;
	}
	public void setIs_check(Integer is_check) {
		this.is_check = is_check;
	}
	public String getParent_id() {
		return parent_id;
	}
	public void setParent_id(String parent_id) {
		this.parent_id = parent_id;
	}
	public AdminPrivilegeVO() {
		super();
	}
}
