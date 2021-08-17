package com.yule.action.set;

import java.util.UUID;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.yule.common.BaseAction;
import com.yule.constant.CaptchaSessionConst;
import com.yule.constant.CaptchaTimeConst;
import com.yule.constant.ErrorConst;
import com.yule.constant.TimeConst;
import com.yule.constant.UserRedisConst;
import com.yule.constant.UserSessionConst;
import com.yule.enumerate.RegExpEnum;
import com.yule.exception.YuleException;
import com.yule.mail.user.UserInsertAuthActivationMail;
import com.yule.redis.util.JedisUtil;
import com.yule.sms.UserInsertAuthActivationSms;
import com.yule.user.service.IUserLoginService;
import com.yule.util.CaptchaUtil;
import com.yule.util.EncryptUtil;
import com.yule.util.RegExpUtil;
import com.yule.util.SendMailRunnableUtil;
import com.yule.util.SendSmsRunnableUtil;

@Controller
@Scope("prototype")
public class UserInsertAuthAction extends BaseAction{
	
	@Autowired
	private IUserLoginService userLoginServiceImpl;
	
	@RequestMapping(value = "/mailInsertAuth",method = RequestMethod.POST)
	public String mailAuth(@RequestParam(value="mail",required=false)String mail,@RequestParam(value="captcha",required=false)String captcha) throws Exception {
		JSONObject obj = new JSONObject();
		obj.put("status", ErrorConst.STATUS_ERROR);
		try{
			if(StringUtils.isEmpty(mail)){
				obj.put("message", ErrorConst.MAIL_2);
				return null;
			}
			if(StringUtils.isEmpty(captcha)){
				obj.put("message", ErrorConst.CAPTCHA_0);
				return null;
			}
			if(!RegExpUtil.match(mail, RegExpEnum.MAIL)){
				obj.put("message", ErrorConst.MAIL_5);
				return null;
			}
			Object code = session.getAttribute(CaptchaSessionConst.USER_AUTH_CAPTCHA);
			if(null!=code&&!StringUtils.isEmpty(code.toString())){
				if (!code.equals(EncryptUtil.encryptToMD5(captcha.toLowerCase()))) {
					obj.put("message", ErrorConst.CAPTCHA_1);
					return null;
				}
			}else{
				obj.put("message", ErrorConst.CAPTCHA_1);
				return null;
			}
			int count = userLoginServiceImpl.findUserLoginCountByAccount(mail);
			if(count>0){
				obj.put("message", ErrorConst.MAIL_3);
				return null;
			}
			long time = CaptchaTimeConst.CAPTCHA_TIME;
			String timeKey  = UserRedisConst.USER_AUTH_TIME+mail;
			if(!JedisUtil.getInstance().exists(timeKey)){
				String randomString = UUID.randomUUID().toString();
				obj.put("c", randomString);
				UserInsertAuthActivationMail userInsertAuthActivationMail = new UserInsertAuthActivationMail();
				userInsertAuthActivationMail.setCaptcha(randomString);
				userInsertAuthActivationMail.setName(getUserLoginVO().getName());
				SendMailRunnableUtil.sendMail(userInsertAuthActivationMail, mail);
				JSONObject result = new JSONObject();
				result.put("id", getCookieValue());
				result.put("mail", mail);
				JedisUtil.getInstance().set(UserRedisConst.USER_INSERT_MAIL_AUTH_ACTIVATION+randomString, result.toString(),TimeConst.ONE_DAY);
				JedisUtil.getInstance().set(timeKey, String.valueOf(System.currentTimeMillis()),TimeConst.ONE_HUNDRED_TWENTY_SECOND);
			}else{
				time = time - ((System.currentTimeMillis() - Long.valueOf(JedisUtil.getInstance().get(timeKey)))/1000);
			}
			JedisUtil.getInstance().set(UserRedisConst.USER_INSERT_AUTH_INFO+session.getId(), mail,TimeConst.HALF_HOUR);
			obj.put("time", time);
			obj.put("status", ErrorConst.STATUS_SUCCESS);
		} catch(Exception e){
			new YuleException(e);
			throw e;
		} finally{
			outputResult(obj.toString());
		}
		return null;
	}
	
	@RequestMapping(value = "/phoneInsertAuth",method = RequestMethod.POST)
	public String phoneAuth(@RequestParam(value="phone",required=false)String phone) throws Exception {
		JSONObject obj = new JSONObject();
		obj.put("status", ErrorConst.STATUS_ERROR);
		try{
			if(StringUtils.isEmpty(phone)){
				obj.put("message", ErrorConst.PHONE_0);
				return null;
			}
			if(!RegExpUtil.match(phone, RegExpEnum.PHONE)){
				obj.put("message", ErrorConst.PHONE_3);
				return null;
			}
			int count = userLoginServiceImpl.findUserLoginCountByAccount(phone);
			if(count>0){
				obj.put("message", ErrorConst.PHONE_1);
				return null;
			}
			long time = CaptchaTimeConst.CAPTCHA_TIME;
			String timeKey  = UserRedisConst.USER_AUTH_TIME+phone;
			if(!JedisUtil.getInstance().exists(timeKey)){
				String randomString = CaptchaUtil.getRandomNumString(6);
				UserInsertAuthActivationSms userInsertAuthActivationSms = new UserInsertAuthActivationSms();
				userInsertAuthActivationSms.setCaptcha(randomString);
				SendSmsRunnableUtil.sendSms(userInsertAuthActivationSms, phone);
				obj.put("c", randomString);
				session.setAttribute(UserSessionConst.USER_INSERT_PHONE_AUTH_ACTIVATION, EncryptUtil.encryptToMD5(randomString));
				JedisUtil.getInstance().set(timeKey, String.valueOf(System.currentTimeMillis()),TimeConst.ONE_HUNDRED_TWENTY_SECOND);
			}else{
				time = time - ((System.currentTimeMillis() - Long.valueOf(JedisUtil.getInstance().get(timeKey)))/1000);
			}
			JedisUtil.getInstance().set(UserRedisConst.USER_INSERT_AUTH_INFO+session.getId(), phone,TimeConst.HALF_HOUR);
			obj.put("time", time);
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