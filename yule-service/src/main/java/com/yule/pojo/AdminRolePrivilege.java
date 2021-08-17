package com.yule.pojo;

import java.io.Serializable;

/**
 * 系统角色权限关联
 */
public class AdminRolePrivilege implements Serializable {

	private static final long serialVersionUID = -2805764085011434470L;

	private String id;
	// 权限ID
	private String admin_privilege_id;
	// 角色ID
	private String admin_role_id;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getAdmin_privilege_id() {
		return admin_privilege_id;
	}

	public void setAdmin_privilege_id(String admin_privilege_id) {
		this.admin_privilege_id = admin_privilege_id;
	}

	public String getAdmin_role_id() {
		return admin_role_id;
	}

	public void setAdmin_role_id(String admin_role_id) {
		this.admin_role_id = admin_role_id;
	}

	public AdminRolePrivilege(String id, String admin_privilege_id,
			String admin_role_id) {
		super();
		this.id = id;
		this.admin_privilege_id = admin_privilege_id;
		this.admin_role_id = admin_role_id;
	}

	public AdminRolePrivilege() {
		super();
	}

}
