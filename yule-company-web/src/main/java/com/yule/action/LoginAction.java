package com.yule.action;

import java.sql.Timestamp;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.yule.common.BaseAction;
import com.yule.company.query.CompanyLoginQuery;
import com.yule.company.service.ICompanyService;
import com.yule.company.service.ICompanyUserService;
import com.yule.company.vo.CompanyUserVO;
import com.yule.constant.ActionReturnConst;
import com.yule.constant.CaptchaSessionConst;
import com.yule.constant.CompanyCookieConst;
import com.yule.constant.CompanyErrorConst;
import com.yule.constant.CompanyRedisConst;
import com.yule.constant.CookieTimeConst;
import com.yule.constant.ErrorConst;
import com.yule.constant.StatusConst;
import com.yule.constant.TimeConst;
import com.yule.enumerate.DateStyle;
import com.yule.enumerate.LogEnum;
import com.yule.exception.YuleException;
import com.yule.pojo.CompanyUser;
import com.yule.redis.util.JedisUtil;
import com.yule.util.CompanyLogUtil;
import com.yule.util.CookieUtil;
import com.yule.util.DateUtil;
import com.yule.util.EncryptUtil;
import com.yule.util.YuLeEncryptUtil;

@Controller
@Scope("prototype")
public class LoginAction extends BaseAction {
	
	@Autowired
	private ICompanyUserService companyUserServiceImpl;
	
	@Autowired
	private ICompanyService companyServiceImpl;
	
	@RequestMapping(value = "/showLogin", method = RequestMethod.GET)
	public String showIndex() throws Exception {
		String value = CookieUtil.getCookieValue(request.getCookies(), CompanyCookieConst.COMPANYUSER_COOKIE_NAME);
		if(!StringUtils.isEmpty(value)){
			return ActionReturnConst.REDIRECT+"/index.do";
		}
		return "login";
	}
	
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout() throws Exception {
		String key = CompanyRedisConst.COMPANY_USER + getCookieValue();
		if(JedisUtil.getInstance().exists(key)){
			JedisUtil.getInstance().del(key);
		}
		CompanyLogUtil.insertLog("", getCompanyUser(), LogEnum.LOGOUT);
		response.addCookie(CookieUtil.addCookie(CompanyCookieConst.COMPANYUSER_COOKIE_NAME, null, CookieTimeConst.CLEAN_COOKIE));
		return ActionReturnConst.REDIRECT+"/showLogin.do";
	}
	
