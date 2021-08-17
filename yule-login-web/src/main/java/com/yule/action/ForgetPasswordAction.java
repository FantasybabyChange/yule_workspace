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
import com.yule.constant.ErrorConst;
import com.yule.constant.LoginCaptchaSessionConst;
import com.yule.constant.LoginRedisConst;
import com.yule.constant.TimeConst;
import com.yule.enumerate.RegExpEnum;
import com.yule.exception.YuleException;
import com.yule.login.service.IUserLoginService;
import com.yule.login.vo.UserLoginInfoVO;
import com.yule.redis.util.JedisUtil;
import com.yule.util.EncryptUtil;
import com.yule.util.RegExpUtil;

@Controller
@Scope("prototype")
public class ForgetPasswordAction extends BaseAction{

	@Autowired
	private IUserLoginService userLoginServiceImpl = null;
	
	/**
	 * 登录
	 */
	@RequestMapping(value = "/findForgetPassword", method = RequestMethod.GET)
	public String findForgetPasswordVerify() throws Exception {
		try { 
			
		} catch (Exception e) {
			throw new YuleException(e);
		} finally{
			
		}
		return "password/forgetPassword";
	}
	
	/**
	 * 登录
	 */
	@RequestMapping(value = "/forgetPassword", method = RequestMethod.POST)
	public String forgetPasswordVerify(@RequestParam(value="account",required=false)String account,@RequestParam(value="captcha",required=false) String captcha) throws Exception {
		JSONObject 	obj = new JSONObject();
		obj.put("status", ErrorConst.STATUS_ERROR);
		try { 
			if(StringUtils.isEmpty(account)){
				obj.put("message", ErrorConst.USER_3);
				return null;
			}
			if(!RegExpUtil.match(account, RegExpEnum.PHONE)&&!RegExpUtil.match(account, RegExpEnum.MAIL)){
				obj.put("message", ErrorConst.USER_4);
				return null;
			}
			if(StringUtils.isEmpty(captcha)){
				obj.put("message", ErrorConst.CAPTCHA_0);
				return null;
			}
			if(null!=captcha){
				Object code = session.getAttribute(LoginCaptchaSessionConst.USER_LOGIN_CAPTCHA);
				if(null!=code&&!StringUtils.isEmpty(code.toString())){
					if (!code.toString().equals(EncryptUtil.encryptToMD5(captcha.toLowerCase()))) {
						obj.put("message", ErrorConst.CAPTCHA_1);
						return null;
					}
				}else{
					obj.put("message", ErrorConst.CAPTCHA_1);
					return null;
				}
			}
			UserLoginInfoVO userLoginInfoVO = userLoginServiceImpl.findUserLoginInfoVOByAccount(account);
			if(userLoginInfoVO==null){
				obj.put("message", ErrorConst.USER_0);
				return null;
			}
			JedisUtil.getInstance().set(LoginRedisConst.USER_FORGET_PASSWORD+session.getId(), JSONObject.fromObject(userLoginInfoVO).toString(),TimeConst.HALF_HOUR);
			obj.put("status", ErrorConst.STATUS_SUCCESS);
		} catch (Exception e) {
			throw new YuleException(e);
		} finally{
			outputResult(obj.toString());
		}
		return null;
	}
	
}
