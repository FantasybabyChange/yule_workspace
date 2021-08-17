package com.yule.action.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.yule.admin.service.IUserService;
import com.yule.admin.vo.UserVO;
import com.yule.common.BaseAction;
import com.yule.constant.ActionReturnConst;
import com.yule.enumerate.LogEnum;
import com.yule.exception.YuleException;
import com.yule.pojo.User;
import com.yule.util.AdminLogUtil;

@Controller
@Scope("prototype")
@RequestMapping("/user")
public class UserAction extends BaseAction{
	
	@Autowired
	private IUserService userServiceImpl;

    /**
     * 更新用户登录
     */
	@RequestMapping(value = "/updateUser", method = RequestMethod.POST)
	public String updateUserLogin(@ModelAttribute("user") User user)throws Exception {
		try {
			userServiceImpl.updateUser(user);
			AdminLogUtil.insertLog("更新用户", getAdminUser(), LogEnum.UPDATE);
		} catch (Exception e) {
			new YuleException("更新用户[updateUser]错误");
			throw e;
		}
		return ActionReturnConst.REDIRECT+"/userLogin/findUserLogin.do";
	}
	
	/**
	 * 根据id获取登录对象
	 */
    @RequestMapping(value="/findUser",method = RequestMethod.GET)
	public String findUser(@RequestParam(value="id",required=false) String id) throws Exception{
    	try {
			UserVO userVO = userServiceImpl.findUserVOById(id);
	        request.setAttribute("htmls", userVO);
	        AdminLogUtil.insertLog("根据id获取用户对象", getAdminUser(), LogEnum.QUERY);
		} catch (Exception e) {
			new YuleException("根据id获取用户对象[findUser]错误", e);
			throw e;
		}
		return "/user/update";
	}
}
