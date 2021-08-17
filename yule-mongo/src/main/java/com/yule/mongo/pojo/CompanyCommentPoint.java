package com.yule.mongo.pojo;

import java.io.Serializable;
import java.util.Date;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import com.yule.mongo.constant.CollectionConst;

/**
 * 企业评分
 */
@Document(collection=CollectionConst.COMPANY_COMMENT_POINT)
public class CompanyCommentPoint implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1549866798513767838L;
	@Id
	private ObjectId id;
	// 订单编号
	@Indexed
	private String order_num;
	// 企业ID
	@Indexed
	private String company_id;
	// 用户ID
	@Indexed
	private String user_id;
	// 用户名
	private String user_name;
	// 企业评分分类名称
	@Indexed
	private String company_comment_category_id;
	// 企业评分分类名称
	private String company_comment_category_name;
	// 分数
	private float point;
	@Indexed
	private Date create_time;
	
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

	public String getCompany_id() {
		return company_id;
	}

	public void setCompany_id(String company_id) {
		this.company_id = company_id;
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
	public CompanyCommentPoint() {
		super();
	}

	public float getPoint() {
		return point;
	}

	public void setPoint(float point) {
		this.point = point;
	}

	public String getCompany_comment_category_id() {
		return company_comment_category_id;
	}

	public void setCompany_comment_category_id(String company_comment_category_id) {
		this.company_comment_category_id = company_comment_category_id;
	}

	public String getCompany_comment_category_name() {
		return company_comment_category_name;
	}

	public void setCompany_comment_category_name(
			String company_comment_category_name) {
		this.company_comment_category_name = company_comment_category_name;
	}
	public Date getCreate_time() {
		return create_time;
	}
	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}
}
