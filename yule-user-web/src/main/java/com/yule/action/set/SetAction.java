package com.yule.action.set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.yule.common.BaseAction;
import com.yule.constant.UserConst;
import com.yule.enumerate.DateStyle;
import com.yule.user.service.IUserInterestService;
import com.yule.user.service.IUserService;
import com.yule.user.vo.UserInterestVO;
import com.yule.user.vo.UserVO;
import com.yule.util.DateUtil;

@Controller
@Scope("prototype")
public class SetAction extends BaseAction{
	
	@Autowired
	private IUserService userServiceImpl;
	
	@Autowired
	private IUserInterestService userInterestServiceImpl;

	@RequestMapping(value = "/set",method = RequestMethod.GET)
	public String set() throws Exception {
		String userId = getCookieValue();
		UserVO userVO = userServiceImpl.findUserVOById(userId);
		UserInterestVO userInterestVO = userInterestServiceImpl.findUserInterestVOById(userId);
		request.setAttribute("birthday", DateUtil.DateToString(userVO.getBirthday(), DateStyle.YYYY_MM_DD_CN));
		request.setAttribute("sex", UserConst.SEXS[userVO.getSex()]);
		request.setAttribute("user", userVO);
		request.setAttribute("userInterest", userInterestVO);
		request.setAttribute("userLoginVO", getUserLoginVO());
		return "set";
	}
	
}