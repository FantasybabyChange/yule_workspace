package com.yule.mongo.pojo;

import java.io.Serializable;
import java.util.Date;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import com.yule.mongo.constant.CollectionConst;
/**
 * 企业收藏记录
 */
@Document(collection=CollectionConst.USER_FAVORITES)
public class UserFavorite implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2259218810991780710L;

	@Id
	private ObjectId id;
	@Indexed
	private String user_id;
	
	private String user_name;
	//企业id
	@Indexed
	private String company_id;
	//企业名称
	private String company_name;
	
	private String company_category_id;
	
	private String company_category_name;
	
	private String company_grade_id;
	
	private String company_grade_name;
	
	private String address_datail;
	
	private String area_county_name;
	
	private String area_city_name;
	//创建时间
	@Indexed
	private Date create_time;
	
	
	public UserFavorite() {
		super();
	}
	
	public ObjectId getId() {
		return id;
	}

	public void setId(ObjectId id) {
		this.id = id;
	}

	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getCompany_id() {
		return company_id;
	}
	public void setCompany_id(String company_id) {
		this.company_id = company_id;
	}
	public String getCompany_name() {
		return company_name;
	}
	public void setCompany_name(String company_name) {
		this.company_name = company_name;
	}
	public String getAddress_datail() {
		return address_datail;
	}
	public void setAddress_datail(String address_datail) {
		this.address_datail = address_datail;
	}
	public String getArea_county_name() {
		return area_county_name;
	}
	public void setArea_county_name(String area_county_name) {
		this.area_county_name = area_county_name;
	}
	public String getArea_city_name() {
		return area_city_name;
	}
	public void setArea_city_name(String area_city_name) {
		this.area_city_name = area_city_name;
	}
	public Date getCreate_time() {
		return create_time;
	}
	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public String getCompany_category_id() {
		return company_category_id;
	}

	public void setCompany_category_id(String company_category_id) {
		this.company_category_id = company_category_id;
	}

	public String getCompany_category_name() {
		return company_category_name;
	}

	public void setCompany_category_name(String company_category_name) {
		this.company_category_name = company_category_name;
	}

	public String getCompany_grade_id() {
		return company_grade_id;
	}

	public void setCompany_grade_id(String company_grade_id) {
		this.company_grade_id = company_grade_id;
	}

	public String getCompany_grade_name() {
		return company_grade_name;
	}

	public void setCompany_grade_name(String company_grade_name) {
		this.company_grade_name = company_grade_name;
	}
	
}
