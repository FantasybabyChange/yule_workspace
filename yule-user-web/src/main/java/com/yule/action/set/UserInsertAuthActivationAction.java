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
import com.yule.constant.AuthConst;
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
public class UserInsertAuthActivationAction extends BaseAction{
	
	@Autowired
	private IUserLoginService userLoginServiceImpl;
	
	@RequestMapping(value = "/mailInsertAuthActivation",method = RequestMethod.GET)
	public String mailAuth(@RequestParam(value="captcha",required=false)String captcha) throws Exception {
		JSONObject obj = new JSONObject();
		obj.put("status", ErrorConst.STATUS_ERROR);
		try{
			if(StringUtils.isEmpty(captcha)){
				obj.put("message", ErrorConst.CAPTCHA_0);
				return null;
			}
			String info = JedisUtil.getInstance().get(UserRedisConst.USER_INSERT_MAIL_AUTH_ACTIVATION+captcha);
			if(null==info){
				return null;
			}
			JedisUtil.getInstance().del(UserRedisConst.USER_INSERT_MAIL_AUTH_ACTIVATION+captcha);
			JSONObject result = JSONObject.fromObject(info);
			String mail = result.getString("mail");
			int count = userLoginServiceImpl.findUserLoginCountByAccount(mail);
			if(count>0){
				obj.put("message", ErrorConst.MAIL_3);
				return null;
			}
			UserLogin userLogin = new UserLogin();
			userLogin.setId(result.getString("id"));
			userLogin.setMail(mail);
			userLogin.setMail_auth(AuthConst.IS_AUTH_TRUE);
			userLoginServiceImpl.updateUserLogin(userLogin);
			setUserLoginVO(userLogin);
			UserAuthSuccessMail userAuthSuccessMail = new UserAuthSuccessMail();
			userAuthSuccessMail.setName(getUserLoginVO().getName());
			userAuthSuccessMail.setMail(mail);
			SendMailRunnableUtil.sendMail(userAuthSuccessMail, mail);
			obj.put("status", ErrorConst.STATUS_SUCCESS);
		} catch(Exception e){
			new YuleException(e);
			throw e;
		} finally{
			outputResult(obj.toString());
		}
		return ActionReturnConst.REDIRECT+"/set.do";
	}
	
	@RequestMapping(value = "/phoneInsertAuthActivation",method = RequestMethod.POST)
	public String phoneAuth(@RequestParam(value="captcha",required=false)String captcha) throws Exception {
		JSONObject obj = new JSONObject();
		obj.put("status", ErrorConst.STATUS_ERROR);
		try{
//			if(StringUtils.isEmpty(phone)){
//				obj.put("message", ErrorConst.PHONE_0);
//				return null;
//			}
			if(StringUtils.isEmpty(captcha)){
				obj.put("message", ErrorConst.CAPTCHA_0);
				return null;
			}
//			if(!RegExpUtil.match(phone, RegExpEnum.PHONE)){
//				obj.put("message", ErrorConst.PHONE_3);
//				return null;
//			}
//			if(!phone.equals(authInfo.toString())){
//				obj.put("message", ErrorConst.CAPTCHA_1);
//				return null;
//			}
			String phone = JedisUtil.getInstance().get(UserRedisConst.USER_INSERT_AUTH_INFO+session.getId());
			if(null==phone){
				return null;
			}
			JedisUtil.getInstance().del(UserRedisConst.USER_INSERT_AUTH_INFO+session.getId());
			Object cpde = session.getAttribute(UserSessionConst.USER_INSERT_PHONE_AUTH_ACTIVATION);
			if(null==cpde){
				return null;
			}
			if(!cpde.toString().equals(EncryptUtil.encryptToMD5(captcha))){
				obj.put("message", ErrorConst.CAPTCHA_1);
				return null;
			}
			session.removeAttribute(UserSessionConst.USER_INSERT_PHONE_AUTH_ACTIVATION);
			int count = userLoginServiceImpl.findUserLoginCountByAccount(phone);
			if(count>0){
				obj.put("message", ErrorConst.PHONE_1);
				return null;
			}
			UserLogin userLogin = new UserLogin();
			userLogin.setId(getCookieValue());
			userLogin.setPhone(phone);
			userLogin.setPhone_auth(AuthConst.IS_AUTH_TRUE);
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