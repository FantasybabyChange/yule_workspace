package com.yule.common;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.yule.constant.TimeConst;
import com.yule.constant.UserCookieConst;
import com.yule.redis.constant.RedisConst;
import com.yule.redis.util.JedisUtil;
import com.yule.util.CookieUtil;
import com.yule.util.YuLeEncryptUtil;
import com.yule.vo.OrdersProductVO;
import com.yule.vo.UserLoginVO;

public class BaseAction {

	protected HttpServletRequest request;
	protected HttpServletResponse response;
	protected HttpSession session;
	
//	HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
//	HttpServletResponse response = ((ServletWebRequest)RequestContextHolder.getRequestAttributes()).getResponse();

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
	
	protected UserLoginVO getUserLoginVO() throws Exception {
		String id = getCookieValue();
		if(StringUtils.isEmpty(id)){
			return null;
		}
		String key = RedisConst.USER + YuLeEncryptUtil.decode(id);
		UserLoginVO userLoginVO  = null;
		if(JedisUtil.getInstance().exists(key)){
			userLoginVO = (UserLoginVO)JSONObject.toBean(JSONObject.fromObject(JedisUtil.getInstance().get(key)),UserLoginVO.class);
		}
		return userLoginVO;
	}
	
	protected void outputResult(String res) throws Exception {
		response.setContentType("text/html;charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = null;
		try {
			out = response.getWriter();
			out.print(res);
		} catch (IOException e) {
			throw e;
		} finally {
			if (out != null) {
				out.flush();
				out.close();
			}
		}
	}
	protected String getOrdersProduct(OrdersProductVO ordersProductVo) throws Exception{
		String uuId = null;
		if(!StringUtils.isEmpty(getCookieValue())){
		 uuId = ordersProductVo.getCompany_id()+"_"+YuLeEncryptUtil.decode(getCookieValue())+"_"+ordersProductVo.getProduct_id();
		String key = RedisConst.DETAIL_TO_ORDER + uuId ;
		if(null!=ordersProductVo){
			if(JedisUtil.getInstance().exists(key)){
				JedisUtil.getInstance().del(key);
			}
			JedisUtil.getInstance().set(key, JSONObject.fromObject(ordersProductVo).toString(),TimeConst.FIFTEEN_MINUTE);
		}
		}
		return uuId;
	}

}
