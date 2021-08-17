package com.yule.action.config;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.yule.common.BaseAction;

@Controller
@Scope("prototype")
@RequestMapping("/initAdmin")
public class InitAdmin extends BaseAction{
/*	@RequestMapping(value = "/initAdminRedis",method = RequestMethod.POST)
	public String initAdminRedis()throws Exception{
		JSONObject obj=new JSONObject();
		obj.put("status",ErrorConst.STATUS_ERROR);
		try {
			obj.put("status", true);
			obj.put("message", InitConst.INITADMIN_SUCCESS);
		} catch (Exception e) {
			obj.put("message", InitConst.INITADMIN_ERROR);
			throw e;
		}finally{
			outputResult(obj.toString());
			obj.clear();
			obj=null;
		}
		return null;
	}*/
}
