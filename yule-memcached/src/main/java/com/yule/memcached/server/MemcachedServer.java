package com.yule.memcached.server;

import java.io.Serializable;

public class MemcachedServer implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2403715153825608649L;

	private String address;
	private int port;
	private int weight;

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}

	public MemcachedServer(String address, int port, int weight) {
		super();
		this.address = address;
		this.port = port;
		this.weight = weight;
	}

	public MemcachedServer() {
		super();
	}
	
	

}
