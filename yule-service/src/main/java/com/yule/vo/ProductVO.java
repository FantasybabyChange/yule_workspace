package com.yule.vo;

import java.io.Serializable;
import java.math.BigDecimal;

public class ProductVO implements Serializable {
	private static final long serialVersionUID = -8905409846989895978L;
	//产品分类id
    private String id;
    //产品分类名称
    private String name;
    //产品id
    private String product_category_id;
    //最低消费
    private BigDecimal min_expense;
    //可容纳人数
    private Integer person_num;
    //状态 0有房1没房
    private Integer status;
    //公司名称
    private String company_name;
    
    private String company_id;
   
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

	public BigDecimal getMin_expense() {
		return min_expense;
	}

	public void setMin_expense(BigDecimal min_expense) {
		this.min_expense = min_expense;
	}

	public Integer getPerson_num() {
		return person_num;
	}

	public void setPerson_num(Integer person_num) {
		this.person_num = person_num;
	}

	public String getProduct_category_id() {
		return product_category_id;
	}

	public void setProduct_category_id(String product_category_id) {
		this.product_category_id = product_category_id;
	}
	
	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	
	public String getCompany_name() {
		return company_name;
	}

	public void setCompany_name(String company_name) {
		this.company_name = company_name;
	}

	
	public String getCompany_id() {
		return company_id;
	}

	public void setCompany_id(String company_id) {
		this.company_id = company_id;
	}

	public ProductVO() {
		super();
	}

	@Override
	public String toString() {
		return "ProductVO [id=" + id + ", name=" + name
				+ ", product_category_id=" + product_category_id
				+ ", min_expense=" + min_expense + ", person_num=" + person_num
				+ ", status=" + status + ", company_name=" + company_name + "]";
	} 
    
	
}
