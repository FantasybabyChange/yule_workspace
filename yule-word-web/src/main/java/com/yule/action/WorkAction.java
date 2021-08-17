package com.yule.action;

import java.net.URLDecoder;

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
public class WorkAction extends BaseAction {
	
	@RequestMapping(value = "/doFilter", method = RequestMethod.POST)
	public String doFilter(@RequestParam(value="word",required=false)String word) throws Exception {
		JSONObject obj = new JSONObject();
		obj.put("status", ErrorConst.STATUS_ERROR);
		if(StringUtils.isEmpty(word)){
			return null;
		}
		try{
			String str = WordUtil.doFilter(URLDecoder.decode(word, CodeConst.UTF_8));
			if(StringUtils.isEmpty(str)){
				obj.put("status", ErrorConst.STATUS_SUCCESS);
			}else{
				obj.put("word", str);
			}
		}catch(Exception e){
			new YuleException(e);
			e.printStackTrace();
		} finally{
			outputResult(obj.toString());
		}
		return null;
	}
	
}