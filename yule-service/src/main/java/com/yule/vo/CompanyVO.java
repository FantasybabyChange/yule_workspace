package com.yule.vo;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 企业详情VO
 */
public class CompanyVO implements Serializable {


	/**
	 * 
	 */
	private static final long serialVersionUID = 7616225705408448483L;

	private String id;
	
	private String title;
	
	private String image_path;
	// 公司名称
	private String name;
	// 拼音名称		
	private String pinyin;
	// 营业时间
	private String hours;
	// 折扣
	private String rebast;
	//提成
	private Integer commision;
	
	private Integer cooperatory_type;
	
	private String salesman_id;
	//最低消费
	private BigDecimal min_expense;
	// 商家介绍
	private String details;
	// 周边介绍
	private String periphery;
	//温馨提示
	private String warm_prompt;
	// 行业名称
	private String company_category_id;
	// 行业名称
	private String company_category_name;
	//档次名称
	private String company_grade_id;
	//档次名称
	private String company_grade_name;
	//企业星级
	private Integer grade_level;
	//企业许可证 号码
	private String  business_license;      
	//公司许可证
	private String  business_license_image;
	//法人图片
	private String  legal_person_image;  
	//法人姓名
	private String  legal_person_name;  
	
	private Integer pay_type;
	
	private String 	pay_type_value;
	
	public CompanyVO() {
		super();
	}
	
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
	public String getPinyin() {
		return pinyin;
	}
	public void setPinyin(String pinyin) {
		this.pinyin = pinyin;
	}
	public String getHours() {
		return hours;
	}
	public void setHours(String hours) {
		this.hours = hours;
	}
	public String getRebast() {
		return rebast;
	}
	public void setRebast(String rebast) {
		this.rebast = rebast;
	}

	public String getCompany_grade_name() {
		return company_grade_name;
	}

	public void setCompany_grade_name(String company_grade_name) {
		this.company_grade_name = company_grade_name;
	}

	
	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}

	public String getPeriphery() {
		return periphery;
	}

	public void setPeriphery(String periphery) {
		this.periphery = periphery;
	}

	public String getCompany_grade_id() {
		return company_grade_id;
	}

	public void setCompany_grade_id(String company_grade_id) {
		this.company_grade_id = company_grade_id;
	}

	public String getCompany_category_id() {
		return company_category_id;
	}

	public void setCompany_category_id(String company_category_id) {
		this.company_category_id = company_category_id;
	}

	public String getCompany_category_name() {
		return company_category_name;
	}

	public void setCompany_category_name(String company_category_name) {
		this.company_category_name = company_category_name;
	}

	public Integer getGrade_level() {
		return grade_level;
	}

	public void setGrade_level(Integer grade_level) {
		this.grade_level = grade_level;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getBusiness_license_image() {
		return business_license_image;
	}

	public void setBusiness_license_image(String business_license_image) {
		this.business_license_image = business_license_image;
	}

	public String getLegal_person_name() {
		return legal_person_name;
	}

	public void setLegal_person_name(String legal_person_name) {
		this.legal_person_name = legal_person_name;
	}

	public String getLegal_person_image() {
		return legal_person_image;
	}

	public void setLegal_person_image(String legal_person_image) {
		this.legal_person_image = legal_person_image;
	}

	public String getBusiness_license() {
		return business_license;
	}

	public void setBusiness_license(String business_license) {
		this.business_license = business_license;
	}

	public String getImage_path() {
		return image_path;
	}

	public void setImage_path(String image_path) {
		this.image_path = image_path;
	}

	public String getWarm_prompt() {
		return warm_prompt;
	}

	public void setWarm_prompt(String warm_prompt) {
		this.warm_prompt = warm_prompt;
	}

	public Integer getCommision() {
		return commision;
	}

	public void setCommision(Integer commision) {
		this.commision = commision;
	}

	public BigDecimal getMin_expense() {
		return min_expense;
	}

	public void setMin_expense(BigDecimal min_expense) {
		this.min_expense = min_expense;
	}

	public Integer getPay_type() {
		return pay_type;
	}

	public void setPay_type(Integer pay_type) {
		this.pay_type = pay_type;
	}

	public String getPay_type_value() {
		return pay_type_value;
	}

	public void setPay_type_value(String pay_type_value) {
		this.pay_type_value = pay_type_value;
	}

	public Integer getCooperatory_type() {
		return cooperatory_type;
	}

	public void setCooperatory_type(Integer cooperatory_type) {
		this.cooperatory_type = cooperatory_type;
	}

	public String getSalesman_id() {
		return salesman_id;
	}

	public void setSalesman_id(String salesman_id) {
		this.salesman_id = salesman_id;
	}
	
}
