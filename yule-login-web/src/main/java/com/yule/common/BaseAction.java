package com.yule.common;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.springframework.web.bind.annotation.ModelAttribute;

import com.yule.constant.CodeConst;
import com.yule.constant.TimeConst;
import com.yule.constant.UserCookieConst;
import com.yule.redis.constant.RedisConst;
import com.yule.redis.util.JedisUtil;
import com.yule.util.CookieUtil;
import com.yule.vo.UserLoginVO;

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
		return CookieUtil.getCookieValue(request.getCookies(), UserCookieConst.USER_COOKIE_NAME);
	}
	
	protected void setUserLoginVO(UserLoginVO userLoginVO) throws Exception {
		String key = RedisConst.USER + userLoginVO.getId();
		if(null!=userLoginVO){
			if(JedisUtil.getInstance().exists(key)){
				JedisUtil.getInstance().del(key);
			}
			JedisUtil.getInstance().set(key, JSONObject.fromObject(userLoginVO).toString(),TimeConst.ONE_HOUR*4);
		}
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