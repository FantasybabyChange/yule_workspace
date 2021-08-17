package com.yule.action.set;

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
import com.yule.constant.ErrorConst;
import com.yule.constant.UserRedisConst;
import com.yule.constant.UserSessionConst;
import com.yule.exception.YuleException;
import com.yule.mail.user.UserAuthSuccessMail;
import com.yule.pojo.UserLogin;
import com.yule.redis.util.JedisUtil;
import com.yule.user.service.IUserLoginService;
import com.yule.util.EncryptUtil;
import com.yule.util.SendMailRunnableUtil;

@Controller
@Scope("prototype")
public class UserAuthActivationAction extends BaseAction{
	
	@Autowired
	private IUserLoginService userLoginServiceImpl;
	
	@RequestMapping(value = "/mailAuthActivation",method = RequestMethod.GET)
	public String mailAuth(@RequestParam(value="captcha",required=false)String captcha) throws Exception {
		JSONObject obj = new JSONObject();
		obj.put("status", ErrorConst.STATUS_ERROR);
		try{
			if(StringUtils.isEmpty(captcha)){
				obj.put("message", ErrorConst.CAPTCHA_0);
				return null;
			}
			String mail = JedisUtil.getInstance().get(UserRedisConst.USER_MAIL_AUTH_ACTIVATION+captcha);
			if(StringUtils.isEmpty(mail)){
				obj.put("message", ErrorConst.CAPTCHA_1);
				return null;
			}
			JedisUtil.getInstance().del(UserRedisConst.USER_MAIL_AUTH_ACTIVATION+captcha);
			int count = userLoginServiceImpl.findUserLoginCountByAccount(mail);
			if(count>0){
				//用户已存在
				return null;
			}
			UserLogin userLogin = new UserLogin();
			userLogin.setId(getCookieValue());
			userLogin.setMail(mail);
			userLoginServiceImpl.updateUserLogin(userLogin);
			setUserLoginVO(userLogin);
			UserAuthSuccessMail userAuthSuccessMail = new UserAuthSuccessMail();
			userAuthSuccessMail.setMail(mail);
			userAuthSuccessMail.setName("yuleing");
			SendMailRunnableUtil.sendMail(userAuthSuccessMail, mail);
			obj.put("status", ErrorConst.STATUS_SUCCESS);
		} catch(Exception e){
			new YuleException(e);
			throw e;
		} finally{
			request.setAttribute("result", obj.toString());
		}
		return ActionReturnConst.REDIRECT+"/set.do";
	}
	
	@RequestMapping(value = "/phoneAuthActivation",method = RequestMethod.POST)
	public String phoneAuth(@RequestParam(value="captcha",required=false)String captcha) throws Exception {
		JSONObject obj = new JSONObject();
		obj.put("status", ErrorConst.STATUS_ERROR);
		try{
			if(StringUtils.isEmpty(captcha)){
				obj.put("message", ErrorConst.CAPTCHA_0);
				return null;
			}
			Object code = session.getAttribute(UserSessionConst.USER_PHONE_AUTH_ACTIVATION);
			if(null==code){
				obj.put("message", ErrorConst.CAPTCHA_2);
				return null;
			}
			if(!code.toString().equals(EncryptUtil.encryptToMD5(captcha))){
				obj.put("message", ErrorConst.CAPTCHA_1);
				return null;
			}
			String phone = JedisUtil.getInstance().get(UserRedisConst.USER_PHONE_AUTH_ACTIVATION_INFO+session.getId());
			if(StringUtils.isEmpty(phone)){
				return null;
			}
			JedisUtil.getInstance().del(UserRedisConst.USER_PHONE_AUTH_ACTIVATION_INFO+session.getId());
			int count = userLoginServiceImpl.findUserLoginCountByAccount(phone);
			if(count>0){
				//用户已存在
				return null;
			}
			session.removeAttribute(UserSessionConst.USER_PHONE_AUTH_ACTIVATION);
			UserLogin userLogin = new UserLogin();
			userLogin.setId(getCookieValue());
			userLogin.setPhone(phone);
			userLoginServiceImpl.updateUserLogin(userLogin);
			setUserLoginVO(userLogin);
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