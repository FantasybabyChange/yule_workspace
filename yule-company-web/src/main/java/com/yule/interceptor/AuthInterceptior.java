package com.yule.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.yule.company.service.ICompanyUserService;
import com.yule.company.vo.CompanyUserVO;
import com.yule.constant.AuthConst;
import com.yule.constant.CompanyCookieConst;
import com.yule.constant.CompanyFlowConst;
import com.yule.constant.CompanyRedisConst;
import com.yule.constant.CompanySessionConst;
import com.yule.constant.CustomBeanConst;
import com.yule.constant.ServletPathConst;
import com.yule.constant.TimeConst;
import com.yule.redis.util.JedisUtil;
import com.yule.util.CookieUtil;
import com.yule.util.CustomBeanFactory;
import com.yule.util.YuLeEncryptUtil;

public class AuthInterceptior implements HandlerInterceptor {

	public AuthInterceptior() {	}

	public boolean preHandle(HttpServletRequest request,HttpServletResponse response, Object handler) throws Exception {
		String url = request.getServletPath();
		if(ServletPathConst.COMPANY_MAPPING_URLS.containsKey(url)){
			return true;
		}
		HttpSession session = request.getSession();
		String userId = YuLeEncryptUtil.decode(CookieUtil.getCookieValue(request.getCookies(), CompanyCookieConst.COMPANYUSER_COOKIE_NAME));
		Object value = session.getAttribute(CompanySessionConst.COMPANY_FLOW+userId);
		if (null==value) {
			String key = CompanyRedisConst.COMPANY_USER+userId;
			CompanyUserVO companyUserVO = null;
			if(!JedisUtil.getInstance().exists(key)){
				ICompanyUserService companyUserServiceImpl = (ICompanyUserService)CustomBeanFactory.getContext(CustomBeanConst.COMPANY_SERVICE_PATHS).getBean("companyUserServiceImpl");
				companyUserVO =  companyUserServiceImpl.findCompanyUserVOById(userId);
				if(null!=companyUserVO){
					JedisUtil.getInstance().set(key, JSONObject.fromObject(companyUserVO).toString(),TimeConst.ONE_HOUR);
				}
			} else{
				companyUserVO = (CompanyUserVO)JSONObject.toBean(JSONObject.fromObject(JedisUtil.getInstance().get(key)),CompanyUserVO.class);
			}	
			if(companyUserVO.getInfo_is_auth()==AuthConst.IS_AUTH_FALSE) {
				value = CompanyFlowConst.COMPANY_FLOW_ONE;
			}else if(companyUserVO.getAddress_is_auth()==AuthConst.IS_AUTH_FALSE){
				value = CompanyFlowConst.COMPANY_FLOW_TWO;
			}else{
				value = CompanyFlowConst.COMPANY_FLOW_THREE;
			}
			session.setAttribute(CompanySessionConst.COMPANY_FLOW+userId, value);
		}
		if(!value.equals(CompanyFlowConst.COMPANY_FLOW_THREE)){
			if(value.equals(CompanyFlowConst.COMPANY_FLOW_ONE)){
				//企业基本信息
				request.getRequestDispatcher("/init/findCompany.do").forward(request, response);
				return false;
			}else if(value.equals(CompanyFlowConst.COMPANY_FLOW_TWO)){
				//地理信息
				request.getRequestDispatcher("/init/findCompanyAddress.do").forward(request, response);
				return false;
			}
		}
		return true;
	}

	public void postHandle(HttpServletRequest request,HttpServletResponse response, Object handler,ModelAndView modelAndView) throws Exception {
		
	}

	public void afterCompletion(HttpServletRequest request,HttpServletResponse response, Object handler, Exception ex)throws Exception {
		
	}

}
