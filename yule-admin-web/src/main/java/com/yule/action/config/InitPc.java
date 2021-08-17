package com.yule.action.config;

import net.sf.json.JSONObject;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.yule.common.BaseAction;
import com.yule.constant.ErrorConst;
import com.yule.constant.InitConst;
import com.yule.util.HttpRequestUtil;

@Controller
@Scope("prototype")
@RequestMapping("/initPc")
public class InitPc extends BaseAction{
	
	@RequestMapping(value = "/initIndexHtml",method = RequestMethod.POST)
	public String initUserRedis()throws Exception{
		JSONObject obj=new JSONObject();
		obj.put("status",ErrorConst.STATUS_ERROR);
		try {
			HttpRequestUtil.doGet("http://www.yueling.com/html/createIndexHtml.do");
			obj.put("status",  ErrorConst.STATUS_SUCCESS);
			obj.put("message", InitConst.INITUSER_SUCCESS);
		} catch (Exception e) {
			obj.put("message", InitConst.INITUSER_ERROR);
			throw e;
		}finally{
			outputResult(obj.toString());
			obj.clear();
			obj=null;
		}
		return null;
	}
	
}
