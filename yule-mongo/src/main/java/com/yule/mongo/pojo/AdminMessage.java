package com.yule.mongo.pojo;

import java.io.Serializable;
import java.util.Date;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import com.yule.mongo.constant.CollectionConst;

/**
 * 后台消息
 */
@Document(collection=CollectionConst.ADMIN_MESSAGE)
public class AdminMessage implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1940084969931514520L;
	
	@Id
	private ObjectId id;
	@Indexed
	private String title;
	@Indexed
	private String content;
	@Indexed
	private String receive_id;
	@Indexed
	private String send_id;
	@Indexed
	private Integer is_read;
	@Indexed
	private Integer is_delete;
	@Indexed
	private Date create_time;

	public ObjectId getId() {
		return id;
	}

	public void setId(ObjectId id) {
		this.id = id;
	}
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	public String getReceive_id() {
		return receive_id;
	}

	public void setReceive_id(String receive_id) {
		this.receive_id = receive_id;
	}

	public String getSend_id() {
		return send_id;
	}

	public void setSend_id(String send_id) {
		this.send_id = send_id;
	}

	public Integer getIs_read() {
		return is_read;
	}

	public void setIs_read(Integer is_read) {
		this.is_read = is_read;
	}

	public Integer getIs_delete() {
		return is_delete;
	}

	public void setIs_delete(Integer is_delete) {
		this.is_delete = is_delete;
	}
	
	public Date getCreate_time() {
		return create_time;
	}

	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}

	public AdminMessage() {
		super();
	} 
	
}

