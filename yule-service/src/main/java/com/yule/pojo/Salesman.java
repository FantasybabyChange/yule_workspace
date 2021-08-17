package com.yule.pojo;

import java.io.Serializable;
import java.sql.Timestamp;

public class Salesman implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5368858709874346990L;
	public Salesman() {
		super();
	}
	private String id;
	private String name;
	private Integer sex;
	private Integer age;
	private String address;
	private String mail;
	private String phone;
	private Integer commision;
	private String image_path;
	private String identity_card;
	private String identity_card_image_path;
	private Integer area_city_id;
	private Integer is_delete;
	private Timestamp create_time;
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
	public Integer getSex() {
		return sex;
	}
	public void setSex(Integer sex) {
		this.sex = sex;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	public String getAddress() {
		return address;
	}
	public Integer getCommision() {
		return commision;
	}
	public void setCommision(Integer commision) {
		this.commision = commision;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getImage_path() {
		return image_path;
	}
	public void setImage_path(String image_path) {
		this.image_path = image_path;
	}
	public String getIdentity_card() {
		return identity_card;
	}
	public void setIdentity_card(String identity_card) {
		this.identity_card = identity_card;
	}
	public String getIdentity_card_image_path() {
		return identity_card_image_path;
	}
	public void setIdentity_card_image_path(String identity_card_image_path) {
		this.identity_card_image_path = identity_card_image_path;
	}
	public Integer getArea_city_id() {
		return area_city_id;
	}
	public void setArea_city_id(Integer area_city_id) {
		this.area_city_id = area_city_id;
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
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public Timestamp getUpdate_time() {
		return update_time;
	}
	public void setUpdate_time(Timestamp update_time) {
		this.update_time = update_time;
	}
	
}
