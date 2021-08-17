package com.yule.mongo.pojo;

import java.io.Serializable;
import java.util.Date;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import com.yule.mongo.constant.CollectionConst;

/**
 * 企业评论
 */
@Document(collection=CollectionConst.COMPANY_COMMENT)
public class CompanyComment implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6305875245002062739L;
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
	// 标题
	@Indexed
	private String title;
	//类型
	@Indexed
	private Integer type;
	// 评论
	private String comment;
	// 建议
	private String advice;
	// 综合得分
	@Indexed
	private float point;
	// 创建时间
	@Indexed
	private Date create_time;
	
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

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	public Date getCreate_time() {
		return create_time;
	}
	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}
	public Float getPoint() {
		return point;
	}
	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getAdvice() {
		return advice;
	}

	public void setAdvice(String advice) {
		this.advice = advice;
	}
	
	public String getOrder_num() {
		return order_num;
	}
	public void setOrder_num(String order_num) {
		this.order_num = order_num;
	}
	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public void setPoint(float point) {
		this.point = point;
	}

	public CompanyComment() {
		super();
	} 
}

