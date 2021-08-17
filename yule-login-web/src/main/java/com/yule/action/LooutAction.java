package com.yule.action;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.yule.common.BaseAction;
import com.yule.constant.ActionReturnConst;
import com.yule.constant.CookieTimeConst;
import com.yule.constant.UserCookieConst;
import com.yule.util.CookieUtil;

@Controller
@Scope("prototype")
public class LooutAction extends BaseAction{
	
	/**
	 * 退出
	 */
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(@RequestParam(value="redirect_url",required=false)String redirect_url) throws Exception {
		response.addCookie(CookieUtil.addCookie(UserCookieConst.USER_COOKIE_NAME, null, CookieTimeConst.CLEAN_COOKIE));
		return ActionReturnConst.REDIRECT+redirect_url;
	}
	
	/**
	 * 异步退出
	 */
	@RequestMapping(value = "/ajaxLogout", method = RequestMethod.POST)
	public String ajaxLogout() throws Exception {
		response.addCookie(CookieUtil.addCookie(UserCookieConst.USER_COOKIE_NAME, null, CookieTimeConst.CLEAN_COOKIE));
		return null;
	}
	
}