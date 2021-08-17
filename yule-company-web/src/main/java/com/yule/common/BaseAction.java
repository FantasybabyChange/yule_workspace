package com.yule.common;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.springframework.web.bind.annotation.ModelAttribute;

import com.yule.company.service.ICompanyUserService;
import com.yule.company.vo.CompanyUserVO;
import com.yule.constant.CodeConst;
import com.yule.constant.CompanyCookieConst;
import com.yule.constant.CompanyRedisConst;
import com.yule.constant.CustomBeanConst;
import com.yule.constant.TimeConst;
import com.yule.redis.util.JedisUtil;
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
	
	protected CompanyUserVO getCompanyUser() throws Exception {
		String key = CompanyRedisConst.COMPANY_USER + getCookieValue();
		CompanyUserVO companyUserVO  = null;
		if(!JedisUtil.getInstance().exists(key)){
			ICompanyUserService companyUserServiceImpl = (ICompanyUserService)CustomBeanFactory.getContext(CustomBeanConst.COMPANY_SERVICE_PATHS).getBean("companyUserServiceImpl");
			companyUserVO =  companyUserServiceImpl.findCompanyUserVOById(getCookieValue());
			if(null!=companyUserVO){
				JedisUtil.getInstance().set(key, JSONObject.fromObject(companyUserVO).toString(),TimeConst.ONE_HOUR);
			}
		} else{
			companyUserVO = (CompanyUserVO)JSONObject.toBean(JSONObject.fromObject(JedisUtil.getInstance().get(key)),CompanyUserVO.class);
		}
		return companyUserVO;
	}
	
	protected void setCompanyUser(CompanyUserVO companyUserVO) throws Exception{
		String key = CompanyRedisConst.COMPANY_USER + getCookieValue();
		if(null!=companyUserVO){
			if(JedisUtil.getInstance().exists(key)){
				JedisUtil.getInstance().del(key);
			}
			JedisUtil.getInstance().set(key, JSONObject.fromObject(companyUserVO).toString());
		}
	}
	
	protected String getCookieValue() throws Exception {
		return YuLeEncryptUtil.decode(CookieUtil.getCookieValue(request.getCookies(), CompanyCookieConst.COMPANYUSER_COOKIE_NAME));
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