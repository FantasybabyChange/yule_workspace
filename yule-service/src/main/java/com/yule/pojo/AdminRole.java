package com.yule.pojo;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * 系统角色
 */
public class AdminRole implements Serializable {

	private static final long serialVersionUID = 3355631044064168023L;

	private String id;
	// 角色名称
	private String name;
	// 是否删除
	private Integer is_delete;
	//0是内置管理,1其他
    private Integer is_admin;
	// 创建时间
	private Timestamp create_time;
	// 更新时间
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
	public Integer getIs_delete() {
		return is_delete;
	}
	public void setIs_delete(Integer is_delete) {
		this.is_delete = is_delete;
	}
	public Integer getIs_admin() {
		return is_admin;
	}
	public void setIs_admin(Integer is_admin) {
		this.is_admin = is_admin;
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
	public AdminRole() {
		super();
	}
	
	

}
