package com.yule.mongo.pojo;

import java.io.Serializable;
import java.util.Date;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import com.yule.mongo.constant.CollectionConst;

/**
 * 用户积分
 */
@Document(collection=CollectionConst.USER_SCORE)
public class UserScore implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6920092003151663774L;
	
	@Id
	private ObjectId id;
	// 用户ID
	@Indexed
	private String user_id;
	// 积分
	private Integer score;
	// 积分类型
	@Indexed
	private Integer type;
	// 0获取 1 支出
	@Indexed
	private Integer status;
	@Indexed
	private Date create_time;
	
	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	
	public Integer getScore() {
		return score;
	}

	public void setScore(Integer score) {
		this.score = score;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
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
	
	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public UserScore() {
		super();
	}

	@Override
	public String toString() {
		return "UserScore [id=" + id + ", user_id=" + user_id + ", score="
				+ score + ", type=" + type + ", status=" + status
				+ ", create_time=" + create_time + "]";
	} 
	
}

