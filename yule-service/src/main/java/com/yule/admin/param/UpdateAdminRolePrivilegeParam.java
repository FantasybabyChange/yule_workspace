package com.yule.admin.param;

import java.io.Serializable;
import java.util.List;

public class UpdateAdminRolePrivilegeParam implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7959268018968553942L;

	private String admin_role_id;

	private List<String> admin_privilege_id;
	
	public String getAdmin_role_id() {
		return admin_role_id;
	}

	public void setAdmin_role_id(String admin_role_id) {
		this.admin_role_id = admin_role_id;
	}

	public List<String> getAdmin_privilege_id() {
		return admin_privilege_id;
	}

	public void setAdmin_privilege_id(List<String> admin_privilege_id) {
		this.admin_privilege_id = admin_privilege_id;
	}

	public UpdateAdminRolePrivilegeParam() {
		super();
	}

}
