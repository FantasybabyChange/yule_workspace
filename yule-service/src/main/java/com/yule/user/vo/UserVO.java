package com.yule.user.vo;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * 前台用户信息
 */
public class UserVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1152228809204570862L;
	// 用户ID
	private String id;
	// 用户等级
	private String user_level_name;
	// 省ID
	private String area_province_id;
	// 省名称
	private String area_province_name;
	// 性别
	private Integer sex;
	// 生日
	private Timestamp birthday;
	// 市ID
	private String area_city_id;
	// 市名称
	private String area_city_name;
	// 用户地址
	private String address;
	// 用户头像
	private String image_path;
	
	public UserVO() {
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

	public String getArea_province_id() {
		return area_province_id;
	}

	public void setArea_province_id(String area_province_id) {
		this.area_province_id = area_province_id;
	}

	public String getArea_province_name() {
		return area_province_name;
	}

	public void setArea_province_name(String area_province_name) {
		this.area_province_name = area_province_name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	
	public String getArea_city_name() {
		return area_city_name;
	}

	public void setArea_city_name(String area_city_name) {
		this.area_city_name = area_city_name;
	}
	
	public String getUser_level_name() {
		return user_level_name;
	}

	public void setUser_level_name(String user_level_name) {
		this.user_level_name = user_level_name;
	}

	public String getImage_path() {
		return image_path;
	}

	public void setImage_path(String image_path) {
		this.image_path = image_path;
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
	
}
