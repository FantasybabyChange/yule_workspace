package com.yule.mongo.pojo;

import java.io.Serializable;
import java.util.Date;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import com.yule.mongo.constant.CollectionConst;

/**
 * 定时任务
 */
@Document(collection=CollectionConst.TIMERJOB)
public class TimerJob implements Serializable {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1008782076386765998L;
	
	@Id
	private ObjectId id;
	//任务名称
	private String name;
	//任务所属组的名称
	private String group;
	//定时任务运行时间表达式
	private String cronExpression;
	//任务描述
	private String details;
	//同步异步
	private Integer async;
	//执行类名
	private String className;
	//任务状态
	@Indexed
	private Integer status;
	//执行时间
	private Date execute_time;
	//执行次数
	private Integer execute_num = 0;
	//错误信息
	private String error_message;
	@Indexed
	private Date create_time;
	
	private Date update_time;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public ObjectId getId() {
		return id;
	}

	public void setId(ObjectId id) {
		this.id = id;
	}
	
	public Date getExecute_time() {
		return execute_time;
	}

	public void setExecute_time(Date execute_time) {
		this.execute_time = execute_time;
	}
	
	public Integer getExecute_num() {
		return execute_num;
	}

	public void setExecute_num(Integer execute_num) {
		this.execute_num = execute_num;
	}
	
	public String getError_message() {
		return error_message;
	}

	public void setError_message(String error_message) {
		this.error_message = error_message;
	}
	
	public String getGroup() {
		return group;
	}

	public void setGroup(String group) {
		this.group = group;
	}

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}

	public Integer getAsync() {
		return async;
	}

	public void setAsync(Integer async) {
		this.async = async;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
	
	public String getCronExpression() {
		return cronExpression;
	}

	public void setCronExpression(String cronExpression) {
		this.cronExpression = cronExpression;
	}
	
	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}
	
	public TimerJob() {
		super();
	} 
	
}

