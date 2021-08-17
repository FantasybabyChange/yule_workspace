package com.yule.pojo;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 企业优惠
 */
public class CompanyFavorable implements Serializable {
	private static final long serialVersionUID = 7848394737972692854L;
	
	private String id;
	//企业id
	private String company_id;
	//优惠名称
	private String name;
	//价格
	private BigDecimal price;
	//优惠内容
	private String content;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getCompany_id() {
		return company_id;
	}
	public void setCompany_id(String company_id) {
		this.company_id = company_id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public CompanyFavorable() {
		super();
		// TODO Auto-generated constructor stub
	}	
}
