package com.yule.pojo;

import java.io.Serializable;

/**
 * 系统用户角色关联
 */
public class AdminUserRole implements Serializable {

	private static final long serialVersionUID = 7639195781685231169L;

	private String id;
	// 角色ID
	private String admin_user_id;
	// 用户ID
	private String admin_role_id;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getAdmin_user_id() {
		return admin_user_id;
	}

	public void setAdmin_user_id(String admin_user_id) {
		this.admin_user_id = admin_user_id;
	}

	public String getAdmin_role_id() {
		return admin_role_id;
	}

	public void setAdmin_role_id(String admin_role_id) {
		this.admin_role_id = admin_role_id;
	}

	public AdminUserRole() {
		super();
	}

}
