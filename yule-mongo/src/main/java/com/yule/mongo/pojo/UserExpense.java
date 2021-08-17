package com.yule.mongo.pojo;

import java.io.Serializable;
import java.util.Date;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import com.yule.mongo.constant.CollectionConst;

/**
 * 用户消费
 */
@Document(collection=CollectionConst.USER_EXPENSE)
public class UserExpense implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6920092003151663774L;
	
	@Id
	private ObjectId id;
	// 企业ID
	@Indexed
	private String company_id;
	// 企业名称
	private String company_name;
	// 用户ID
	@Indexed
	private String user_id;
	// 用户名称
	private String user_name;
	// 订单ID
	@Indexed
	private String order_num;
	// 消费金额
	private double money;
	@Indexed
	private Date create_time;
	
	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public double getMoney() {
		return money;
	}

	public void setMoney(double money) {
		this.money = money;
	}
	
	public String getOrder_num() {
		return order_num;
	}

	public void setOrder_num(String order_num) {
		this.order_num = order_num;
	}

	public Date getCreate_time() {
		return create_time;
	}

	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}

	public ObjectId getId() {
		return id;
	}

	public void setId(ObjectId id) {
		this.id = id;
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

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public UserExpense() {
		super();
	} 
	
}

