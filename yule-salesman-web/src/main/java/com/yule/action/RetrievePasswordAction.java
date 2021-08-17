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
import com.yule.constant.SalesmanErrorConst;
import com.yule.constant.SalesmanSessionConst;
import com.yule.constant.StatusConst;
import com.yule.exception.YuleException;
import com.yule.mail.salesman.SalesmanUpdatePasswordMail;
import com.yule.pojo.SalesmanLogin;
import com.yule.salesman.service.ISalesmanLoginService;
import com.yule.salesman.vo.SalesmanVO;
import com.yule.util.CaptchaUtil;
import com.yule.util.EncryptUtil;
import com.yule.util.SendMailRunnableUtil;
@Controller
@Scope("prototype")
public class RetrievePasswordAction extends BaseAction {
	
	@Autowired
	private ISalesmanLoginService salesmanLoginService;
	/**
	 * 验证登录
	 */
	@RequestMapping(value = "/sendMail", method = RequestMethod.POST)
	public String sendMail(@RequestParam(value="account",required=false)String account) throws Exception{
		JSONObject obj = new JSONObject();
		obj.put("status", ErrorConst.STATUS_ERROR);
		try{
			if(StringUtils.isEmpty(account)){
				obj.put("message", SalesmanErrorConst.SALESMAN_3);
				return null;
			}
			SalesmanVO salesmanVO = this.salesmanLoginService.findSalesmanVOByAccount(account);
			if(null==salesmanVO){
				obj.put("message", SalesmanErrorConst.SALESMAN_0);
				return null;
			}
			if(StatusConst.STATUS_FALSE==salesmanVO.getStatus()){
				obj.put("message", SalesmanErrorConst.SALESMAN_1);
				return null;
			}
			session.removeAttribute(SalesmanSessionConst.SALESMAN_MAIL);
			String randomString = CaptchaUtil.getRandomString();
			session.setAttribute(SalesmanSessionConst.SALESMAN_MAIL,EncryptUtil.encryptToMD5(randomString.toLowerCase()));
			SalesmanUpdatePasswordMail salesmanUpdatePasswordMail = new SalesmanUpdatePasswordMail();
			salesmanUpdatePasswordMail.setAccount(account);
			salesmanUpdatePasswordMail.setCode(randomString);
			SendMailRunnableUtil.sendMail(salesmanUpdatePasswordMail, salesmanVO.getMail());
			obj.put("id",salesmanVO.getId());
			obj.put("status", ErrorConst.STATUS_SUCCESS);
		} catch(Exception e){
			obj.put("message", ErrorConst.MAIL_1);
			new YuleException("ajax验证企业登录发生错误!",e);
			throw e;
		} finally{
			outputResult(obj.toString());
		}
		return null;
	}
	/**
	 * 验证登录
	 */
	@RequestMapping(value = "/backPassword", method = RequestMethod.POST)
	public String backPassword(@RequestParam(value="id",required=false)String id,
			@RequestParam(value="password",required=false)String password,@RequestParam(value="captcha",required=false)String captcha) throws Exception{
		JSONObject obj = new JSONObject();
		obj.put("status", ErrorConst.STATUS_ERROR);
		try{
			if (StringUtils.isEmpty(captcha)) {
				obj.put("message", ErrorConst.CAPTCHA_0);
				return null;
			}
			if(StringUtils.isEmpty(password)){
				obj.put("message", ErrorConst.PASSWORD_1);
				return null;
			}
			Object code = session.getAttribute(SalesmanSessionConst.SALESMAN_MAIL);
			if(null!=code&&!StringUtils.isEmpty(code.toString())){
				if (!code.toString().equals(EncryptUtil.encryptToMD5(captcha.toLowerCase()))) {
					obj.put("errorMessage", ErrorConst.CAPTCHA_1);
					return null;
				}
			}else{
				obj.put("errorMessage", ErrorConst.CAPTCHA_1);
				return null;
			}
			SalesmanLogin salesmanLogin = new SalesmanLogin();
			salesmanLogin.setId(id);
			salesmanLogin.setPassword(password);
			boolean flag = this.salesmanLoginService.updateSalesmanLogin(salesmanLogin);
			obj.put("status", flag);
		} catch(Exception e){
			new YuleException("ajax找回企业密码出错!",e);
			throw e;
		} finally{
			outputResult(obj.toString());
		}
		return null;
	}


}
