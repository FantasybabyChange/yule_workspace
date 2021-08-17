package com.yule.mail.salesman;

import java.io.Serializable;

import com.yule.util.MailUtil;

public class SalesmanUpdatePasswordMail extends MailUtil implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4063944703074851079L;

	public SalesmanUpdatePasswordMail() throws Exception {
		super();
	}

	private String code;
	//企业账号
	private String account;
	
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
}
