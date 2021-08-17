package com.yule.action;

import net.sf.json.JSONObject;

import org.springframework.beans.BeanUtils;
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
import com.yule.constant.CookieTimeConst;
import com.yule.constant.DoMainConst;
import com.yule.constant.ErrorConst;
import com.yule.constant.LoginRedisConst;
import com.yule.constant.LoginSessionConst;
import com.yule.constant.ScoreConst;
import com.yule.constant.UserCookieConst;
import com.yule.enumerate.DateStyle;
import com.yule.exception.YuleException;
import com.yule.login.service.IUserLoginService;
import com.yule.mail.user.UserRegisterSuccessMail;
import com.yule.mongo.login.service.IUserScoreMongo;
import com.yule.mongo.pojo.UserScore;
import com.yule.pojo.UserLogin;
import com.yule.redis.util.JedisUtil;
import com.yule.sms.UserRegisterSuccessSms;
import com.yule.util.CookieUtil;
import com.yule.util.DateUtil;
import com.yule.util.EncryptUtil;
import com.yule.util.SendMailRunnableUtil;
import com.yule.util.SendSmsRunnableUtil;
import com.yule.util.YuLeEncryptUtil;
import com.yule.vo.UserLoginVO;

@Controller
@Scope("prototype")
public class RegisterAuthActivationAction extends BaseAction{
	
	@Autowired
	private IUserLoginService userLoginServiceImpl = null;
	
	@Autowired
	private IUserScoreMongo userScoreMongoImpl = null;

	/**
	 * 注册验证
	 */
	@RequestMapping(value = "/registerMailAuthActivation", method = RequestMethod.GET)
	public String mailAuth(@RequestParam(value="captcha",required=false)String captcha) throws Exception {
		if(!StringUtils.isEmpty(CookieUtil.getCookieValue(request.getCookies(), UserCookieConst.USER_COOKIE_NAME))){
			return ActionReturnConst.REDIRECT+DoMainConst.PC_URL;
		}
		JSONObject obj = new JSONObject();
		obj.put("status", ErrorConst.STATUS_ERROR);
		try {
			if(StringUtils.isEmpty(captcha)){
				obj.put("message", ErrorConst.CAPTCHA_0);
				return null;
			}
			String userRegisterInfo = JedisUtil.getInstance().get(LoginRedisConst.USER_REGISTER_MAIL+captcha);
			if(StringUtils.isEmpty(userRegisterInfo)){
				obj.put("message", ErrorConst.CAPTCHA_2);
				return null;
			}
			UserLogin userLogin = (UserLogin) JSONObject.toBean(JSONObject.fromObject(userRegisterInfo),UserLogin.class);
			int count = this.userLoginServiceImpl.findUserLoginCountByAccount(userLogin.getMail());
			if(count>0){
				obj.put("message", ErrorConst.USER_2);
				return null;
			}
			userLogin.setMail_auth(AuthConst.IS_AUTH_TRUE);
			this.userLoginServiceImpl.insertUserLogin(userLogin);
			UserLoginVO userLoginVO = new UserLoginVO();
			BeanUtils.copyProperties(userLogin, userLoginVO);
			userLoginVO.setLogin_time(DateUtil.DateToString(DateUtil.getCurrentTimestamp(), DateStyle.YYYY_MM_DD_HH_MM_SS_EN));
			setUserLoginVO(userLoginVO);
			UserScore userScore = new UserScore();
			userScore.setType(ScoreConst.TYPE_ZERO);
			userScore.setScore(ScoreConst.SCORE_REGISTER);
			userScore.setStatus(ScoreConst.STATUS_IN);
			userScore.setUser_id(userLogin.getId());
			userScoreMongoImpl.insertUserScore(userScore);
			UserRegisterSuccessMail userRegisterSuccessMail = new UserRegisterSuccessMail();
			userRegisterSuccessMail.setName(userLogin.getName());
			userRegisterSuccessMail.setMail(userLogin.getMail());
			userRegisterSuccessMail.setScore(ScoreConst.SCORE_REGISTER);
			SendMailRunnableUtil.sendMail(userRegisterSuccessMail, userLogin.getMail());
			obj.put("status", ErrorConst.STATUS_SUCCESS);
			JedisUtil.getInstance().del(LoginRedisConst.USER_REGISTER_MAIL+captcha);
			response.addCookie(CookieUtil.addCookie(UserCookieConst.USER_COOKIE_NAME, YuLeEncryptUtil.encode(userLogin.getId()), CookieTimeConst.DEFAULT_COOKIE));
			response.addCookie(CookieUtil.addCookie(UserCookieConst.USER_TIMEOUT_COOKIE_NAME, String.valueOf(System.currentTimeMillis()), CookieTimeConst.HALF_HOUR));
		} catch (Exception e) {
			throw new YuleException(e);
		} finally{
			request.setAttribute("result", obj.toString());
		}
		return ActionReturnConst.REDIRECT+DoMainConst.PC_URL;
	}
	
