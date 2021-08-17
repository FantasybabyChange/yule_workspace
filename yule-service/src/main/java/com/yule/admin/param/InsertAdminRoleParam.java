package com.yule.admin.param;

import java.io.Serializable;
import java.util.List;

public class InsertAdminRoleParam implements Serializable {

	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1710205096089703853L;
	
	private List<String> id;
	
	private List<String> name;
	
	public List<String> getId() {
		return id;
	}

	public void setId(List<String> id) {
		this.id = id;
	}

	public List<String> getName() {
		return name;
	}

	public void setName(List<String> name) {
		this.name = name;
	}

	public InsertAdminRoleParam() {
		super();
	}
	
	
}
