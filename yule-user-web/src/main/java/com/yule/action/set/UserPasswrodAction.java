package com.yule.action.set;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.yule.common.BaseAction;
import com.yule.constant.ErrorConst;
import com.yule.exception.YuleException;
import com.yule.pojo.UserLogin;
import com.yule.redis.constant.RedisConst;
import com.yule.redis.util.JedisUtil;
import com.yule.user.service.IUserLoginService;
import com.yule.util.BeanUtils;
import com.yule.util.EncryptUtil;
import com.yule.vo.UserLoginVO;

@Controller
@Scope("prototype")
public class UserPasswrodAction extends BaseAction{
	private final String returnName = "return/resetPassword";
	@Autowired
	private IUserLoginService userLoginServiceImpl;

	@RequestMapping(value = "/updatePassword",method = RequestMethod.POST)
	public String findScore(@RequestParam(value="password",required=false)String password,@RequestParam(value="newPassword",required=false)String newPassword) throws Exception {
		JSONObject obj = new JSONObject();
		obj.put("status", ErrorConst.STATUS_ERROR);
		try{
			if(StringUtils.isEmpty(password)){
				obj.put("message", ErrorConst.PASSWORD_1);
				return null;
			}
			if(StringUtils.isEmpty(newPassword)){
				obj.put("message", ErrorConst.NEW_PASSWORD_0);
				return null;
			}
			String id = getCookieValue();
			String key = RedisConst.USER + id;
			UserLoginVO userLoginVO = (UserLoginVO)JSONObject.toBean(JSONObject.fromObject(JedisUtil.getInstance().get(key)),UserLoginVO.class);
			if(!EncryptUtil.encryptToMD5(password).equals(userLoginVO.getPassword())){
				obj.put("message", ErrorConst.PASSWORD_0);
				return null;
			}
			UserLogin userLogin = new UserLogin();
			userLogin.setId(id);
			userLogin.setPassword(newPassword);
			boolean flag = userLoginServiceImpl.updateUserLogin(userLogin);
			if(!flag){
				obj.put("message", "发生错误");
			}
			BeanUtils.copyProperties(userLogin, userLoginVO);
			JedisUtil.getInstance().set(key, JSONObject.fromObject(userLoginVO).toString());
			obj.put("status", ErrorConst.STATUS_SUCCESS);
		} catch(Exception e){
			new YuleException(e);
			throw e;
		} finally{
			outputResult(obj.toString());
		}
		return null;
	}
	/**
	 * 从form提交的修改密码
	 */
	@RequestMapping(value = "/updatePasswordForm", method = RequestMethod.POST)
	public String register(@RequestParam(value="password",required=false)String password,@RequestParam(value="newPassword",required=false)String newPassword) throws Exception {
		JSONObject 	obj = new JSONObject();
		obj.put("status", ErrorConst.STATUS_ERROR);
		try {
			if(StringUtils.isEmpty(password)){
				obj.put("message", ErrorConst.PASSWORD_1);
				return returnName;
			}
			if(StringUtils.isEmpty(newPassword)){
				obj.put("message", ErrorConst.NEW_PASSWORD_0);
				return returnName;
			}
			String id = getCookieValue();
			String key = RedisConst.USER + id;
			UserLoginVO userLoginVO = (UserLoginVO)JSONObject.toBean(JSONObject.fromObject(JedisUtil.getInstance().get(key)),UserLoginVO.class);
			if(!EncryptUtil.encryptToMD5(password).equals(userLoginVO.getPassword())){
				obj.put("message", ErrorConst.PASSWORD_0);
				return returnName;
			}
			UserLogin userLogin = new UserLogin();
			userLogin.setId(id);
			userLogin.setPassword(newPassword);
			boolean flag = userLoginServiceImpl.updateUserLogin(userLogin);
			if(!flag){
				obj.put("message", "发生错误");
			}
			BeanUtils.copyProperties(userLogin, userLoginVO);
			JedisUtil.getInstance().set(key, JSONObject.fromObject(userLoginVO).toString());
			obj.put("status", ErrorConst.STATUS_SUCCESS);
		} catch (Exception e) {
			new YuleException(e);
			throw e;
		} finally{
			request.setAttribute("result", obj.toString());
		}
		return returnName;
	}
}