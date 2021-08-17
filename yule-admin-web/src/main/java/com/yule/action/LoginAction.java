package com.yule.action;

import java.sql.Timestamp;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.yule.admin.service.IAdminUserService;
import com.yule.admin.vo.AdminUserVO;
import com.yule.common.BaseAction;
import com.yule.constant.ActionReturnConst;
import com.yule.constant.AdminCookieConst;
import com.yule.constant.AdminErrorConst;
import com.yule.constant.CaptchaSessionConst;
import com.yule.constant.CookieTimeConst;
import com.yule.constant.ErrorConst;
import com.yule.constant.StatusConst;
import com.yule.enumerate.DateStyle;
import com.yule.enumerate.LogEnum;
import com.yule.exception.YuleException;
import com.yule.pojo.AdminUser;
import com.yule.util.AdminLogUtil;
import com.yule.util.CookieUtil;
import com.yule.util.DateUtil;
import com.yule.util.EncryptUtil;
import com.yule.util.YuLeEncryptUtil;

@Controller
@Scope("prototype")
public class LoginAction extends BaseAction {
	
	@Autowired
	private IAdminUserService adminUserServiceImpl;
	
	@RequestMapping(value = "/showLogin", method = RequestMethod.GET)
	public String showIndex() throws Exception {
		String value = CookieUtil.getCookieValue(request.getCookies(), AdminCookieConst.ADMINUSER_COOKIE_NAME);
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
		response.addCookie(CookieUtil.addCookie(AdminCookieConst.ADMINUSER_COOKIE_NAME, null, CookieTimeConst.CLEAN_COOKIE));
		AdminLogUtil.insertLog("用户退出", getAdminUser(), LogEnum.LOGOUT);
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
				jsonObject.put("errorMessage", AdminErrorConst.ADMIN_USER_3);
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
			AdminUserVO adminUserVO = this.adminUserServiceImpl.findAdminUserVOByAccount(account);
			if(null==adminUserVO){
				jsonObject.put("errorMessage", AdminErrorConst.ADMIN_USER_0);
				return "login";
			}
			if (!adminUserVO.getPassword().equals(EncryptUtil.encryptToMD5(password))) {
				jsonObject.put("errorMessage", ErrorConst.PASSWORD_0);
				return "login";
			}
			Object code = session.getAttribute(CaptchaSessionConst.ADMIN_CAPTCHA_KEY);
			if(null!=code&&!StringUtils.isEmpty(code.toString())){
				if (!code.toString().equals(EncryptUtil.encryptToMD5(captcha.toLowerCase()))) {
					jsonObject.put("errorMessage", ErrorConst.CAPTCHA_1);
					return "login";
				}
			}else{
				jsonObject.put("errorMessage", ErrorConst.CAPTCHA_1);
				return "login";
			}
			String id = adminUserVO.getId();
			Timestamp loginTime = DateUtil.getCurrentTimestamp();
			AdminUser adminUser = new AdminUser();
			adminUser.setId(id);
			adminUser.setLogin_time(loginTime);
			this.adminUserServiceImpl.updateAdminUser(adminUser);
			adminUserVO.setLogin_time(DateUtil.DateToString(loginTime, DateStyle.YYYY_MM_DD_HH_MM_SS_EN));
			JSONObject obj = new JSONObject();
			obj.put("id", id);
			obj.put("admin_role_id", adminUserVO.getAdmin_role_id());
			response.addCookie(CookieUtil.addCookie(AdminCookieConst.ADMINUSER_COOKIE_NAME, YuLeEncryptUtil.encode(obj.toString()), CookieTimeConst.DEFAULT_COOKIE));
			obj.clear();
			obj = null;
			setAdminUser(adminUserVO);
			jsonObject.put("status", ErrorConst.STATUS_SUCCESS);
			AdminLogUtil.insertLog("", adminUserVO, LogEnum.LOGIN);
		} catch (Exception e) {
			new YuleException(e);
			throw e;
		} finally{
			request.setAttribute("errorMessage", jsonObject.toString());
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
				obj.put("message", AdminErrorConst.ADMIN_USER_3);
				return null;
			}
			if (StringUtils.isEmpty(password)) {
				obj.put("message", ErrorConst.PASSWORD_1);
				return null;
			}
			if (StringUtils.isEmpty(captcha)) {
				obj.put("errorMessage", ErrorConst.CAPTCHA_0);
				return null;
			}
			AdminUser adminUser = this.adminUserServiceImpl.findAdminUserByAccount(account);
			if(null==adminUser){
				obj.put("message", AdminErrorConst.ADMIN_USER_0);
				return null;
			}
			if (!adminUser.getPassword().equals(EncryptUtil.encryptToMD5(password))) {
				obj.put("message", ErrorConst.PASSWORD_0);
				return null;
			}
			if(StatusConst.STATUS_FALSE == adminUser.getStatus()){
				obj.put("message", AdminErrorConst.ADMIN_USER_1);
				return null;
			}
			Object code = session.getAttribute(CaptchaSessionConst.ADMIN_CAPTCHA_KEY);
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
