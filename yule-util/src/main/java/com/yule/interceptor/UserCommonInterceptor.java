package com.yule.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.yule.constant.CookieTimeConst;
import com.yule.constant.TimeConst;
import com.yule.constant.UserCookieConst;
import com.yule.util.CookieUtil;

public class UserCommonInterceptor implements HandlerInterceptor {

	public UserCommonInterceptor() { }  

	public boolean preHandle(HttpServletRequest request,HttpServletResponse response, Object handler) throws Exception {
		return true;
	}

	public void postHandle(HttpServletRequest request,HttpServletResponse response, Object handler,ModelAndView modelAndView) throws Exception {
		if(!StringUtils.isEmpty(CookieUtil.getCookieValue(request.getCookies(), UserCookieConst.USER_COOKIE_NAME))){
			String timeout = CookieUtil.getCookieValue(request.getCookies(), UserCookieConst.USER_TIMEOUT_COOKIE_NAME);
			if(!StringUtils.isEmpty(timeout)) {
				if((System.currentTimeMillis()-Long.valueOf(timeout))<TimeConst.HALF_HOUR) {
					response.addCookie(CookieUtil.addCookie(UserCookieConst.USER_TIMEOUT_COOKIE_NAME, String.valueOf(System.currentTimeMillis()), CookieTimeConst.HALF_HOUR));
				}
			}
		}
	}

	public void afterCompletion(HttpServletRequest request,HttpServletResponse response, Object handler, Exception ex) throws Exception {
		
	}

}
