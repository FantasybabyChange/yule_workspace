package com.yule.action;

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
import com.yule.constant.CaptchaTimeConst;
import com.yule.constant.ErrorConst;
import com.yule.constant.LoginCaptchaSessionConst;
import com.yule.constant.LoginRedisConst;
import com.yule.constant.LoginSessionConst;
import com.yule.constant.TimeConst;
import com.yule.constant.UserConst;
import com.yule.enumerate.RegExpEnum;
import com.yule.exception.YuleException;
import com.yule.login.service.IUserLoginService;
import com.yule.mail.user.UserRegisterActivationMail;
import com.yule.pojo.UserLogin;
import com.yule.redis.util.JedisUtil;
import com.yule.sms.UserRegisterCaptchaSms;
import com.yule.util.CaptchaUtil;
import com.yule.util.EncryptUtil;
import com.yule.util.RegExpUtil;
import com.yule.util.SendMailRunnableUtil;

@Controller
@Scope("prototype")
public class RegisterAction extends BaseAction{
	
	@Autowired
	private IUserLoginService userLoginServiceImpl = null;
	
	private final String returnName = "return/register";
	
	/**
	 * 登录
	 */
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String register(@RequestParam(value="account",required=false)String account,@RequestParam(value="password",required=false) String password,
			@RequestParam(value="captcha",required=false) String captcha) throws Exception {
		JSONObject 	obj = new JSONObject();
		obj.put("status", ErrorConst.STATUS_ERROR);
		try {
			if(StringUtils.isEmpty(account)){
				obj.put("message", ErrorConst.USER_3);
				return returnName;
			}
			boolean phoneCheck = RegExpUtil.match(account, RegExpEnum.PHONE);
			boolean mailCheck = RegExpUtil.match(account, RegExpEnum.MAIL);
			if(!mailCheck&&!phoneCheck){
				obj.put("message", ErrorConst.USER_4);
				return returnName;
			}
			if(StringUtils.isEmpty(password)){
				obj.put("message", ErrorConst.PASSWORD_1);
				return returnName;
			}
			if (StringUtils.isEmpty(captcha)) {
				obj.put("message", ErrorConst.CAPTCHA_0);
				return returnName;
			}
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
			int count = this.userLoginServiceImpl.findUserLoginCountByAccount(account);
			if(count>0){
				obj.put("message", ErrorConst.USER_2);
				return returnName;
			}
			UserLogin userLogin = new UserLogin();
			if(phoneCheck){
				userLogin.setPhone(account);
				obj.put("type", UserConst.PHONE);
			}else if(mailCheck){
				userLogin.setMail(account);
				obj.put("type", UserConst.MAIL);
			}
			userLogin.setPassword(password);
			userLogin.setName(UserConst.DEFAULT_NAME);
			JedisUtil.getInstance().set(LoginRedisConst.USER_REGISTER+session.getId(), JSONObject.fromObject(userLogin).toString(),TimeConst.HALF_HOUR);
			String timeKey  = LoginRedisConst.REGISTER_AUTH_TIME+account;
			long time = CaptchaTimeConst.CAPTCHA_TIME;
			if(!JedisUtil.getInstance().exists(timeKey)){
				String randomString = null;
				if(mailCheck){
					randomString = UUID.randomUUID().toString();
					UserRegisterActivationMail userRegisterCaptchaMail = new UserRegisterActivationMail();
					userRegisterCaptchaMail.setMail(account);
					userRegisterCaptchaMail.setCaptcha(randomString);
					SendMailRunnableUtil.sendMail(userRegisterCaptchaMail, account);
					obj.put("c", randomString);
					JedisUtil.getInstance().set(LoginRedisConst.USER_REGISTER_MAIL+randomString, JSONObject.fromObject(userLogin).toString(),TimeConst.ONE_DAY);
				}else if(phoneCheck){
					randomString = CaptchaUtil.getRandomNumString(6);
					UserRegisterCaptchaSms userRegisterCaptchaSms = new UserRegisterCaptchaSms();
					userRegisterCaptchaSms.setCaptcha(randomString);
//					SendSmsRunnableUtil.sendSms(userRegisterCaptchaSms, account, "");
					obj.put("c", randomString);
					session.setAttribute(LoginSessionConst.USER_REGISTER_PHONE_AUTH, EncryptUtil.encryptToMD5(randomString));
				}
				JedisUtil.getInstance().set(timeKey, String.valueOf(System.currentTimeMillis()),TimeConst.ONE_HUNDRED_TWENTY_SECOND);
			}else{
				time = time - ((System.currentTimeMillis() - Long.valueOf(JedisUtil.getInstance().get(timeKey)))/1000);
			}
			obj.put("time", time);
			obj.put("status", ErrorConst.STATUS_SUCCESS);
		} catch (Exception e) {
			new YuleException(e);
			throw e;
		} finally{
			request.setAttribute("result", obj.toString());
		}
		return returnName;
	}
	
}