package com.yule.pojo;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * 前台用户信息
 */
public class User implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6765981879231516231L;
	// 用户ID
	private String id;
	// 用户等级
	private String user_level_id;
	// 市ID
	private String area_city_id;
	// 性别
	private Integer sex;
	// 生日
	private Timestamp birthday;
	// 用户地址
	private String address;
	// 是否删除  0 删除 1 未删除
	private Integer is_delete;
	//用户头像
	private String image_path;
	// 创建时间
	private Timestamp create_time;
	// 修改时间
	private Timestamp update_time;
	
	public User() {
		super();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getArea_city_id() {
		return area_city_id;
	}

	public void setArea_city_id(String area_city_id) {
		this.area_city_id = area_city_id;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
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
	
	public Integer getSex() {
		return sex;
	}

	public void setSex(Integer sex) {
		this.sex = sex;
	}

	public Timestamp getBirthday() {
		return birthday;
	}

	public void setBirthday(Timestamp birthday) {
		this.birthday = birthday;
	}

	public String getUser_level_id() {
		return user_level_id;
	}

	public void setUser_level_id(String user_level_id) {
		this.user_level_id = user_level_id;
	}

	public String getImage_path() {
		return image_path;
	}

	public void setImage_path(String image_path) {
		this.image_path = image_path;
	}
	
}
