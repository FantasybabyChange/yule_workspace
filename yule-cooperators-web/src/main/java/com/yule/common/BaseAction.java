package com.yule.common;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.ModelAttribute;

import com.yule.constant.CodeConst;

public class BaseAction {

	protected HttpServletRequest request;
	protected HttpServletResponse response;
	protected HttpSession session;

//	protected HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();

//	protected HttpServletResponse response =  ((ServletWebRequest)RequestContextHolder.getRequestAttributes()).getResponse();
	@ModelAttribute
	public void setReqAndRes(HttpServletRequest request,
			HttpServletResponse response) {
		this.request = request;
		this.response = response;
		this.session = request.getSession();
	}
	protected void outputResult(String text) throws Exception{
		response.setContentType(CodeConst.TEXT_HTML);
		response.setCharacterEncoding(CodeConst.UTF_8);
		PrintWriter out = null;
		try{
			out = response.getWriter();
			out.println(text);
		} catch (Exception e){
			throw e;
		} finally{
			if(null!=out){
				out.flush();
				out.close();
			}
		}
	}

}