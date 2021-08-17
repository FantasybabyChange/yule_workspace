package com.yule.admin.vo;

public class UserVO {
	
	//用户id
	private String id;
	//用户真实姓名
	private String name;
	// 用户等级id
	private String user_level_id;
	// 用户名称
	private String user_level_name;
	//用户头像
	private String image_path;
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUser_level_id() {
		return user_level_id;
	}

	public void setUser_level_id(String user_level_id) {
		this.user_level_id = user_level_id;
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

	public UserVO() {
		super();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
