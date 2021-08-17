package com.yule.action.config;

import net.sf.json.JSONObject;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.yule.common.BaseAction;
import com.yule.constant.ErrorConst;

@Controller
@Scope("prototype")
@RequestMapping("/initArea")
public class InitArea extends BaseAction {

	@RequestMapping(value = "/initAreaRedis")
	public String initAreaRedis()throws Exception{
		JSONObject obj=new JSONObject();
		obj.put("status",ErrorConst.STATUS_ERROR);
		try {
			com.yule.cached.AreaInit.initArea();
			obj.put("status", true);
			obj.put("message", "初始化区域Redis成功");
		} catch (Exception e) {
			obj.put("message", "初始化区域Redis失败");
			throw e;
		}finally{
			outputResult(obj.toString());
			obj.clear();
			obj=null;
		}
		return null;
	}
}
