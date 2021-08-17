package com.yule.mongo.pojo;

import java.io.Serializable;
import java.util.Date;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.yule.mongo.constant.CollectionConst;

/**
 * 业务员结算
 */
@Document(collection=CollectionConst.SALESMAN_BALANCE)
public class SalesmanBalance implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1452213332383308333L;

	@Id
	private ObjectId id;
	// 订单数
	private int orders_count;
	// 订单总收入
	private double orders_expense_sum;
	// 业务员id
	private String salesman_id;
	// 业务员名称
	private String salesman_name;
	// 业务员提成
	private double salesman_commision;
	// 支付金额
	private double pay_money;
	// 支付时间
	private Date pay_time;
	// 支付状态
	private Integer pay_status;
	// 创建时间
	private Date create_time;
	
	public ObjectId getId() {
		return id;
	}

	public int getOrders_count() {
		return orders_count;
	}

	public void setOrders_count(int orders_count) {
		this.orders_count = orders_count;
	}

	public String getSalesman_id() {
		return salesman_id;
	}

	public void setSalesman_id(String salesman_id) {
		this.salesman_id = salesman_id;
	}

	public String getSalesman_name() {
		return salesman_name;
	}

	public void setSalesman_name(String salesman_name) {
		this.salesman_name = salesman_name;
	}
	
	public double getSalesman_commision() {
		return salesman_commision;
	}

	public void setSalesman_commision(double salesman_commision) {
		this.salesman_commision = salesman_commision;
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

	public double getOrders_expense_sum() {
		return orders_expense_sum;
	}

	public void setOrders_expense_sum(double orders_expense_sum) {
		this.orders_expense_sum = orders_expense_sum;
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

	public SalesmanBalance() {
		super();
	}
	
}
