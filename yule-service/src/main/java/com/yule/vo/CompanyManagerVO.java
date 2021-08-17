package com.yule.vo;

import java.io.Serializable;
import java.sql.Timestamp;

public class CompanyManagerVO implements Serializable {
	
	/**原CompanyLogin的东西
	 * 
	 */
	private static final long serialVersionUID = -2140251146376126744L;
	private String id;
	
	private Integer code;
	//企业名称
	private String name;
	// 帐号
	private String account;
	// 密码
	private String password;
	private String salesman_id;
	private Integer rebast;
	private Integer commision;
	private String company_category_name;
	private String company_grade_name;
	private String company_category_id;
	private String company_grade_id;
	private Timestamp login_time;
	// 状态0 启用1未启用
	private Integer status;
	// 是否删除0删除1未删除
	private Integer is_delete;
	// 创建时间
	private Timestamp create_time;
	// 更新时间
	private Timestamp update_time;
	//排序号
	private Integer order;
	
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
	public CompanyManagerVO() {
		super();
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getCode() {
		return code;
	}
	public void setCode(Integer code) {
		this.code = code;
	}
	public String getSalesman_id() {
		return salesman_id;
	}
	public void setSalesman_id(String salesman_id) {
		this.salesman_id = salesman_id;
	}
	public Integer getRebast() {
		return rebast;
	}
	public void setRebast(Integer rebast) {
		this.rebast = rebast;
	}
	public Integer getCommision() {
		return commision;
	}
	public void setCommision(Integer commision) {
		this.commision = commision;
	}
	public String getCompany_category_name() {
		return company_category_name;
	}
	public void setCompany_category_name(String company_category_name) {
		this.company_category_name = company_category_name;
	}
	public String getCompany_grade_name() {
		return company_grade_name;
	}
	public void setCompany_grade_name(String company_grade_name) {
		this.company_grade_name = company_grade_name;
	}
	public String getCompany_category_id() {
		return company_category_id;
	}
	public void setCompany_category_id(String company_category_id) {
		this.company_category_id = company_category_id;
	}
	public String getCompany_grade_id() {
		return company_grade_id;
	}
	public void setCompany_grade_id(String company_grade_id) {
		this.company_grade_id = company_grade_id;
	}
	public Integer getOrder() {
		return order;
	}
	public void setOrder(Integer order) {
		this.order = order;
	}

	
}
