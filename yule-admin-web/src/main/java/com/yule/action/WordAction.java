package com.yule.action;

import java.net.URLEncoder;

import net.sf.json.JSONObject;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.yule.common.BaseAction;
import com.yule.constant.CodeConst;
import com.yule.constant.ErrorConst;
import com.yule.exception.YuleException;
import com.yule.util.WordUtil;

@Controller
@Scope("prototype")
@RequestMapping("/word")
public class WordAction extends BaseAction {
	
	/**
	 * 异步验证登录
	 */
	@RequestMapping(value = "/doFilter", method = RequestMethod.POST)
	public String doFilter(@RequestParam(value="word",required=false)String word) throws Exception{
		JSONObject obj = new JSONObject();
		obj.put("status", ErrorConst.STATUS_ERROR);
		if(StringUtils.isEmpty(word)){
			return null;
		}
		try{
			obj = JSONObject.fromObject(WordUtil.doFilter(URLEncoder.encode(word, CodeConst.UTF_8)));
		} catch(Exception e){
			new YuleException(e);
			throw e;
		} finally{
			outputResult(obj.toString());
		}
		return null;
	}
	
}
