package com.yule.action;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.yule.common.BaseAction;
import com.yule.constant.ActionReturnConst;
import com.yule.constant.CaptchaTimeConst;
import com.yule.constant.ErrorConst;
import com.yule.constant.LoginRedisConst;
import com.yule.constant.LoginSessionConst;
import com.yule.constant.TimeConst;
import com.yule.exception.YuleException;
import com.yule.login.service.IUserLoginService;
import com.yule.login.vo.UserLoginInfoVO;
import com.yule.mail.user.UserForgetPasswordMail;
import com.yule.redis.util.JedisUtil;
import com.yule.sms.UserForgetPasswordSms;
import com.yule.util.CaptchaUtil;
import com.yule.util.EncryptUtil;
import com.yule.util.SendMailRunnableUtil;
import com.yule.util.SendSmsRunnableUtil;

@Controller
@Scope("prototype")
public class ForgetPasswordCaptchaAction extends BaseAction{

	@Autowired
	private IUserLoginService userLoginServiceImpl;
	
	/**
	 * 邮箱验证码
	 */
	@RequestMapping(value = "/findForgetPasswordCaptcha", method = RequestMethod.GET)
	public String findForgetPasswordCaptcha() throws Exception {
		try {
			if(!JedisUtil.getInstance().exists(LoginRedisConst.USER_FORGET_PASSWORD+session.getId())){
				return ActionReturnConst.REDIRECT+"/findForgetPassword.do";
			}
			UserLoginInfoVO userLoginInfoVO = (UserLoginInfoVO) JSONObject.toBean(JSONObject.fromObject(JedisUtil.getInstance().get(LoginRedisConst.USER_FORGET_PASSWORD+session.getId())),UserLoginInfoVO.class);
			request.setAttribute("userLoginInfoVO", userLoginInfoVO);
		} catch (Exception e) {
			throw new YuleException(e);
		} 
		return "password/captcha";
	}
	
	/**
	 * 邮箱验证码
	 */
	@RequestMapping(value = "/mailForgetPasswordCaptcha", method = RequestMethod.POST)
	public String mailForgetPasswordCaptcha() throws Exception {
		JSONObject 	obj = new JSONObject();
		obj.put("status", ErrorConst.STATUS_ERROR);
		try {
			String info = JedisUtil.getInstance().get(LoginRedisConst.USER_FORGET_PASSWORD+session.getId());
			if(StringUtils.isEmpty(info)){
				return null;
			}
			UserLoginInfoVO userLoginInfoVO = (UserLoginInfoVO) JSONObject.toBean(JSONObject.fromObject(info),UserLoginInfoVO.class);
			String account = userLoginInfoVO.getMail();
			if(StringUtils.isEmpty(account)){
				return null;
			}
			String timeKey  = LoginRedisConst.FORGET_PASSWORD_AUTH_TIME+account;
			long time = CaptchaTimeConst.CAPTCHA_TIME;
			if(!JedisUtil.getInstance().exists(timeKey)){
				String randomString = userLoginInfoVO.getId();
				UserForgetPasswordMail userForgetPasswordMail = new UserForgetPasswordMail();
				userForgetPasswordMail.setName(userLoginInfoVO.getName());
				userForgetPasswordMail.setCaptcha(randomString);
				SendMailRunnableUtil.sendMail(userForgetPasswordMail, account);
				obj.put("c", randomString);
				JedisUtil.getInstance().set(LoginRedisConst.USER_UPDATE_PASSWORD+randomString, JSONObject.fromObject(userLoginInfoVO).toString(),TimeConst.ONE_DAY);
				JedisUtil.getInstance().set(timeKey, String.valueOf(System.currentTimeMillis()),TimeConst.ONE_HUNDRED_TWENTY_SECOND);
			}else{
				time = time - ((System.currentTimeMillis() - Long.valueOf(JedisUtil.getInstance().get(timeKey)))/1000);
			}
			obj.put("time", time);
			obj.put("status", ErrorConst.STATUS_SUCCESS);
		} catch (Exception e) {
			throw new YuleException(e);
		} finally{
			outputResult(obj.toString());
		}
		return null;
	}
	
	/**
	 * 手机验证码
	 */
	@RequestMapping(value = "/phoneForgetPasswordCaptcha", method = RequestMethod.POST)
	public String phoneForgetPasswordCaptcha() throws Exception {
		JSONObject 	obj = new JSONObject();
		obj.put("status", ErrorConst.STATUS_ERROR);
		try {
			String info = JedisUtil.getInstance().get(LoginRedisConst.USER_FORGET_PASSWORD+session.getId());
			if(StringUtils.isEmpty(info)){
				return null;
			}
			UserLoginInfoVO userLoginInfoVO = (UserLoginInfoVO) JSONObject.toBean(JSONObject.fromObject(info),UserLoginInfoVO.class);
			String account = userLoginInfoVO.getPhone();
			if(StringUtils.isEmpty(account)){
				return null;
			}
			String timeKey  = LoginRedisConst.FORGET_PASSWORD_AUTH_TIME+account;
			long time = CaptchaTimeConst.CAPTCHA_TIME;
			if(!JedisUtil.getInstance().exists(timeKey)){
				String randomString = CaptchaUtil.getRandomNumString(6);
				UserForgetPasswordSms userForgetPasswordSms = new UserForgetPasswordSms();
				userForgetPasswordSms.setCaptcha(randomString);
				SendSmsRunnableUtil.sendSms(userForgetPasswordSms, account, "");
				obj.put("c", randomString);
				session.setAttribute(LoginSessionConst.USER_FORGET_PASSWORD_PHONE_AUTH, EncryptUtil.encryptToMD5(randomString));
				obj.put("captcha", userLoginInfoVO.getId());
				JedisUtil.getInstance().set(timeKey, String.valueOf(System.currentTimeMillis()),TimeConst.ONE_HUNDRED_TWENTY_SECOND);
			}else{
				time = time - ((System.currentTimeMillis() - Long.valueOf(JedisUtil.getInstance().get(timeKey)))/1000);
			}
			obj.put("time", time);
			obj.put("status", ErrorConst.STATUS_SUCCESS);
		} catch (Exception e) {
			throw new YuleException(e);
		} finally{
			outputResult(obj.toString());
		}
		return null;
	}
	
}
