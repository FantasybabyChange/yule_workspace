package com.yule.action.set;

import net.sf.json.JSONObject;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.yule.common.BaseAction;
import com.yule.constant.CaptchaTimeConst;
import com.yule.constant.ErrorConst;
import com.yule.constant.TimeConst;
import com.yule.constant.UserRedisConst;
import com.yule.constant.UserSessionConst;
import com.yule.mail.user.UserAuthCaptchaMail;
import com.yule.redis.util.JedisUtil;
import com.yule.sms.UserAuthCaptchaSms;
import com.yule.util.CaptchaUtil;
import com.yule.util.EncryptUtil;
import com.yule.util.SendMailRunnableUtil;
import com.yule.util.SendSmsRunnableUtil;

@Controller
@Scope("prototype")
public class UserCaptchaAction extends BaseAction{
	
	
	@RequestMapping(value = "/phoneCaptcha",method = RequestMethod.POST)
	public String phoneCaptcha() throws Exception {
		JSONObject obj = new JSONObject();
		obj.put("status", ErrorConst.STATUS_ERROR);
		try{
			String phone = getUserLoginVO().getPhone();
			long time = CaptchaTimeConst.CAPTCHA_TIME;
			String timeKey  = UserRedisConst.USER_AUTH_TIME+phone;
			if(!JedisUtil.getInstance().exists(timeKey)){
				JedisUtil.getInstance().set(timeKey, String.valueOf(System.currentTimeMillis()),TimeConst.ONE_HUNDRED_TWENTY_SECOND);
				String randomString = CaptchaUtil.getRandomNumString(6);
				UserAuthCaptchaSms userAuthCaptchaSms = new UserAuthCaptchaSms();
				userAuthCaptchaSms.setCaptcha(randomString);
				SendSmsRunnableUtil.sendSms(userAuthCaptchaSms, phone);
				obj.put("c", randomString);
				session.setAttribute(UserSessionConst.USER_PHONE_AUTH, EncryptUtil.encryptToMD5(randomString));
			}else{
				time = time - ((System.currentTimeMillis() - Long.valueOf(JedisUtil.getInstance().get(timeKey)))/1000);
			}
			obj.put("time", time);
			obj.put("status", ErrorConst.STATUS_SUCCESS);
		} catch(Exception e){
			throw e;
		} finally{
			outputResult(obj.toString());
		}
		return null;
	}
	
	@RequestMapping(value = "/mailCaptcha",method = RequestMethod.POST)
	public String mailCaptcha() throws Exception {
		JSONObject obj = new JSONObject();
		obj.put("status", ErrorConst.STATUS_ERROR);
		try{
			String mail = getUserLoginVO().getMail();
			long time = CaptchaTimeConst.CAPTCHA_TIME;
			String timeKey  = UserRedisConst.USER_AUTH_TIME+mail;
			if(!JedisUtil.getInstance().exists(timeKey)){
				JedisUtil.getInstance().set(timeKey, String.valueOf(System.currentTimeMillis()),TimeConst.ONE_HUNDRED_TWENTY_SECOND);
				String randomString = CaptchaUtil.getRandomString(6);
				UserAuthCaptchaMail userAuthCaptchaMail = new UserAuthCaptchaMail();
				userAuthCaptchaMail.setName(getUserLoginVO().getName());
				userAuthCaptchaMail.setCaptcha(randomString);	
				SendMailRunnableUtil.sendMail(userAuthCaptchaMail, mail);
				obj.put("c", randomString);
				session.setAttribute(UserSessionConst.USER_MAIL_AUTH, EncryptUtil.encryptToMD5(randomString.toLowerCase()));
			}else{
				time = time - ((System.currentTimeMillis() - Long.valueOf(JedisUtil.getInstance().get(timeKey)))/1000);
			}
			obj.put("time", time);
			obj.put("status", ErrorConst.STATUS_SUCCESS);
		} catch(Exception e){
			throw e;
		} finally{
			outputResult(obj.toString());
		}
		return null;
	}
	
}