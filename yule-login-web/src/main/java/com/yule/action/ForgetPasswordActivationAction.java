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
import com.yule.constant.LoginSessionConst;
import com.yule.exception.YuleException;
import com.yule.login.service.IUserLoginService;
import com.yule.util.EncryptUtil;

@Controller
@Scope("prototype")
public class ForgetPasswordActivationAction extends BaseAction{

	@Autowired
	private IUserLoginService userLoginServiceImpl = null;
	
	/**
	 * 邮箱验证码
	 */
	@RequestMapping(value = "/phoneForgetPasswordActivation", method = RequestMethod.POST)
	public String phoneForgetPasswordCaptcha(@RequestParam(value="captcha",required=false) String captcha) throws Exception {
		JSONObject 	obj = new JSONObject();
		obj.put("status", ErrorConst.STATUS_ERROR);
		try {
			if(StringUtils.isEmpty(captcha)){
				obj.put("message",ErrorConst.CAPTCHA_0);
				return null;
			}
			Object code = session.getAttribute(LoginSessionConst.USER_FORGET_PASSWORD_PHONE_AUTH);
			if(null==code){
				obj.put("message",ErrorConst.CAPTCHA_2);
				return null;
			}
			if(!code.equals(EncryptUtil.encryptToMD5(captcha))){
				obj.put("message",ErrorConst.CAPTCHA_1);
				return null;
			}
			obj.put("status", ErrorConst.STATUS_SUCCESS);
			session.removeAttribute(LoginSessionConst.USER_FORGET_PASSWORD_PHONE_AUTH);
		} catch (Exception e) {
			throw new YuleException(e);
		} finally{
			outputResult(obj.toString());
		}
		return null;
	}
	
}
