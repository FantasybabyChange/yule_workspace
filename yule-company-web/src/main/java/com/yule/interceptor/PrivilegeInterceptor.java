package com.yule.interceptor;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.druid.util.StringUtils;
import com.yule.constant.CodeConst;
import com.yule.constant.CompanyCookieConst;
import com.yule.constant.ServletPathConst;
import com.yule.exception.YuleException;
import com.yule.util.CompanyUtil;
import com.yule.util.CookieUtil;
import com.yule.util.PrivilegeUtil;
import com.yule.util.YuLeEncryptUtil;

public class PrivilegeInterceptor implements HandlerInterceptor {

	public PrivilegeInterceptor() { }  

	public boolean preHandle(HttpServletRequest request,HttpServletResponse response, Object handler) throws Exception {
		String url = request.getServletPath();//request.getRequestURL().toString();
		if(ServletPathConst.COMPANY_MAPPING_URLS.containsKey(url)){
			return true;
		}
		String value = YuLeEncryptUtil.decode(CookieUtil.getCookieValue(request.getCookies(), CompanyCookieConst.COMPANYUSER_COOKIE_NAME));
		if(!url.equals("/index.do")&&
			!url.equals("/companyUser/findCompanyUserPassword.do")&&
			!url.equals("/companyUser/updateCompanyUserPassword.do")){
			if(!PrivilegeUtil.getCompanyPrivilegeUrl(value, url)) {
				new YuleException("没有权限!");
				response.setContentType(CodeConst.TEXT_HTML);
				response.setCharacterEncoding(CodeConst.UTF_8);
				PrintWriter out = null;
				try{
					out = response.getWriter();
					out.println("没有权限!");
				} catch (Exception e){
					throw e;
				} finally{
					if(null!=out){
						out.flush();
						out.close();
					}
				}
				return false;
			}
		}
		String requestType = request.getHeader("X-Requested-With");
		if(StringUtils.isEmpty(requestType)){
			request.setAttribute("menu_html", CompanyUtil.getCompanyMenuHtml(YuLeEncryptUtil.decode(value)));
		}
		return true;
	}

	public void postHandle(HttpServletRequest request,HttpServletResponse response, Object handler,ModelAndView modelAndView) throws Exception {
		
	}

	public void afterCompletion(HttpServletRequest request,HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
	}

}
