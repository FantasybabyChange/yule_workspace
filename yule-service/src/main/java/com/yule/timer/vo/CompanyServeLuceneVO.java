package com.yule.timer.vo;

import java.io.Serializable;

/**
 * 企业服务设施
 */
public class CompanyServeLuceneVO implements Serializable {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6310779733873521757L;

	private String name;
	
	private String logo;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

	public CompanyServeLuceneVO() {
		super();
	}
}