	/**
	 * 
	 * @param account
	 * @param password
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(CompanyLoginQuery companyLoginQuery) throws Exception {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("status", ErrorConst.STATUS_ERROR);
		try {
			if(StringUtils.isEmpty(companyLoginQuery.getAccount())){
				jsonObject.put("errorMessage", CompanyErrorConst.COMPANY_USER_3);
				return "login";
			}
			if (StringUtils.isEmpty(companyLoginQuery.getPassword())) {
				jsonObject.put("errorMessage", ErrorConst.PASSWORD_1);
				return "login";
			}
			String captcha = companyLoginQuery.getCaptcha();
			if (StringUtils.isEmpty(captcha)) {
				jsonObject.put("errorMessage", ErrorConst.CAPTCHA_0);
				return "login";
			}
			CompanyUserVO companyUserVO = companyUserServiceImpl.findCompanyUserVOByAccount(companyLoginQuery.getAccount(),companyLoginQuery.getCode());
			if(null==companyUserVO){
				jsonObject.put("errorMessage", CompanyErrorConst.COMPANY_USER_0);
				return "login";
			}
			if (!companyUserVO.getPassword().equals(EncryptUtil.encryptToMD5(companyLoginQuery.getPassword()))) {
				jsonObject.put("errorMessage", ErrorConst.PASSWORD_0);
				return "login";
			}
			if(StatusConst.STATUS_FALSE==companyUserVO.getStatus()){
				jsonObject.put("errorMessage", CompanyErrorConst.COMPANY_USER_1);
				return "login";
			}
			Object code = session.getAttribute(CaptchaSessionConst.COMPANY_CAPTCHA_KEY);
			if(null!=code&&!StringUtils.isEmpty(code.toString())){
				if (!code.toString().equals(EncryptUtil.encryptToMD5(captcha.toLowerCase()))) {
					jsonObject.put("errorMessage", ErrorConst.CAPTCHA_1);
					return "login";
				}
			}else{
				jsonObject.put("errorMessage", ErrorConst.CAPTCHA_1);
				return "login";
			}
			String id = companyUserVO.getId().toString();
			Timestamp loginTime = DateUtil.getCurrentTimestamp();
			CompanyUser companyUser = new CompanyUser();
			companyUser.setId(id);
			companyUser.setLogin_time(loginTime);
			this.companyUserServiceImpl.updateCompanyUser(companyUser);
			companyUserVO.setLogin_time(DateUtil.DateToString(loginTime, DateStyle.YYYY_MM_DD_HH_MM_SS_EN));
			response.addCookie(CookieUtil.addCookie(CompanyCookieConst.COMPANYUSER_COOKIE_NAME, YuLeEncryptUtil.encode(id), CookieTimeConst.DEFAULT_COOKIE));
			String key = CompanyRedisConst.COMPANY_USER+id;
			if(null!=companyUser){
				JedisUtil.getInstance().set(key, JSONObject.fromObject(companyUserVO).toString(),TimeConst.ONE_HOUR);
			}
			jsonObject.put("status", ErrorConst.STATUS_SUCCESS);
			CompanyLogUtil.insertLog("",companyUserVO , LogEnum.LOGIN);
		} catch (Exception e) {
			new YuleException(e);
			throw e;
		} finally{
			request.setAttribute("errorMessage", jsonObject.toString());
		}
		return ActionReturnConst.REDIRECT+"/showLogin.do";
	}
	
	/**
	 * 验证登录
	 */
	@RequestMapping(value = "/verifyLogin", method = RequestMethod.POST)
	public String verifyLogin(CompanyLoginQuery companyLoginQuery) throws Exception{
		JSONObject obj = new JSONObject();
		obj.put("status", ErrorConst.STATUS_ERROR);
		try{
			if(StringUtils.isEmpty(companyLoginQuery.getAccount())){
				obj.put("message", CompanyErrorConst.COMPANY_USER_3);
				return null;
			}
			if (StringUtils.isEmpty(companyLoginQuery.getPassword())) {
				obj.put("message", ErrorConst.PASSWORD_1);
				return null;
			}
			String captcha = companyLoginQuery.getCaptcha();
			if (StringUtils.isEmpty(captcha)) {
				obj.put("errorMessage", ErrorConst.CAPTCHA_0);
				return null;
			}
			CompanyUserVO companyUserVO = this.companyUserServiceImpl.findCompanyUserVOByAccount(companyLoginQuery.getAccount(),companyLoginQuery.getCode());
			if(null==companyUserVO){
				obj.put("message", CompanyErrorConst.COMPANY_USER_0);
				return null;
			}
			if (!companyUserVO.getPassword().equals(EncryptUtil.encryptToMD5(companyLoginQuery.getPassword()))) {
				obj.put("message", ErrorConst.PASSWORD_0);
				
				return null;
			}
			if(StatusConst.STATUS_FALSE==companyUserVO.getStatus()){
				obj.put("message", CompanyErrorConst.COMPANY_USER_1);
				return null;
			}
			Object code = session.getAttribute(CaptchaSessionConst.COMPANY_CAPTCHA_KEY);
			if(null!=code&&!StringUtils.isEmpty(code.toString())){
				if (!code.toString().equals(EncryptUtil.encryptToMD5(captcha.toLowerCase()))) {
					obj.put("message", ErrorConst.CAPTCHA_1);
					return null;
				}
			}else{
				obj.put("message", ErrorConst.CAPTCHA_1);
				return null;
			}
			obj.put("status", ErrorConst.STATUS_SUCCESS);
		} catch(Exception e){
			new YuleException("ajax验证企业登录发生错误!",e);
			throw e;
		} finally{
			outputResult(obj.toString());
		}
		return null;
	}
	
}
