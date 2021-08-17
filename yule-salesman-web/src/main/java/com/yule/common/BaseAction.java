package com.yule.common;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.springframework.web.bind.annotation.ModelAttribute;

import com.yule.constant.CodeConst;
import com.yule.constant.CustomBeanConst;
import com.yule.constant.SalesmanCookieConst;
import com.yule.constant.SalesmanRedisConst;
import com.yule.constant.TimeConst;
import com.yule.redis.util.JedisUtil;
import com.yule.salesman.service.ISalesmanLoginService;
import com.yule.salesman.vo.SalesmanVO;
import com.yule.util.CookieUtil;
import com.yule.util.CustomBeanFactory;
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
	
	protected SalesmanVO getSalesman() throws Exception {
		String key = SalesmanRedisConst.SALESMAN_USER + getCookieValue();
		SalesmanVO salesmanVO  = null;
		if(!JedisUtil.getInstance().exists(key)){
			ISalesmanLoginService salesmanLoginServiceImpl = (ISalesmanLoginService)CustomBeanFactory.getContext(CustomBeanConst.SALESMAN_SERVICE_PATHS).getBean("salesmanLoginServiceImpl");
			salesmanVO =  salesmanLoginServiceImpl.findSalesmanLoginById(getCookieValue());
			if(null!=salesmanVO){
				JedisUtil.getInstance().set(key, JSONObject.fromObject(salesmanVO).toString(),TimeConst.ONE_HOUR);
			}
		} else{
			salesmanVO = (SalesmanVO)JSONObject.toBean(JSONObject.fromObject(JedisUtil.getInstance().get(key)),SalesmanVO.class); ;
		}
		return salesmanVO;
	}
	
	protected void setSalesman(SalesmanVO salesmanVO) throws Exception{
		String key = SalesmanRedisConst.SALESMAN_USER + getCookieValue();
		if(null!=salesmanVO){
			if(JedisUtil.getInstance().exists(key)){
				JedisUtil.getInstance().del(key);
			}
			JedisUtil.getInstance().set(key, JSONObject.fromObject(salesmanVO).toString());
		}
	}
	
	protected String getCookieValue() throws Exception {
		return YuLeEncryptUtil.decode(CookieUtil.getCookieValue(request.getCookies(), SalesmanCookieConst.SALESMAN_COOKIE_NAME));
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