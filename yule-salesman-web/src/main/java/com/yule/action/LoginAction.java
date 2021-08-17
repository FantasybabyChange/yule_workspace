package com.yule.action;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.yule.common.BaseAction;
import com.yule.constant.ActionReturnConst;
import com.yule.constant.CaptchaSessionConst;
import com.yule.constant.CookieTimeConst;
import com.yule.constant.ErrorConst;
import com.yule.constant.SalesmanCookieConst;
import com.yule.constant.SalesmanErrorConst;
import com.yule.constant.StatusConst;
import com.yule.exception.YuleException;
import com.yule.pojo.SalesmanLogin;
import com.yule.salesman.service.ISalesmanLoginService;
import com.yule.util.CookieUtil;
import com.yule.util.DateUtil;
import com.yule.util.EncryptUtil;
import com.yule.util.YuLeEncryptUtil;

@Controller
@Scope("prototype")
public class LoginAction extends BaseAction {
	
	@Autowired
	private ISalesmanLoginService salesmanLoginServiceImpl;
	
	@RequestMapping(value = "/showLogin", method = RequestMethod.GET)
	public String showIndex() throws Exception {
		String value = CookieUtil.getCookieValue(request.getCookies(), SalesmanCookieConst.SALESMAN_COOKIE_NAME);
		if(!StringUtils.isEmpty(value)){
			return ActionReturnConst.REDIRECT+"/index.do";
		}
		return "login";
	}
	
	/**
	 * 退出
	 */
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout() throws Exception {
		response.addCookie(CookieUtil.addCookie(SalesmanCookieConst.SALESMAN_COOKIE_NAME, null, CookieTimeConst.CLEAN_COOKIE));
		return ActionReturnConst.REDIRECT+"/showLogin.do";
	}
	
	/**
	 * 登录
	 */
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(@RequestParam(value="account",required=false)String account,
			@RequestParam(value="password",required=false) String password,
				@RequestParam(value="captcha",required=false) String captcha) throws Exception {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("status", ErrorConst.STATUS_ERROR);
		try {
			if(StringUtils.isEmpty(account)){
				jsonObject.put("errorMessage", SalesmanErrorConst.SALESMAN_3);
				return "login";
			}
			if (StringUtils.isEmpty(password)) {
				jsonObject.put("errorMessage", ErrorConst.PASSWORD_1);
				return "login";
			}
			if (StringUtils.isEmpty(captcha)) {
				jsonObject.put("errorMessage", ErrorConst.CAPTCHA_0);
				return "login";
			}
			SalesmanLogin salesmanLogin = this.salesmanLoginServiceImpl.findSalesmanLoginByAccount(account);
			if(null==salesmanLogin){
				jsonObject.put("errorMessage", SalesmanErrorConst.SALESMAN_0);
				return "login";
			}
			if (!salesmanLogin.getPassword().equals(EncryptUtil.encryptToMD5(password))) {
				jsonObject.put("errorMessage", ErrorConst.PASSWORD_0);
				return "login";
			}
			Object code = session.getAttribute(CaptchaSessionConst.SALESMAN_CAPTCHA_KEY);
			if(null!=code&&!StringUtils.isEmpty(code.toString())){
				if (!code.toString().equals(EncryptUtil.encryptToMD5(captcha.toLowerCase()))) {
					jsonObject.put("errorMessage", ErrorConst.CAPTCHA_1);
					return "login";
				}
			}else{
				jsonObject.put("errorMessage", ErrorConst.CAPTCHA_1);
				return "login";
			}
			String id = salesmanLogin.getId();
			SalesmanLogin salesMan = new SalesmanLogin();
			salesMan.setId(id);
			salesMan.setLogin_time(DateUtil.getCurrentTimestamp());
			this.salesmanLoginServiceImpl.updateSalesmanLogin(salesMan);
			response.addCookie(CookieUtil.addCookie(SalesmanCookieConst.SALESMAN_COOKIE_NAME, YuLeEncryptUtil.encode(id), CookieTimeConst.DEFAULT_COOKIE));
		} catch (Exception e) {
			new YuleException(e);
			throw e;
		} 
		return ActionReturnConst.REDIRECT+"/showLogin.do";
	}
	
	/**
	 * 异步验证登录
	 */
	@RequestMapping(value = "/verifyLogin", method = RequestMethod.POST)
	public String verifyLogin(@RequestParam(value="account",required=false)String account,@RequestParam(value="password",required=false) String password,@RequestParam(value="captcha",required=false) String captcha) throws Exception{
		JSONObject obj = new JSONObject();
		obj.put("status", ErrorConst.STATUS_ERROR);
		try{
			if(StringUtils.isEmpty(account)){
				obj.put("message", SalesmanErrorConst.SALESMAN_3);
				return null;
			}
			if (StringUtils.isEmpty(password)) {
				obj.put("message", ErrorConst.PASSWORD_1);
				return null;
			}
			if (StringUtils.isEmpty(captcha)) {
				obj.put("message", ErrorConst.CAPTCHA_0);
				return null;
			}
			SalesmanLogin salesmanLogin = this.salesmanLoginServiceImpl.findSalesmanLoginByAccount(account);
			if(null==salesmanLogin){
				obj.put("message", SalesmanErrorConst.SALESMAN_0);
				return null;
			}
			if (!salesmanLogin.getPassword().equals(EncryptUtil.encryptToMD5(password))) {
				obj.put("message", ErrorConst.PASSWORD_0);
				return null;
			}
			
			if(StatusConst.STATUS_FALSE == salesmanLogin.getStatus()){
				obj.put("message", SalesmanErrorConst.SALESMAN_1);
				return null;
			}
			Object code = session.getAttribute(CaptchaSessionConst.SALESMAN_CAPTCHA_KEY);
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
			new YuleException(e);
			throw e;
		} finally{
			outputResult(obj.toString());
		}
		return null;
	}
	
}
