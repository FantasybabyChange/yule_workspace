package com.yule.action;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.yule.common.BaseAction;
import com.yule.constant.CookieTimeConst;
import com.yule.constant.UserCookieConst;
import com.yule.util.CookieUtil;

@Controller
@Scope("prototype")
public class IndexAction extends BaseAction{

	@RequestMapping(value = "/index",method = RequestMethod.GET)
	public String index() throws Exception {
		String areaProvinceId = getAreaCookieValue();	
		areaProvinceId = areaProvinceId.substring(0,3)+"000";
		return "forward:/html/"+areaProvinceId+".html";
	}
	
	@RequestMapping(value = "/updateArea",method = RequestMethod.POST)
	public String updateArea(@RequestParam(value="areaId",required=false)String areaId) throws Exception {
		response.addCookie(CookieUtil.addCookie(UserCookieConst.USER_AREA_COOKIE_NAME, areaId, CookieTimeConst.CLEAN_COOKIE));
		response.addCookie(CookieUtil.addCookie(UserCookieConst.USER_AREA_COOKIE_NAME, areaId, CookieTimeConst.HALF_HOUR));
		return null;
	}
	
}