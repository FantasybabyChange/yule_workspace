package com.yule.action.login;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.yule.common.BaseAction;
import com.yule.constant.ErrorConst;
import com.yule.pojo.UserLogin;
import com.yule.user.service.IUserLoginService;

@Controller
@Scope("prototype")
public class UserLoginAction extends BaseAction{
	
	@Autowired
	private IUserLoginService userLoginServiceImpl;

	@RequestMapping(value = "/updateUserLogin",method = RequestMethod.POST)
	public String updateUserLogin(UserLogin userLogin) throws Exception {
		JSONObject obj = new JSONObject();
		obj.put("status", ErrorConst.STATUS_ERROR);
		String id = getCookieValue();
		userLogin.setId(id);
		boolean flag = userLoginServiceImpl.updateUserLogin(userLogin);
		setUserLoginVO(userLogin);
		obj.put("status", flag);
		outputResult(obj.toString());
		return null;
	}
	
}