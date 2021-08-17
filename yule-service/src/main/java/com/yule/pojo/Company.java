package com.yule.pojo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * 企业
 */
public class Company implements Serializable {

	private static final long serialVersionUID = 6881536436118001217L;

	private String id;
	
	private String openId;
	
	private Integer code;
	
	private String mail;
	
	private String phone;
	//
	private String title;
	// 公司名称
	private String name;
	// 拼音名称
	private String pinyin;
	// 营业时间
	private String hours;
	// 商家详细介绍 
	private String details;
	// 周边介绍
	private String periphery;
	//温馨提示
	private String warm_prompt;
	//最低消费
	private BigDecimal min_expense;
	// 折扣
	private String rebast;
	// 档次表主键id外键
	private String company_grade_id;
	// 行业id外键
	private String company_category_id;
	private Integer cooperatory_type;
	private String salesman_id;
	private Integer grade_level;
	// 是否删除0未删除1删除
	private Integer is_delete;
	//支付方式
	private Integer pay_type;
	//提成
	private Integer commision;
	// 创建时间
	private Timestamp create_time;
	// 更新时间
	private Timestamp update_time;
	
	private Integer is_auth;

	private Integer order;

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
	
	public String getRebast() {
		return rebast;
	}
	public void setRebast(String rebast) {
		this.rebast = rebast;
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
	public Timestamp getCreate_time() {
		return create_time;
	}
	public void setCreate_time(Timestamp create_time) {
		this.create_time = create_time;
	}
	public Timestamp getUpdate_time() {
		return update_time;
	}
	public void setUpdate_time(Timestamp update_time) {
		this.update_time = update_time;
	}
	public Integer getIs_delete() {
		return is_delete;
	}
	public void setIs_delete(Integer is_delete) {
		this.is_delete = is_delete;
	}
	public Integer getGrade_level() {
		return grade_level;
	}
	public void setGrade_level(Integer grade_level) {
		this.grade_level = grade_level;
	}
	public String getWarm_prompt() {
		return warm_prompt;
	}
	public void setWarm_prompt(String warm_prompt) {
		this.warm_prompt = warm_prompt;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Integer getCode() {
		return code;
	}
	public void setCode(Integer code) {
		this.code = code;
	}
	public BigDecimal getMin_expense() {
		return min_expense;
	}
	public void setMin_expense(BigDecimal min_expense) {
		this.min_expense = min_expense;
	}
	public Integer getCommision() {
		return commision;
	}
	public void setCommision(Integer commision) {
		this.commision = commision;
	}
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public Integer getPay_type() {
		return pay_type;
	}
	public void setPay_type(Integer pay_type) {
		this.pay_type = pay_type;
	}
	public Integer getIs_auth() {
		return is_auth;
	}
	public void setIs_auth(Integer is_auth) {
		this.is_auth = is_auth;
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
	public String getOpenId() {
		return openId;
	}
	public void setOpenId(String openId) {
		this.openId = openId;
	}
	
	public Integer getOrder() {
		return order;
	}
	public void setOrder(Integer order) {
		this.order = order;
	}
	public Company() {
		super();
	}
}
