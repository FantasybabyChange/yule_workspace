package com.yule.common;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.yule.constant.CodeConst;
import com.yule.constant.CookieTimeConst;
import com.yule.constant.UserCookieConst;
import com.yule.util.CookieUtil;
import com.yule.util.IPAddressUtil;
import com.yule.util.YuLeEncryptUtil;

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

	protected String getCookieValue() throws Exception {
		return YuLeEncryptUtil.decode(CookieUtil.getCookieValue(request.getCookies(), UserCookieConst.USER_COOKIE_NAME));
	}
	
	protected String getAreaCookieValue() throws Exception {
		String areaId = getCookieValue(UserCookieConst.USER_AREA_COOKIE_NAME);
		if(StringUtils.isEmpty(areaId)){
			String ip = IPAddressUtil.getRemortIP(request);
			if(ip!=null){
				areaId = IPAddressUtil.getAddresses(ip).getCity_id();
			}else{
				areaId = "610100";
			}
			response.addCookie(CookieUtil.addCookie(UserCookieConst.USER_AREA_COOKIE_NAME, areaId, CookieTimeConst.HALF_HOUR));
		}
		return areaId;
	}
	
	protected String getCookieValue(String key) throws Exception {
		return CookieUtil.getCookieValue(request.getCookies(), key);
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