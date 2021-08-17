package com.yule.admin.param;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

public class InsertCompanyManagerParam implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2665745524513251322L;
	private String id;
	
	private String company_category_id;
	//企业名称
	private String name;
	// 帐号
	private String account;
	// 密码
	private String password;
	// 企业CODE
	private String code;
	// 登录时间
	private Timestamp login_time;
	// 状态0 启用1未启用
	private Integer status;
	//支付方式
	private Integer pay_type;
	private Integer cooperatory_type;
	private String salesman_id;
	//提成
	private Integer commision;
	// 折扣
	private String rebast;
	//最低消费
	private BigDecimal min_expense;
	// 档次表主键id外键
	private String company_grade_id;
	// 是否删除0删除1未删除
	private Integer is_delete;
	// 创建时间
	private Timestamp create_time;
	// 更新时间
	private Timestamp update_time;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public Timestamp getLogin_time() {
		return login_time;
	}
	public void setLogin_time(Timestamp login_time) {
		this.login_time = login_time;
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
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public InsertCompanyManagerParam() {
		super();
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCompany_category_id() {
		return company_category_id;
	}
	public void setCompany_category_id(String company_category_id) {
		this.company_category_id = company_category_id;
	}
	public Integer getPay_type() {
		return pay_type;
	}
	public void setPay_type(Integer pay_type) {
		this.pay_type = pay_type;
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
	public String getCompany_grade_id() {
		return company_grade_id;
	}
	public void setCompany_grade_id(String company_grade_id) {
		this.company_grade_id = company_grade_id;
	}
	public String getRebast() {
		return rebast;
	}
	public void setRebast(String rebast) {
		this.rebast = rebast;
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
