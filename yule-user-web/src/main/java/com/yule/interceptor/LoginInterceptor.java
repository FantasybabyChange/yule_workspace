package com.yule.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.yule.constant.DoMainConst;
import com.yule.constant.UserCookieConst;
import com.yule.util.CookieUtil;

public class LoginInterceptor implements HandlerInterceptor {

	public LoginInterceptor() { }  

	public boolean preHandle(HttpServletRequest request,HttpServletResponse response, Object handler) throws Exception {
		String value = CookieUtil.getCookieValue(request.getCookies(), UserCookieConst.USER_COOKIE_NAME);
		if (StringUtils.isEmpty(value)) {
			response.sendRedirect(DoMainConst.PC_URL);
			return false;
		}
		if(StringUtils.isEmpty(CookieUtil.getCookieValue(request.getCookies(), UserCookieConst.USER_TIMEOUT_COOKIE_NAME))){
			response.sendRedirect(DoMainConst.LOGIN_URL+"/login.jsp?backurl="+request.getRemoteHost()+request.getServletPath());
			return false;
		}
		return true;
	}

	public void postHandle(HttpServletRequest request,HttpServletResponse response, Object handler,ModelAndView modelAndView) throws Exception {
		
	}

	public void afterCompletion(HttpServletRequest request,HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
	}

}
