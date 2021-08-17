package com.yule.action.set.interest;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.yule.common.BaseAction;
import com.yule.pojo.UserInterest;
import com.yule.user.service.IUserInterestService;

@Controller
@Scope("prototype")
public class UserInterestAction extends BaseAction{
	
	@Autowired
	private IUserInterestService userInterestServiceImpl;

	@RequestMapping(value = "/updateUserInterest",method = RequestMethod.POST)
	public String updateUserInterest(UserInterest userInterest) throws Exception {
		userInterest.setId(getCookieValue());
		JSONObject obj = new JSONObject();
		boolean flag = userInterestServiceImpl.updateUserInterest(userInterest);
		obj.put("status", flag);
		outputResult(obj.toString());
		return null;
	}
	
}