package com.yule.common;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.yule.constant.CodeConst;
import com.yule.constant.DoMainConst;
import com.yule.constant.UserCookieConst;
import com.yule.pojo.UserLogin;
import com.yule.redis.constant.RedisConst;
import com.yule.redis.util.JedisUtil;
import com.yule.util.BeanUtils;
import com.yule.util.CookieUtil;
import com.yule.util.YuLeEncryptUtil;
import com.yule.vo.UserLoginVO;

@Controller
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
	
	protected UserLoginVO getUserLoginVO() throws Exception {
		String id = getCookieValue();
		String key = RedisConst.USER + id;
		UserLoginVO userLoginVO  = null;
		if(!JedisUtil.getInstance().exists(key)){
			response.sendRedirect(DoMainConst.PC_URL);
		} else{
			userLoginVO = (UserLoginVO)JSONObject.toBean(JSONObject.fromObject(JedisUtil.getInstance().get(key)),UserLoginVO.class);
		}
		return userLoginVO;
	}
	
	protected void setUserLoginVO(UserLogin userLogin) throws Exception {
		String key = RedisConst.USER + getCookieValue();
		UserLoginVO userLoginVO = (UserLoginVO)JSONObject.toBean(JSONObject.fromObject(JedisUtil.getInstance().get(key)),UserLoginVO.class);
		BeanUtils.copyProperties(userLogin, userLoginVO);
		JedisUtil.getInstance().set(key, JSONObject.fromObject(userLoginVO).toString());
	}
	
	protected String getCookieValue() throws Exception {
		return YuLeEncryptUtil.decode(CookieUtil.getCookieValue(request.getCookies(), UserCookieConst.USER_COOKIE_NAME));
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