package com.yule.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.yule.constant.CompanyCookieConst;
import com.yule.constant.ServletPathConst;
import com.yule.exception.YuleException;
import com.yule.util.CookieUtil;

public class LoginInterceptor implements HandlerInterceptor {

	public LoginInterceptor() { }  


	public boolean preHandle(HttpServletRequest request,HttpServletResponse response, Object handler) throws Exception {
		String url = request.getServletPath();
		if(ServletPathConst.COMPANY_MAPPING_URLS.containsKey(url)){
			return true;
		}
		String value = CookieUtil.getCookieValue(request.getCookies(), CompanyCookieConst.COMPANYUSER_COOKIE_NAME);
		if (StringUtils.isEmpty(value)) {
			new YuleException("企业未登录");
			request.getRequestDispatcher("/showLogin.do").forward(request, response);
			return false;
		}
		if(url.equals("/index.do")){
			return true;
		}
		return true;
	}
	
	public void postHandle(HttpServletRequest request,HttpServletResponse response, Object handler,ModelAndView modelAndView) throws Exception {
		
	}

	public void afterCompletion(HttpServletRequest request,HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		
	}


	
	
}
