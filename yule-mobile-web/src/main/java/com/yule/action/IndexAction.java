package com.yule.action;

import net.sf.json.JSONObject;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.yule.common.BaseAction;
import com.yule.constant.CookieTimeConst;
import com.yule.constant.UserCookieConst;
import com.yule.pojo.AreaCity;
import com.yule.redis.constant.RedisConst;
import com.yule.redis.util.JedisUtil;
import com.yule.util.CookieUtil;
import com.yule.util.MobileSearchCriteriaUtil;

@Controller
@Scope("prototype")
public class IndexAction extends BaseAction{

	@RequestMapping(value = "/index",method = RequestMethod.GET)
	public String index() throws Exception {
		String areaCityId = getAreaCookieValue();	
		if(JedisUtil.getInstance().exists(RedisConst.AREA_CITY_INFO+areaCityId)){
			AreaCity areaCity = (AreaCity) JSONObject.toBean(JSONObject.fromObject(JedisUtil.getInstance().get(RedisConst.AREA_CITY_INFO+areaCityId)),AreaCity.class);
			request.setAttribute("cityId", areaCity.getId());
			request.setAttribute("cityName", areaCity.getName());
		}else{
			request.setAttribute("cityId", "610100");
			request.setAttribute("cityName", "西安");
		}
		return "index";
	}
	
	@RequestMapping(value = "/findSearchCritera",method = RequestMethod.POST)
	public String findSearchCritera() throws Exception {
		JSONObject jsonObject =new JSONObject();
		jsonObject.put("categorySelect", MobileSearchCriteriaUtil.getCompanyCategorySelect());
		jsonObject.put("gradeSelect", MobileSearchCriteriaUtil.getCompanyGradeSelect());
		outputResult(jsonObject.toString());
		jsonObject.clear();
		jsonObject = null;
		return null;
	}
	
	@RequestMapping(value = "/updateArea",method = RequestMethod.POST)
	public String updateArea(@RequestParam(value="areaId",required=false)String areaId) throws Exception {
		response.addCookie(CookieUtil.addCookie(UserCookieConst.USER_AREA_COOKIE_NAME, areaId, CookieTimeConst.CLEAN_COOKIE));
		response.addCookie(CookieUtil.addCookie(UserCookieConst.USER_AREA_COOKIE_NAME, areaId, CookieTimeConst.HALF_HOUR));
		return null;
	}
	
}