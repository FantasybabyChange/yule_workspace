/*package com.yule.action.login;

import java.sql.Timestamp;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.yule.common.BaseAction;
import com.yule.constant.CookieTimeConst;
import com.yule.constant.ErrorConst;
import com.yule.constant.LoginCaptchaSessionConst;
import com.yule.constant.UserCookieConst;
import com.yule.enumerate.DateStyle;
import com.yule.enumerate.RegExpEnum;
import com.yule.exception.YuleException;
import com.yule.login.service.IUserLoginService;
import com.yule.pojo.UserLogin;
import com.yule.redis.constant.RedisConst;
import com.yule.redis.util.JedisUtil;
import com.yule.util.CookieUtil;
import com.yule.util.DateUtil;
import com.yule.util.EncryptUtil;
import com.yule.util.RegExpUtil;
import com.yule.util.YuLeEncryptUtil;
import com.yule.vo.UserLoginVO;

@Controller
@Scope("prototype")
public class LoginAction extends BaseAction{
	
	@Autowired
	private IUserLoginService userLoginServiceImpl = null;
	
//	@RequestMapping(value = "/findUserLogin", method = RequestMethod.GET)
//	public String findUserTimeout() throws Exception {
//		String userId = getCookieValue();
//		try{
//			if(StringUtils.isEmpty(userId)){
//				request.setAttribute("type", "0");
//			}else{
//				request.setAttribute("type", "1");
//				userId = YuLeEncryptUtil.decode(userId);
//				if(!StringUtils.isEmpty(CookieUtil.getCookieValue(request.getCookies(), UserCookieConst.USER_TIMEOUT_COOKIE_NAME))){
//					return ActionReturnConst.REDIRECT+DoMainConst.PC_URL;
//				}
//				UserLoginVO userLoginVO = (UserLoginVO) JSONObject.toBean(JSONObject.fromObject(JedisUtil.getInstance().get(RedisConst.USER+session.getId())),UserLoginVO.class);
//				request.setAttribute("userLoginVO", userLoginVO);
//			}
//		} catch(Exception e){
//			throw new YuleException(e);
//		}
//		return "login";
//	}
	
	private final String returnName = "return/login";
	
	*//**
	 * 登录
	 *//*
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(@RequestParam(value="account",required=false)String account,
			@RequestParam(value="password",required=false) String password,
				@RequestParam(value="captcha",required=false) String captcha) throws Exception {
		JSONObject 	obj = new JSONObject();
		obj.put("status", ErrorConst.STATUS_ERROR);
		try { 
			UserLoginVO userLoginVO = null;
			String userId = getCookieValue();
			if(StringUtils.isEmpty(userId)){
				if(StringUtils.isEmpty(account)){
					obj.put("message", ErrorConst.USER_3);
					return returnName;
				}
				if(!RegExpUtil.match(account, RegExpEnum.PHONE)&&!RegExpUtil.match(account, RegExpEnum.MAIL)){
					obj.put("message", ErrorConst.USER_4);
					return returnName;
				}
			}else{
				userLoginVO = (UserLoginVO) JSONObject.toBean(JSONObject.fromObject(JedisUtil.getInstance().get(RedisConst.USER+YuLeEncryptUtil.decode(userId))),UserLoginVO.class);
			}
			if(StringUtils.isEmpty(password)){
				obj.put("message", ErrorConst.PASSWORD_1);
				return returnName;
			}
			if(null!=captcha){
				if("".equals(captcha)){
					obj.put("message", ErrorConst.CAPTCHA_0);
					return returnName;
				}
			}
			if(null!=captcha){
				Object code = session.getAttribute(LoginCaptchaSessionConst.USER_LOGIN_CAPTCHA);
				if(null!=code&&!StringUtils.isEmpty(code.toString())){
					if (!code.toString().equals(EncryptUtil.encryptToMD5(captcha.toLowerCase()))) {
						obj.put("message", ErrorConst.CAPTCHA_1);
						return returnName;
					}
				}else{
					obj.put("message", ErrorConst.CAPTCHA_1);
					return returnName;
				}
			}
			if(StringUtils.isEmpty(userId)){
				userLoginVO = this.userLoginServiceImpl.findUserLoginVOByAccount(account);
			}
			if(null==userLoginVO){
				obj.put("message", ErrorConst.USER_0);
				return returnName;
			}
			if (!userLoginVO.getPassword().equals(EncryptUtil.encryptToMD5(password))) {
				obj.put("message", ErrorConst.PASSWORD_0);
				return returnName;
			}
			String id = userLoginVO.getId();
			Timestamp loginTime = DateUtil.getCurrentTimestamp();
			UserLogin user = new UserLogin();
			user.setId(id);
			user.setLogin_time(loginTime);
			this.userLoginServiceImpl.updateUserLogin(user);
			userLoginVO.setLogin_time(DateUtil.DateToString(loginTime, DateStyle.YYYY_MM_DD_HH_MM_SS_EN));
//			JSONObject obj = new JSONObject();
//			obj.put("id", id);
			if(StringUtils.isEmpty(userId)){
				response.addCookie(CookieUtil.addCookie(UserCookieConst.USER_COOKIE_NAME, YuLeEncryptUtil.encode(id), CookieTimeConst.DEFAULT_COOKIE));
			}
			response.addCookie(CookieUtil.addCookie(UserCookieConst.USER_TIMEOUT_COOKIE_NAME, String.valueOf(System.currentTimeMillis()), CookieTimeConst.HALF_HOUR));
//			obj.clear();
//			obj = null;
			//setUserLoginVO(userLoginVO);
			obj.put("status", ErrorConst.STATUS_SUCCESS);
		} catch (Exception e) {
			new YuleException(e);
			throw e;
		} finally{
			request.setAttribute("result", obj.toString());
		}
		return returnName;
	}
	
}*/