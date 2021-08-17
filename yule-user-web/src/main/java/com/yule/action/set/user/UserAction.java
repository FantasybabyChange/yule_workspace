package com.yule.action.set.user;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.yule.common.BaseAction;
import com.yule.pojo.User;
import com.yule.user.service.IUserService;

@Controller
@Scope("prototype")
public class UserAction extends BaseAction{
	
	@Autowired
	private IUserService userServiceImpl;

	@RequestMapping(value = "/updateUser",method = RequestMethod.POST)
	public String updateUser(User user) throws Exception {
		JSONObject obj = new JSONObject();
		user.setId(getCookieValue());
		boolean flag = userServiceImpl.updateUser(user);
		obj.put("status", flag);
		outputResult(obj.toString());
		return null;
	}
}