	/**
	 * 注册验证
	 */
	@RequestMapping(value = "/registerPhoneAuthActivation", method = RequestMethod.POST)
	public String phoneAuth(@RequestParam(value="captcha",required=false)String captcha) throws Exception {
		final String returnName = "return/register_phone_auth";
		JSONObject 	obj = new JSONObject();
		if(!StringUtils.isEmpty(CookieUtil.getCookieValue(request.getCookies(), UserCookieConst.USER_COOKIE_NAME))){
			obj.put("status", ErrorConst.STATUS_SUCCESS);
			return returnName;
		}
		obj.put("status", ErrorConst.STATUS_ERROR);
		try {
			if(StringUtils.isEmpty(captcha)){
				obj.put("message", ErrorConst.CAPTCHA_0);
				return returnName;
			}
			Object code = session.getAttribute(LoginSessionConst.USER_REGISTER_PHONE_AUTH);
			if(null==code){
				obj.put("message", ErrorConst.CAPTCHA_2);
				return returnName;
			}
			if (!code.toString().equals(EncryptUtil.encryptToMD5(captcha))) {
				obj.put("message", ErrorConst.CAPTCHA_1);
				return returnName;
			}
			String userRegister = JedisUtil.getInstance().get(LoginRedisConst.USER_REGISTER+session.getId());
			if(StringUtils.isEmpty(userRegister)){
				return null;
			}
			UserLogin userLogin = (UserLogin) JSONObject.toBean(JSONObject.fromObject(userRegister.toString()),UserLogin.class);
			int count = this.userLoginServiceImpl.findUserLoginCountByAccount(userLogin.getPhone());
			if(count>0){
				obj.put("message", ErrorConst.USER_2);
				return returnName;
			}
			userLogin.setPhone_auth(AuthConst.IS_AUTH_TRUE);
			this.userLoginServiceImpl.insertUserLogin(userLogin);
			UserLoginVO userLoginVO = new UserLoginVO();
			BeanUtils.copyProperties(userLogin, userLoginVO);
			userLoginVO.setLogin_time(DateUtil.DateToString(DateUtil.getCurrentTimestamp(), DateStyle.YYYY_MM_DD_HH_MM_SS_EN));
			setUserLoginVO(userLoginVO);
			UserRegisterSuccessSms userRegisterSuccessSms = new UserRegisterSuccessSms();
			userRegisterSuccessSms.setScore(ScoreConst.SCORE_REGISTER);
			SendSmsRunnableUtil.sendSms(userRegisterSuccessSms, userLogin.getPhone());
			UserScore userScore = new UserScore();
			userScore.setType(ScoreConst.TYPE_ZERO);
			userScore.setScore(ScoreConst.SCORE_REGISTER);
			userScore.setStatus(ScoreConst.STATUS_IN);
			userScore.setUser_id(userLogin.getId());
			userScoreMongoImpl.insertUserScore(userScore);
			obj.put("status", ErrorConst.STATUS_SUCCESS);
			session.removeAttribute(LoginSessionConst.USER_REGISTER_PHONE_AUTH);
			JedisUtil.getInstance().del(LoginRedisConst.USER_REGISTER+session.getId());
			response.addCookie(CookieUtil.addCookie(UserCookieConst.USER_COOKIE_NAME, YuLeEncryptUtil.encode(userLogin.getId()), CookieTimeConst.DEFAULT_COOKIE));
			response.addCookie(CookieUtil.addCookie(UserCookieConst.USER_TIMEOUT_COOKIE_NAME, String.valueOf(System.currentTimeMillis()), CookieTimeConst.HALF_HOUR));
		} catch (Exception e) {
			throw new YuleException(e);
		} finally{
			request.setAttribute("result", obj.toString());
		}
		return returnName;
	}
	
}