package com.yule.action;

import java.util.UUID;

import net.sf.json.JSONObject;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.yule.common.BaseAction;
import com.yule.constant.CaptchaTimeConst;
import com.yule.constant.ErrorConst;
import com.yule.constant.LoginRedisConst;
import com.yule.constant.LoginSessionConst;
import com.yule.constant.ScoreConst;
import com.yule.constant.TimeConst;
import com.yule.constant.UserConst;
import com.yule.mail.user.UserRegisterActivationMail;
import com.yule.pojo.UserLogin;
import com.yule.redis.util.JedisUtil;
import com.yule.sms.UserRegisterSuccessSms;
import com.yule.util.CaptchaUtil;
import com.yule.util.EncryptUtil;
import com.yule.util.SendMailRunnableUtil;
import com.yule.util.SendSmsRunnableUtil;

@Controller
@Scope("prototype")
public class RegisterCaptchaAction extends BaseAction{
	
	private final String returnName = "return/captcha";
	
	@RequestMapping(value = "/phoneCaptcha",method = RequestMethod.POST)
	public String phoneCaptcha() throws Exception {
		JSONObject obj = new JSONObject();
		obj.put("status", ErrorConst.STATUS_ERROR);
		if(!StringUtils.isEmpty(getCookieValue())){
			return returnName;
		}
		obj.put("type", UserConst.PHONE);
		try{
			String userRegister = JedisUtil.getInstance().get(LoginRedisConst.USER_REGISTER+session.getId());
			if(StringUtils.isEmpty(userRegister)){
				return returnName;
			}
			UserLogin userLogin = (UserLogin) JSONObject.toBean(JSONObject.fromObject(userRegister),UserLogin.class);
			String account = userLogin.getPhone();
			long time = CaptchaTimeConst.CAPTCHA_TIME;
			String timeKey  = LoginRedisConst.REGISTER_AUTH_TIME+account;
			if(!JedisUtil.getInstance().exists(timeKey)){
				String randomString = CaptchaUtil.getRandomNumString(6);
				UserRegisterSuccessSms userRegisterSuccessSms = new UserRegisterSuccessSms();
				userRegisterSuccessSms.setScore(ScoreConst.SCORE_REGISTER);
				SendSmsRunnableUtil.sendSms(userRegisterSuccessSms, account);
				obj.put("c", randomString);
				session.setAttribute(LoginSessionConst.USER_REGISTER_PHONE_AUTH, EncryptUtil.encryptToMD5(randomString));
				JedisUtil.getInstance().set(timeKey, String.valueOf(System.currentTimeMillis()),TimeConst.ONE_HUNDRED_TWENTY_SECOND);
			}else{
				time = time - ((System.currentTimeMillis() - Long.valueOf(JedisUtil.getInstance().get(timeKey)))/1000);
			}
			obj.put("time", time);
			obj.put("status", ErrorConst.STATUS_SUCCESS);
		} catch(Exception e){
			throw e;
		} finally{
			request.setAttribute("result", obj.toString());
		}
		return returnName;
	}
	
	@RequestMapping(value = "/mailCaptcha",method = RequestMethod.POST)
	public String mailCaptcha() throws Exception {
		JSONObject obj = new JSONObject();
		obj.put("status", ErrorConst.STATUS_ERROR);
		if(!StringUtils.isEmpty(getCookieValue())){
			return returnName;
		}
		obj.put("type", UserConst.MAIL);
		try{
			if(!StringUtils.isEmpty(getCookieValue())){
				return returnName;
			}
			String userRegister = JedisUtil.getInstance().get(LoginRedisConst.USER_REGISTER+session.getId());
			if(StringUtils.isEmpty(userRegister)){
				return returnName;
			}
			UserLogin userLogin = (UserLogin) JSONObject.toBean(JSONObject.fromObject(userRegister),UserLogin.class);
			String account = userLogin.getMail();
			long time = CaptchaTimeConst.CAPTCHA_TIME;
			String timeKey  = LoginRedisConst.REGISTER_AUTH_TIME+account;
			if(!JedisUtil.getInstance().exists(timeKey)){
				String randomString = UUID.randomUUID().toString();
				UserRegisterActivationMail userRegisterCaptchaMail = new UserRegisterActivationMail();
				userRegisterCaptchaMail.setMail(account);
				userRegisterCaptchaMail.setCaptcha(randomString);
				SendMailRunnableUtil.sendMail(userRegisterCaptchaMail, account);
				obj.put("c", randomString);
				JedisUtil.getInstance().set(LoginRedisConst.USER_REGISTER_MAIL+randomString, JSONObject.fromObject(userLogin).toString(),TimeConst.ONE_DAY);
				JedisUtil.getInstance().set(timeKey, String.valueOf(System.currentTimeMillis()),TimeConst.ONE_HUNDRED_TWENTY_SECOND);
			}else{
				time = time - ((System.currentTimeMillis() - Long.valueOf(JedisUtil.getInstance().get(timeKey)))/1000);
			}
			obj.put("time", time);
			obj.put("status", ErrorConst.STATUS_SUCCESS);
		} catch(Exception e){
			throw e;
		} finally{
			request.setAttribute("result", obj.toString());
		}
		return returnName;
	}
	
}