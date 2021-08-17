package com.yule.vo;

import java.io.Serializable;

public class FileImageSource implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String originalfilepath;
	private String smallfilepath;
	private String mediumfilepath;
	private String bigfilepath;
	private String identitycardfilepath;
	public String getOriginalfilepath() {
		return originalfilepath;
	}

	public void setOriginalfilepath(String originalfilepath) {
		this.originalfilepath = originalfilepath;
	}

	public String getSmallfilepath() {
		return smallfilepath;
	}

	public void setSmallfilepath(String smallfilepath) {
		this.smallfilepath = smallfilepath;
	}

	public String getMediumfilepath() {
		return mediumfilepath;
	}

	public void setMediumfilepath(String mediumfilepath) {
		this.mediumfilepath = mediumfilepath;
	}

	public String getBigfilepath() {
		return bigfilepath;
	}

	public void setBigfilepath(String bigfilepath) {
		this.bigfilepath = bigfilepath;
	}

	public String getIdentitycardfilepath() {
		return identitycardfilepath;
	}

	public void setIdentitycardfilepath(String identitycardfilepath) {
		this.identitycardfilepath = identitycardfilepath;
	}
	

}
