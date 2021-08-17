package com.yule.pojo;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * 企业地址
 */
public class CompanyAddress implements Serializable {

	private static final long serialVersionUID = 828810012002547057L;

	private String id;
	// 县区标识id
	private String area_county_id;
	// 热门商区id
	private String area_business_id;
	// 详细地址
	private String address_detail;
	// 经度
	private String lng;
	// 纬度
	private String lat;
	// 是否删除0删除1未删除
	private Integer is_delete;
	// 创建时间
	private Timestamp create_time;
	// 更新时间
	private Timestamp update_time;
	
	private Integer is_auth;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	public String getArea_county_id() {
		return area_county_id;
	}

	public void setArea_county_id(String area_county_id) {
		this.area_county_id = area_county_id;
	}
	
	public String getAddress_detail() {
		return address_detail;
	}

	public void setAddress_detail(String address_detail) {
		this.address_detail = address_detail;
	}

	public String getLng() {
		return lng;
	}

	public void setLng(String lng) {
		this.lng = lng;
	}

	public String getLat() {
		return lat;
	}

	public void setLat(String lat) {
		this.lat = lat;
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

	public String getArea_business_id() {
		return area_business_id;
	}

	public void setArea_business_id(String area_business_id) {
		this.area_business_id = area_business_id;
	}
	
	public Integer getIs_delete() {
		return is_delete;
	}

	public void setIs_delete(Integer is_delete) {
		this.is_delete = is_delete;
	}

	
	public Integer getIs_auth() {
		return is_auth;
	}

	public void setIs_auth(Integer is_auth) {
		this.is_auth = is_auth;
	}

	public CompanyAddress() {
		super();
	}

}
