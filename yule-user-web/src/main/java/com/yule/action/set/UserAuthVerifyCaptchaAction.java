package com.yule.action.set;

import java.util.UUID;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.yule.common.BaseAction;
import com.yule.constant.CaptchaTimeConst;
import com.yule.constant.ErrorConst;
import com.yule.constant.TimeConst;
import com.yule.constant.UserRedisConst;
import com.yule.mail.user.UserAuthActivationMail;
import com.yule.redis.util.JedisUtil;
import com.yule.user.service.IUserLoginService;
import com.yule.util.SendMailRunnableUtil;

@Controller
@Scope("prototype")
public class UserAuthVerifyCaptchaAction extends BaseAction{
	
	@Autowired
	private IUserLoginService userLoginServiceImpl;
	
	@RequestMapping(value = "/mailAuthVerifyCaptcha",method = RequestMethod.POST)
	public String mailAuth() throws Exception {
		JSONObject obj = new JSONObject();
		obj.put("status", ErrorConst.STATUS_ERROR);
		try{
			String mail = JedisUtil.getInstance().get(UserRedisConst.USER_MAIL_AUTH_ACTIVATION_INFO+session.getId());
			if(StringUtils.isEmpty(mail)){
				return null;
			}
			long time = CaptchaTimeConst.CAPTCHA_TIME;
			String timeKey  = UserRedisConst.USER_AUTH_TIME+mail;
			if(!JedisUtil.getInstance().exists(timeKey)){
				String randomString = UUID.randomUUID().toString();
				obj.put("c", randomString);
				JedisUtil.getInstance().set(UserRedisConst.USER_MAIL_AUTH_ACTIVATION+randomString,mail,TimeConst.ONE_DAY);
				UserAuthActivationMail userAuthActivationMail = new UserAuthActivationMail();
				userAuthActivationMail.setName(mail.toString());
				userAuthActivationMail.setCaptcha(randomString);
				SendMailRunnableUtil.sendMail(userAuthActivationMail, mail.toString());
				JedisUtil.getInstance().set(timeKey, String.valueOf(System.currentTimeMillis()),TimeConst.ONE_HUNDRED_TWENTY_SECOND);
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