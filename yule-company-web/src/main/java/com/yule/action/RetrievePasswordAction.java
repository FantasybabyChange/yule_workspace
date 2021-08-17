package com.yule.action;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.yule.common.BaseAction;
import com.yule.company.query.CompanyLoginQuery;
import com.yule.company.service.ICompanyUserService;
import com.yule.company.vo.CompanyUserVO;
import com.yule.constant.CompanyErrorConst;
import com.yule.constant.CompanySessionConst;
import com.yule.constant.ErrorConst;
import com.yule.constant.StatusConst;
import com.yule.exception.YuleException;
import com.yule.mail.company.CompanyUpdatePasswordMail;
import com.yule.pojo.CompanyUser;
import com.yule.util.CaptchaUtil;
import com.yule.util.EncryptUtil;
import com.yule.util.SendMailRunnableUtil;
@Controller
@Scope("prototype")
public class RetrievePasswordAction extends BaseAction {
	
	@Autowired
	private ICompanyUserService companyUserServiceImpl;
	/**
	 * 验证登录
	 */
	@RequestMapping(value = "/sendMail", method = RequestMethod.POST)
	public String sendMail(CompanyLoginQuery companyLoginQuery) throws Exception{
		JSONObject obj = new JSONObject();
		obj.put("status", ErrorConst.STATUS_ERROR);
		try{
			if(StringUtils.isEmpty(companyLoginQuery.getAccount())){
				obj.put("message", CompanyErrorConst.COMPANY_USER_3);
				return null;
			}
			if (StringUtils.isEmpty(companyLoginQuery.getCode())) {
				obj.put("message", CompanyErrorConst.COMPANY_CODE_0);
				return null;
			}
			CompanyUserVO companyUserVO = this.companyUserServiceImpl.findCompanyUserVOByAccount(companyLoginQuery.getAccount(),companyLoginQuery.getCode());
			if(null==companyUserVO){
				obj.put("message", CompanyErrorConst.COMPANY_USER_0);
				return null;
			}
			if(StatusConst.STATUS_FALSE==companyUserVO.getStatus()){
				obj.put("message", CompanyErrorConst.COMPANY_USER_1);
				return null;
			}
			if (!companyUserVO.getId().equals(companyUserVO.getCompany_id())) {
				obj.put("message", CompanyErrorConst.COMPANY_USER_4);
				return null;
			}
			session.removeAttribute(CompanySessionConst.COMPANY_MAIL);
			String randomString = CaptchaUtil.getRandomString();
			session.setAttribute(CompanySessionConst.COMPANY_MAIL,EncryptUtil.encryptToMD5(randomString.toLowerCase()));
			CompanyUpdatePasswordMail companyUpdatePasswordMail = new CompanyUpdatePasswordMail();
			companyUpdatePasswordMail.setAccount(companyLoginQuery.getAccount());
			companyUpdatePasswordMail.setCode(randomString);
			SendMailRunnableUtil.sendMail(companyUpdatePasswordMail, companyUserVO.getMail());
			obj.put("id",companyUserVO.getId());
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
	public String backPassword(CompanyLoginQuery companyLoginQuery) throws Exception{
		JSONObject obj = new JSONObject();
		obj.put("status", ErrorConst.STATUS_ERROR);
		try{
			if(StringUtils.isEmpty(companyLoginQuery.getPassword())){
				obj.put("message", ErrorConst.PASSWORD_1);
				return null;
			}
			Object code = session.getAttribute(CompanySessionConst.COMPANY_MAIL);
			String captcha = companyLoginQuery.getCaptcha();
			if(null!=code&&!StringUtils.isEmpty(code.toString())){
				if (StringUtils.isEmpty(captcha)) {
					obj.put("message", ErrorConst.CAPTCHA_0);
					return null;
				}
				if (!code.toString().equals(EncryptUtil.encryptToMD5(captcha.toLowerCase()))) {
					obj.put("message", ErrorConst.CAPTCHA_1);
					return null;
				}
			}
			CompanyUser companyUser = new CompanyUser();
			companyUser.setId(companyLoginQuery.getId());
			companyUser.setPassword(companyLoginQuery.getPassword());
			boolean flag = this.companyUserServiceImpl.updateCompanyUser(companyUser);
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
