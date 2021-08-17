package com.yule.pojo;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * 产品服务设施
 */
public class ProductServe implements Serializable {
	private static final long serialVersionUID = 5137185450815166355L;
	private String id;
	//名称
	private String name;
	//排序
	private Integer order;
	//是筛选条件(0 是,1否) 默认1
	private Integer is_show;
	//是否删除(0未删除,1删除)
	private Integer is_delete;
	//创建时间
	private Timestamp create_time;
	//修改时间
	private Timestamp update_time;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getOrder() {
		return order;
	}
	public void setOrder(Integer order) {
		this.order = order;
	}
	public Integer getIs_show() {
		return is_show;
	}
	public void setIs_show(Integer is_show) {
		this.is_show = is_show;
	}
	public Integer getIs_delete() {
		return is_delete;
	}
	public void setIs_delete(Integer is_delete) {
		this.is_delete = is_delete;
	}
	public Timestamp getCreate_time() {
		return create_time;
	}
	public void setCreate_time(Timestamp create_time) {
		this.create_time = create_time;
	}
	public Timestamp getUpdate_time() {
		return update_time;
	}
	public void setUpdate_time(Timestamp update_time) {
		this.update_time = update_time;
	}
	public ProductServe() {
		super();
	}
}
