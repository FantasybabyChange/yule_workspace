package com.yule.mongo.company.vo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class CompanyCommentPointVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2711906509064366556L;

	private String order_num;

	private String user_id;
	
	private String user_name;
	//产品名称
	private List<String> name;
	//产品名称
	private List<Double> point;
	
	private Date create_time;
	
	public String getOrder_num() {
		return order_num;
	}

	public void setOrder_num(String order_num) {
		this.order_num = order_num;
	}

	public List<String> getName() {
		return name;
	}

	public void setName(List<String> name) {
		this.name = name;
	}
	public List<Double> getPoint() {
		return point;
	}
	public void setPoint(List<Double> point) {
		this.point = point;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public Date getCreate_time() {
		return create_time;
	}

	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}
	
}
