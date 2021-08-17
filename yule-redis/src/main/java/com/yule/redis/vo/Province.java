package com.yule.redis.vo;

public class Province {
	
	private String id;
	
	private String name;
	
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


	public Province(String id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public Province() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
