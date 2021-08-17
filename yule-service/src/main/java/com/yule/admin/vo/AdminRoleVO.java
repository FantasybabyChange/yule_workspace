package com.yule.admin.vo;

import java.io.Serializable;

/**
 * 角色VO
 */
public class AdminRoleVO implements Serializable {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8853862574254485394L;
	
	private String id;
	// 权限名称
	private String name;
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
	public AdminRoleVO() {
		super();
	}
}
