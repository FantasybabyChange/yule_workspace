package com.yule.mongo.pojo;

import java.io.Serializable;
import java.util.Date;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import com.yule.mongo.constant.CollectionConst;

/**
 * 企业结算
 */
@Document(collection=CollectionConst.COMPANY_BALANCE)
public class CompanyBalance implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1452213332383308333L;

	@Id
	private ObjectId id;
	@Indexed
	private String company_id;
	// 企业名
	@Indexed
	private String company_name;
	// 订单数
	@Indexed
	private int orders_count;
	
	private double commision;
	// 订单总收入
	private double orders_expense_sum;
	// 积分总和
	private double orders_score_sum;
	// 支付
	private double pay_money;
	// 支付时间
	@Indexed
	private Date pay_time;
	// 支付状态
	@Indexed
	private Integer pay_status;
	// 创建时间
	@Indexed
	private Date create_time;
	
	public ObjectId getId() {
		return id;
	}

	public String getCompany_id() {
		return company_id;
	}

	public void setCompany_id(String company_id) {
		this.company_id = company_id;
	}

	public String getCompany_name() {
		return company_name;
	}

	public void setCompany_name(String company_name) {
		this.company_name = company_name;
	}

	public int getOrders_count() {
		return orders_count;
	}

	public void setOrders_count(int orders_count) {
		this.orders_count = orders_count;
	}

	public Date getCreate_time() {
		return create_time;
	}

	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}

	public Date getPay_time() {
		return pay_time;
	}

	public void setPay_time(Date pay_time) {
		this.pay_time = pay_time;
	}

	public void setId(ObjectId id) {
		this.id = id;
	}

	public double getCommision() {
		return commision;
	}

	public void setCommision(double commision) {
		this.commision = commision;
	}

	public double getOrders_expense_sum() {
		return orders_expense_sum;
	}

	public void setOrders_expense_sum(double orders_expense_sum) {
		this.orders_expense_sum = orders_expense_sum;
	}

	public double getOrders_score_sum() {
		return orders_score_sum;
	}

	public void setOrders_score_sum(double orders_score_sum) {
		this.orders_score_sum = orders_score_sum;
	}

	public double getPay_money() {
		return pay_money;
	}

	public void setPay_money(double pay_money) {
		this.pay_money = pay_money;
	}
	
	public Integer getPay_status() {
		return pay_status;
	}

	public void setPay_status(Integer pay_status) {
		this.pay_status = pay_status;
	}

	public CompanyBalance() {
		super();
	}

	
}
