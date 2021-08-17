package com.yule.mail.company;

import java.io.Serializable;

import com.yule.util.MailUtil;

public class CompanyUpdatePasswordMail extends MailUtil implements Serializable {

	public CompanyUpdatePasswordMail() throws Exception {
		super();
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 4575023878583650768L;
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
