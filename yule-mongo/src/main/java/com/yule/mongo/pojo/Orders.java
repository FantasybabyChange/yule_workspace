package com.yule.mongo.pojo;

import java.io.Serializable;
import java.util.Date;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import com.yule.mongo.constant.CollectionConst;

/**
 * 订单
 */
@Document(collection=CollectionConst.ORDERS)
public class Orders implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1940084969931514520L;
	
	@Id
	private ObjectId id;
	// 订单编号
	@Indexed(unique =true)
	private String order_num;
	// 用户ID
	@Indexed
	private String user_id;
	// 用户区域城市ID
	@Indexed
	private String user_area_city_id;
	// 用户区域城市名称
	private String user_area_city_name;
	//用户微信ID
	private String user_openid;
	// 企业ID
	@Indexed
	private String company_id;
	// 企业名称
	private String company_name;
	//企业微信ID
	private String company_openid;
	// 企业区域城市ID
	@Indexed
	private String company_area_city_id;
	// 企业区域城市名称
	private String company_area_city_name;
	@Indexed
	private String company_area_province_id;
	// 企业区域城市名称
	private String company_area_province_name;
	// 产品名称
	@Indexed
	private String product_id;
	// 产品名称
	private String product_name;
	//是否评论
	@Indexed
	private Integer is_comment;
	// 预计到店时间
	private Date estimate_arrive_time;
	
	private String last_arrive_time;
	// 实际到店时间
	private Date arrive_time;
	// 客户姓名
	private String customer_name;
	// 手机号
	private String customer_phone;
	//邮件
	private String customer_mail;
	// 消费金额
	private double expense_money;
	// 特殊要求
	private String desc;
	// 状态
	@Indexed
	private Integer status;
	// 类型 0 休闲 1 商务 
	private Integer type;
	@Indexed
	private Integer is_delete;
	@Indexed
	private String create_year;
	@Indexed
	private String create_month;
	@Indexed
	private String create_day;
	@Indexed
	private String time;
	@Indexed
	private Date create_time;
	
	private Date update_time;
	
	private Integer user_score;
	
	public ObjectId getId() {
		return id;
	}

	public void setId(ObjectId id) {
		this.id = id;
	}
	
	public String getOrder_num() {
		return order_num;
	}

	public void setOrder_num(String order_num) {
		this.order_num = order_num;
	}
	
	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
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

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}


	public Date getEstimate_arrive_time() {
		return estimate_arrive_time;
	}

	public void setEstimate_arrive_time(Date estimate_arrive_time) {
		this.estimate_arrive_time = estimate_arrive_time;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public Integer getIs_delete() {
		return is_delete;
	}

	public void setIs_delete(Integer is_delete) {
		this.is_delete = is_delete;
	}
	public Date getArrive_time() {
		return arrive_time;
	}
	public void setArrive_time(Date arrive_time) {
		this.arrive_time = arrive_time;
	}


	public double getExpense_money() {
		return expense_money;
	}

	public void setExpense_money(double expense_money) {
		this.expense_money = expense_money;
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
	
	public String getCustomer_name() {
		return customer_name;
	}

	public void setCustomer_name(String customer_name) {
		this.customer_name = customer_name;
	}

	public String getCustomer_phone() {
		return customer_phone;
	}

	public void setCustomer_phone(String customer_phone) {
		this.customer_phone = customer_phone;
	}
	
	public Date getCreate_time() {
		return create_time;
	}

	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}

	public Date getUpdate_time() {
		return update_time;
	}

	public void setUpdate_time(Date update_time) {
		this.update_time = update_time;
	}
	
	public String getCreate_year() {
		return create_year;
	}

	public void setCreate_year(String create_year) {
		this.create_year = create_year;
	}

	public String getCreate_month() {
		return create_month;
	}

	public void setCreate_month(String create_month) {
		this.create_month = create_month;
	}

	public String getCreate_day() {
		return create_day;
	}

	public void setCreate_day(String create_day) {
		this.create_day = create_day;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}
	
	public Integer getIs_comment() {
		return is_comment;
	}

	public void setIs_comment(Integer is_comment) {
		this.is_comment = is_comment;
	}
	
	public String getUser_area_city_id() {
		return user_area_city_id;
	}

	public void setUser_area_city_id(String user_area_city_id) {
		this.user_area_city_id = user_area_city_id;
	}

	public String getUser_area_city_name() {
		return user_area_city_name;
	}

	public void setUser_area_city_name(String user_area_city_name) {
		this.user_area_city_name = user_area_city_name;
	}

		
	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	
	public String getCustomer_mail() {
		return customer_mail;
	}

	public void setCustomer_mail(String customer_mail) {
		this.customer_mail = customer_mail;
	}

	
	public Integer getUser_score() {
		return user_score;
	}

	public void setUser_score(Integer user_score) {
		this.user_score = user_score;
	}

	public String getLast_arrive_time() {
		return last_arrive_time;
	}

	public void setLast_arrive_time(String last_arrive_time) {
		this.last_arrive_time = last_arrive_time;
	}

	public String getCompany_area_city_id() {
		return company_area_city_id;
	}

	public void setCompany_area_city_id(String company_area_city_id) {
		this.company_area_city_id = company_area_city_id;
	}

	public String getCompany_area_city_name() {
		return company_area_city_name;
	}

	public void setCompany_area_city_name(String company_area_city_name) {
		this.company_area_city_name = company_area_city_name;
	}
	
	public String getCompany_area_province_id() {
		return company_area_province_id;
	}

	public void setCompany_area_province_id(String company_area_province_id) {
		this.company_area_province_id = company_area_province_id;
	}

	public String getCompany_area_province_name() {
		return company_area_province_name;
	}

	public void setCompany_area_province_name(String company_area_province_name) {
		this.company_area_province_name = company_area_province_name;
	}
	
	public String getUser_openid() {
		return user_openid;
	}

	public void setUser_openid(String user_openid) {
		this.user_openid = user_openid;
	}

	public String getCompany_openid() {
		return company_openid;
	}

	public void setCompany_openid(String company_openid) {
		this.company_openid = company_openid;
	}

	public Orders() {
		super();
	} 
	
}

