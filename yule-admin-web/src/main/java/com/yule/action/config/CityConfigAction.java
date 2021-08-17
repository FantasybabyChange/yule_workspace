package com.yule.action.config;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.yule.common.BaseAction;

@Controller
@Scope("prototype")
@RequestMapping("/cityConfig")
public class CityConfigAction extends BaseAction{
	
	
	@RequestMapping(value = "/findCity", method = RequestMethod.GET)
	public String findCity()throws Exception{
		
		return "/config/city/index";
	}
}
