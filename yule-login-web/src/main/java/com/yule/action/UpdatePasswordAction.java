package com.yule.action;

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
import com.yule.constant.LoginRedisConst;
import com.yule.constant.TimeConst;
import com.yule.exception.YuleException;
import com.yule.login.service.IUserLoginService;
import com.yule.login.vo.UserLoginInfoVO;
import com.yule.pojo.UserLogin;
import com.yule.redis.util.JedisUtil;

@Controller
@Scope("prototype")
public class UpdatePasswordAction extends BaseAction{

	@Autowired
	private IUserLoginService userLoginServiceImpl = null;
	
	/**
	 * 邮箱修改密码
	 */
	@RequestMapping(value = "/mailUpdatePassword", method = RequestMethod.GET)
	public String findMailUpdatePassword(@RequestParam(value="captcha",required=false) String captcha) throws Exception {
		try {
			if(StringUtils.isEmpty(captcha)){
				return ActionReturnConst.REDIRECT+"/findForgetPasswordCaptcha.do";
			}
			String info = JedisUtil.getInstance().get(LoginRedisConst.USER_UPDATE_PASSWORD+captcha);
			if(StringUtils.isEmpty(info)){
				return ActionReturnConst.REDIRECT+"/findForgetPassword.do";
			}
			UserLoginInfoVO userLoginInfoVO = (UserLoginInfoVO) JSONObject.toBean(JSONObject.fromObject(info),UserLoginInfoVO.class);
			request.setAttribute("name", userLoginInfoVO.getName());
			JedisUtil.getInstance().set(LoginRedisConst.USER_UPDATE_PASSWORD_ID+session.getId(), userLoginInfoVO.getId(),TimeConst.HALF_HOUR);
			JedisUtil.getInstance().set(LoginRedisConst.USER_UPDATE_PASSWORD_CAPTCHA, captcha);
		} catch (Exception e) {
			throw new YuleException(e);
		} finally{
		}
		return "password/password";
	}
	
	/**
	 * 手机修改密码
	 */
	@RequestMapping(value = "/phoneUpdatePassword", method = RequestMethod.POST)
	public String findPhoneUpdatePassword() throws Exception {
		try {
			String info = JedisUtil.getInstance().get(LoginRedisConst.USER_FORGET_PASSWORD+session.getId());
			if(StringUtils.isEmpty(info)){
				return ActionReturnConst.REDIRECT+"/findForgetPassword.do";
			}
			UserLoginInfoVO userLoginInfoVO = (UserLoginInfoVO) JSONObject.toBean(JSONObject.fromObject(info),UserLoginInfoVO.class);
			request.setAttribute("name", userLoginInfoVO.getName());
			JedisUtil.getInstance().set(LoginRedisConst.USER_UPDATE_PASSWORD_ID+session.getId(), userLoginInfoVO.getId(),TimeConst.HALF_HOUR);
			
		} catch (Exception e) {
			throw new YuleException(e);
		} finally{
		}
		return "password/password";
	}
	
	
	/**
	 * 邮箱验证码
	 */
	@RequestMapping(value = "/updatePassword", method = RequestMethod.POST)
	public String updatePassword(@RequestParam(value="password",required=false)String password) throws Exception {
		JSONObject obj = new JSONObject();
		obj.put("status", ErrorConst.STATUS_ERROR);
		try {
			String userUpdateId = JedisUtil.getInstance().get(LoginRedisConst.USER_UPDATE_PASSWORD_ID+session.getId());
			if(StringUtils.isEmpty(userUpdateId)){
				return null;
			}
			if(StringUtils.isEmpty(password)){
				obj.put("message", ErrorConst.PASSWORD_1);
				return null;
			}
			UserLogin userLogin = new UserLogin();
			userLogin.setId(userUpdateId);
			userLogin.setPassword(password);
			userLoginServiceImpl.updateUserLogin(userLogin);
			String captcha = LoginRedisConst.USER_UPDATE_PASSWORD_CAPTCHA;
			if(!StringUtils.isEmpty(captcha)){
				JedisUtil.getInstance().del(LoginRedisConst.USER_UPDATE_PASSWORD+captcha);
			}
			JedisUtil.getInstance().del(LoginRedisConst.USER_UPDATE_PASSWORD_ID+session.getId());
			JedisUtil.getInstance().del(LoginRedisConst.USER_FORGET_PASSWORD+session.getId());
			obj.put("status", ErrorConst.STATUS_SUCCESS);
		} catch (Exception e) {
			throw new YuleException(e);
		} finally{
			outputResult(obj.toString());
		}
		return null;
	}
	
}
