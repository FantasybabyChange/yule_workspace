package com.yule.mongo.query;

import java.io.Serializable;
import java.util.List;

public class OrdersQuery implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1940084969931514520L;

	private String order_num;
	private String company_id;
	private String product_id;
	private String user_id;
	private String person_name;
	private Integer commision;
	private String phone;
	private String desc;
	private List<Integer> status;
	private Integer queryStatus;
	private Integer is_delete;
	private String start_time;
	private String end_time;
	private Integer time_type;
	private List<String> companyIds;
	private String company_name;
	private String product_name;
	public String getOrder_num() {
		return order_num;
	}
	public void setOrder_num(String order_num) {
		this.order_num = order_num;
	}
	public String getCompany_id() {
		return company_id;
	}
	public void setCompany_id(String company_id) {
		this.company_id = company_id;
	}
	public String getProduct_id() {
		return product_id;
	}
	public void setProduct_id(String product_id) {
		this.product_id = product_id;
	}
	public String getPerson_name() {
		return person_name;
	}
	public void setPerson_name(String person_name) {
		this.person_name = person_name;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public List<Integer> getStatus() {
		return status;
	}
	public void setStatus(List<Integer> status) {
		this.status = status;
	}
	public Integer getIs_delete() {
		return is_delete;
	}
	public void setIs_delete(Integer is_delete) {
		this.is_delete = is_delete;
	}
	public String getStart_time() {
		return start_time;
	}
	public void setStart_time(String start_time) {
		this.start_time = start_time;
	}
	public String getEnd_time() {
		return end_time;
	}
	public void setEnd_time(String end_time) {
		this.end_time = end_time;
	}
	public Integer getTime_type() {
		return time_type;
	}
	public void setTime_type(Integer time_type) {
		this.time_type = time_type;
	}
	
	public Integer getCommision() {
		return commision;
	}
	public void setCommision(Integer commision) {
		this.commision = commision;
	}
	public List<String> getCompanyIds() {
		return companyIds;
	}
	public void setCompanyIds(List<String> companyIds) {
		this.companyIds = companyIds;
	}
	public String getCompany_name() {
		return company_name;
	}
	public void setCompany_name(String company_name) {
		this.company_name = company_name;
	}
	public String getProduct_name() {
		return product_name;
	}
	public void setProduct_name(String product_name) {
		this.product_name = product_name;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	
	public Integer getQueryStatus() {
		return queryStatus;
	}
	public void setQueryStatus(Integer queryStatus) {
		this.queryStatus = queryStatus;
	}
	public OrdersQuery() {
		super();
	}
	
}