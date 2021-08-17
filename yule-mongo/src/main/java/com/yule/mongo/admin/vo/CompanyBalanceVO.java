package com.yule.mongo.admin.vo;

import java.io.Serializable;
import java.util.Date;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.yule.mongo.constant.CollectionConst;

@Document(collection=CollectionConst.COMPANY_BALANCE)
public class CompanyBalanceVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3671150208570560636L;

	@Id
	private ObjectId id;
	
	private String company_id;

	private String company_name;
	// 订单数
	private String orders_count;

	private String orders_expense_sum;
	// 积分总和
	private String orders_score_sum;
	// 支付
	private String pay_money;

	private Date create_time;
	// 提成
	private double commision;
	// 支付状态
	private Integer pay_status;
	// 支付时间
	private Date pay_time;
	
	public String getCompany_id() {
		return company_id;
	}

	public void setCompany_id(String company_id) {
		this.company_id = company_id;
	}

	public String getOrders_count() {
		return orders_count;
	}

	public void setOrders_count(String orders_count) {
		this.orders_count = orders_count;
	}

	public String getOrders_expense_sum() {
		return orders_expense_sum;
	}

	public void setOrders_expense_sum(String orders_expense_sum) {
		this.orders_expense_sum = orders_expense_sum;
	}

	public String getOrders_score_sum() {
		return orders_score_sum;
	}

	public void setOrders_score_sum(String orders_score_sum) {
		this.orders_score_sum = orders_score_sum;
	}

	public String getPay_money() {
		return pay_money;
	}

	public void setPay_money(String pay_money) {
		this.pay_money = pay_money;
	}

	public Date getCreate_time() {
		return create_time;
	}

	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}


	public double getCommision() {
		return commision;
	}

	public void setCommision(double commision) {
		this.commision = commision;
	}

	public Integer getPay_status() {
		return pay_status;
	}

	public void setPay_status(Integer pay_status) {
		this.pay_status = pay_status;
	}

	public Date getPay_time() {
		return pay_time;
	}

	public void setPay_time(Date pay_time) {
		this.pay_time = pay_time;
	}

	public ObjectId getId() {
		return id;
	}

	public void setId(ObjectId id) {
		this.id = id;
	}

	public String getCompany_name() {
		return company_name;
	}

	public void setCompany_name(String company_name) {
		this.company_name = company_name;
	}
	
}
