package com.yule.action.set;

import net.sf.json.JSONObject;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.yule.common.BaseAction;
import com.yule.constant.ErrorConst;
import com.yule.constant.UserSessionConst;
import com.yule.exception.YuleException;
import com.yule.util.EncryptUtil;

@Controller
@Scope("prototype")
public class UserAuthCaptchaAction extends BaseAction{
	
	@RequestMapping(value = "/phoneAuthCaptcha",method = RequestMethod.POST)
	public String phoneCaptchaAuth(@RequestParam(value="captcha",required=false)String captcha) throws Exception {
		JSONObject obj = new JSONObject();
		obj.put("status", ErrorConst.STATUS_ERROR);
		try{
			if(StringUtils.isEmpty(captcha)){
				obj.put("message", ErrorConst.CAPTCHA_0);
				return null;
			}
			Object code = session.getAttribute(UserSessionConst.USER_PHONE_AUTH);
			if(null!=code&&!StringUtils.isEmpty(code.toString())){
				if (!code.toString().equals(EncryptUtil.encryptToMD5(captcha))) {
					obj.put("message", ErrorConst.CAPTCHA_1);
					return null;
				}
			}else{
				obj.put("message", ErrorConst.CAPTCHA_1);
				return null;
			}
			obj.put("status", ErrorConst.STATUS_SUCCESS);
			session.removeAttribute(UserSessionConst.USER_PHONE_AUTH);
		} catch(Exception e){
			new YuleException(e);
			throw e;
		} finally{
			outputResult(obj.toString());
		}
		return null;
	}
	
	@RequestMapping(value = "/mailAuthCaptcha",method = RequestMethod.POST)
	public String mailCaptchaAuth(@RequestParam(value="captcha",required=false)String captcha) throws Exception {
		JSONObject obj = new JSONObject();
		obj.put("status", ErrorConst.STATUS_ERROR);
		try{
			if(StringUtils.isEmpty(captcha)){
				obj.put("message", ErrorConst.CAPTCHA_0);
				return null;
			}
			Object code = session.getAttribute(UserSessionConst.USER_MAIL_AUTH);
			if(null!=code&&!StringUtils.isEmpty(code.toString())){
				if (!code.toString().equals(EncryptUtil.encryptToMD5(captcha.toLowerCase()))) {
					obj.put("message", ErrorConst.CAPTCHA_1);
					return null;
				}
			}else{
				obj.put("message", ErrorConst.CAPTCHA_1);
				return null;
			}
			obj.put("status", ErrorConst.STATUS_SUCCESS);
			session.removeAttribute(UserSessionConst.USER_MAIL_AUTH);
		} catch(Exception e){
			new YuleException(e);
			throw e;
		} finally{
			outputResult(obj.toString());
		}
		return null;
	}
	
}