package com.yule.action;

import net.sf.json.JSONObject;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.yule.common.BaseAction;
import com.yule.constant.HtmlTemplatePathConst;
import com.yule.constant.LoginMemcachedConst;
import com.yule.constant.TimeConst;
import com.yule.constant.UserCookieConst;
import com.yule.memcached.util.MemcachedUtil;
import com.yule.redis.constant.RedisConst;
import com.yule.redis.util.JedisUtil;
import com.yule.util.CookieUtil;
import com.yule.util.FileContentUtil;
import com.yule.util.YuLeEncryptUtil;
import com.yule.vo.UserLoginVO;

@Controller
@Scope("prototype")
public class UserLoginInfoAction extends BaseAction{
    
    @RequestMapping(value = "/findHeader",method = RequestMethod.POST)
	public String phoneCaptcha() throws Exception {
    	StringBuffer htmls = new StringBuffer("");
    	String cookie =  CookieUtil.getCookieValue(request.getCookies(), UserCookieConst.USER_COOKIE_NAME);
    	UserLoginVO userLoginVO = null;
		if(!StringUtils.isEmpty(cookie)){
			String userId = YuLeEncryptUtil.decode(cookie);
			String key = RedisConst.USER + userId;
			userLoginVO = (UserLoginVO)JSONObject.toBean(JSONObject.fromObject(JedisUtil.getInstance().get(key)),UserLoginVO.class);
		}
		
        if(null!=userLoginVO){
        	MemcachedUtil.getInstance().delete(LoginMemcachedConst.HEADER_LOGIN_HTML);
        	if(!MemcachedUtil.getInstance().keyExists(LoginMemcachedConst.HEADER_LOGIN_HTML)){
    			MemcachedUtil.getInstance().set(LoginMemcachedConst.HEADER_LOGIN_HTML, FileContentUtil.readFileByLines(HtmlTemplatePathConst.LOGIN_HTML),TimeConst.TWO_DAY);
        	}
        	String content = MemcachedUtil.getInstance().get(LoginMemcachedConst.HEADER_LOGIN_HTML).toString();
			content = content.replace("#{userLoginVO.name}", userLoginVO.getName());
			htmls.append(content);
        }else{
        	MemcachedUtil.getInstance().delete(LoginMemcachedConst.HEADER_NOT_LOGIN_HTML);
        	if(!MemcachedUtil.getInstance().keyExists(LoginMemcachedConst.HEADER_NOT_LOGIN_HTML)){
    			MemcachedUtil.getInstance().set(LoginMemcachedConst.HEADER_NOT_LOGIN_HTML, FileContentUtil.readFileByLines(HtmlTemplatePathConst.NOT_LOGIN_HTML),TimeConst.TWO_DAY);
        	}
        	htmls.append(MemcachedUtil.getInstance().get(LoginMemcachedConst.HEADER_NOT_LOGIN_HTML).toString());
        }
        request.setAttribute("result", htmls.toString());
        return "return/userLoginInfo";
    }
    
}
