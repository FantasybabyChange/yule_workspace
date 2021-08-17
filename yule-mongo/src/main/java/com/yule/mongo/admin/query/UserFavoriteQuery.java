package com.yule.mongo.admin.query;

import java.io.Serializable;

public class UserFavoriteQuery implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3898132601477560466L;
	// 用户id
	private String userId;
	// 企业id
	private String companyId;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getCompanyId() {
		return companyId;
	}

	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}

	public UserFavoriteQuery() {
		super();
	}

}
