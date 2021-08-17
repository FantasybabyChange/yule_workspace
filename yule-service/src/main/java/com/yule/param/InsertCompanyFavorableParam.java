package com.yule.param;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

public class InsertCompanyFavorableParam implements Serializable {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5410334492822305341L;
	
	private List<String> id;
	//企业id
	private String company_id;
	//优惠名称
	private List<String> name;
	//价格
	private List<BigDecimal> price;
	//优惠内容
	private List<String> content;
	
	public List<String> getId() {
		return id;
	}

	public void setId(List<String> id) {
		this.id = id;
	}

	public String getCompany_id() {
		return company_id;
	}

	public void setCompany_id(String company_id) {
		this.company_id = company_id;
	}

	public List<String> getName() {
		return name;
	}

	public void setName(List<String> name) {
		this.name = name;
	}

	public List<BigDecimal> getPrice() {
		return price;
	}

	public void setPrice(List<BigDecimal> price) {
		this.price = price;
	}

	public List<String> getContent() {
		return content;
	}

	public void setContent(List<String> content) {
		this.content = content;
	}

	public InsertCompanyFavorableParam() {
		super();
		// TODO Auto-generated constructor stub
	}

	
}
