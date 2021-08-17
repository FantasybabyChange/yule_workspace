package com.yule.interceptor;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.yule.constant.AdminCookieConst;
import com.yule.constant.CodeConst;
import com.yule.constant.ServletPathConst;
import com.yule.exception.YuleException;
import com.yule.util.CookieUtil;
import com.yule.util.PrivilegeUtil;
import com.yule.util.YuLeEncryptUtil;

public class PrivilegeInterceptor implements HandlerInterceptor {

	public PrivilegeInterceptor() { }  

	public boolean preHandle(HttpServletRequest request,HttpServletResponse response, Object handler) throws Exception {
		String url = request.getServletPath();//request.getRequestURL().toString();
		if(ServletPathConst.ADMIN_MAPPING_URLS.containsKey(url)){
			return true;
		}
		String value = CookieUtil.getCookieValue(request.getCookies(), AdminCookieConst.ADMINUSER_COOKIE_NAME);
		if(!StringUtils.isEmpty(value)){
			String adminRoleId = JSONObject.fromObject(YuLeEncryptUtil.decode(value)).getString("admin_role_id");
			if(!PrivilegeUtil.getAdminPrivilegeUrl(adminRoleId).containsKey(url)) {
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
//				request.getRequestDispatcher("/index.do").forward(request, response);
				return false;
			}
			String requestType = request.getHeader("X-Requested-With");
			if(StringUtils.isEmpty(requestType)){
				request.setAttribute("privilegeId", PrivilegeUtil.getAdminPrivilegeUrl(adminRoleId).get(url));
			}
		}
		return true;
	}

	public void postHandle(HttpServletRequest request,HttpServletResponse response, Object handler,ModelAndView modelAndView) throws Exception {
		
	}

	public void afterCompletion(HttpServletRequest request,HttpServletResponse response, Object handler, Exception ex) throws Exception {
		
	}

}
