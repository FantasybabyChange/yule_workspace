package com.yule.action.user;



import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.yule.admin.query.UserLoginQuery;
import com.yule.admin.service.IUserLoginService;
import com.yule.common.BaseAction;
import com.yule.constant.ActionReturnConst;
import com.yule.constant.AuthConst;
import com.yule.constant.DeleteConst;
import com.yule.constant.ErrorConst;
import com.yule.constant.PageConst;
import com.yule.constant.PrivilegeConst;
import com.yule.constant.StatusConst;
import com.yule.enumerate.DateStyle;
import com.yule.enumerate.LogEnum;
import com.yule.exception.YuleException;
import com.yule.pojo.AdminPrivilege;
import com.yule.pojo.UserLogin;
import com.yule.util.AdminLogUtil;
import com.yule.util.DateUtil;
import com.yule.util.PaginationUtil;
import com.yule.vo.Page;

@Controller
@Scope("prototype")
@RequestMapping("/userLogin")
public class UserLoginAction extends BaseAction{
	
	@Autowired
	private IUserLoginService userLoginServiceImpl;
	
	/**
	 * 查询用户登录
	 */
	@RequestMapping(value = "/findUserLogin",method = RequestMethod.GET)
	public String findUserLogin(UserLoginQuery userLoginQuery,@RequestParam(value = "pageNo", required = false) Integer pageNo)throws Exception {
		if (pageNo==null||pageNo<1) {
			pageNo=1;
		}
		try {
			StringBuffer htmls = new StringBuffer();
			Page<UserLogin> page = userLoginServiceImpl.findUserLoginPage(userLoginQuery,PageConst.PAGE_SIZE_TEN, pageNo);
			htmls.append("<tfoot><tr><td colspan=\"8\">");
			htmls.append("<div class=\"bulk-actions align-left\"></div>");
			htmls.append(PaginationUtil.getPaginationHtml(page));
			htmls.append("<div class=\"clear\"></div>");
			htmls.append("</td></tr></tfoot>");
			htmls.append("<tbody>");
			if(page.getRowCount()>0){
				StringBuffer id = new StringBuffer();
				AdminPrivilege adminPrivilege = null;
				for (UserLogin userLogin: page.getDatas()) {
					id.append(userLogin.getId());
					htmls.append("<tr>");
					htmls.append("<td>" + userLogin.getName() + "</td>");
					htmls.append("<td>" + userLogin.getPhone() + "</td>");
					htmls.append("<td>" + AuthConst.AUTH_STATUS[userLogin.getPhone_auth()] + "</td>");
					htmls.append("<td>" + userLogin.getMail() + "</td>");
					htmls.append("<td>" + AuthConst.AUTH_STATUS[userLogin.getMail_auth()] + "</td>");
					htmls.append("<td class=\"is_delete\">" + DeleteConst.IS_DELETE[userLogin.getIs_delete()] + "</td>");
					htmls.append("<td>" + DateUtil.DateToString(userLogin.getLogin_time(), DateStyle.YYYY_MM_DD_HH_MM_SS_EN) + "</td>");
					htmls.append("<td>" + DateUtil.DateToString(userLogin.getCreate_time(), DateStyle.YYYY_MM_DD_HH_MM_SS_EN) + "</td>");
					htmls.append("<td>");
					if(getPrivilege().containsKey(PrivilegeConst.ADMIN_PRIVILEGE_CODE_UPDATE)){				
						adminPrivilege = getPrivilege().get(PrivilegeConst.ADMIN_PRIVILEGE_CODE_UPDATE);
						htmls.append("<a class=\"button\" href=\""+adminPrivilege.getUrl()+"?id="+id + "\" >"+adminPrivilege.getName()+"</a>&nbsp;");
						adminPrivilege = null;
					}
					if(getPrivilege().containsKey(PrivilegeConst.ADMIN_PRIVILEGE_CODE_DELETE)){
						adminPrivilege = getPrivilege().get(PrivilegeConst.ADMIN_PRIVILEGE_CODE_DELETE);
						htmls.append("<a class=\"button\" data-name=\"is_delete\" href=\"javascript:;\"  name=\"is_delete\" data-url=\""+adminPrivilege.getUrl()+"\" data-id=\""+userLogin.getId()+"\" data-status=\""+DeleteConst.BUTTON_DELETE_VALUE[userLogin.getIs_delete()]+"\" />"+DeleteConst.BUTTON_DELETE[userLogin.getIs_delete()]+"</a>&nbsp;");
						adminPrivilege = null;
					}
					if(getPrivilege().containsKey(PrivilegeConst.ADMIN_PRIVILEGE_CODE_UPDATE_STATUS)){
						adminPrivilege = getPrivilege().get(PrivilegeConst.ADMIN_PRIVILEGE_CODE_UPDATE_STATUS);
						htmls.append("<a class=\"button\" data-name=\"status\" href=\"javascript:;\"  name=\"status\" data-url=\""+adminPrivilege.getUrl()+"\" data-id=\""+userLogin.getId()+"\" data-status=\""+StatusConst.BUTTON_STATUS_VALUE[userLogin.getStatus()]+"\" />"+StatusConst.BUTTON_STATUS[userLogin.getStatus()]+"</a>&nbsp;");
						adminPrivilege = null;
					}
					if(getPrivilege().containsKey(PrivilegeConst.ADMIN_PRIVILEGE_CODE_DETAILS)){
						adminPrivilege = getPrivilege().get(PrivilegeConst.ADMIN_PRIVILEGE_CODE_DETAILS);
						htmls.append("<a class=\"button\" href=\""+adminPrivilege.getUrl()+"?id="+id + "\" >"+adminPrivilege.getName()+"</a>&nbsp;");
						adminPrivilege = null;
					}
					if(getPrivilege().containsKey(PrivilegeConst.ADMIN_PRIVILEGE_CODE_USER_COLLECTIONS)){
						adminPrivilege = getPrivilege().get(PrivilegeConst.ADMIN_PRIVILEGE_CODE_USER_COLLECTIONS);
						htmls.append("<a class=\"button\" href=\""+adminPrivilege.getUrl()+"?userId="+id + "\" >"+adminPrivilege.getName()+"</a>&nbsp;");
						adminPrivilege = null;
					}
					htmls.append("</td></tr>");
					id.setLength(0);
				}
				id = null;
				page.cleanDatas();
				page = null;
			}else{
				htmls.append("<tr>");
				htmls.append("<td class=\"td-center-style\" colspan=\"8\">暂无数据</td>");
				htmls.append("</tr>");
			}
			htmls.append("</tbody>");
			request.setAttribute("htmls", htmls);
			request.setAttribute("userLoginQuery", userLoginQuery);
			userLoginQuery = null;
			htmls = null;
			AdminLogUtil.insertLog("查看用户登录", getAdminUser(), LogEnum.QUERY);
		} catch (Exception e) {
			new YuleException("查看用户登录[findUserLogin]错误");
			throw e;
		}
		return "/user/login/index";
	}
	
	
	/**
	 * 根据id获取用户登录对象
	 */
    @RequestMapping(value="/findUserLoginById",method = RequestMethod.GET)
	public String findUserLoginById(@RequestParam(value="id",required=false) String id) throws Exception{
    	try {
			UserLogin userLogin = userLoginServiceImpl.findUserLoginById(id);
	        request.setAttribute("htmls", userLogin);
	        AdminLogUtil.insertLog("根据id获取用户登录对象", getAdminUser(), LogEnum.QUERY);
		} catch (Exception e) {
			new YuleException("根据id获取用户登录对象发生[findUserLoginById]错误", e);
			throw e;
		}
		return "/user/login/update";
	}
    
    /**
     * 更新用户登录
     */
	@RequestMapping(value = "/updateUserLogin", method = RequestMethod.POST)
	public String updateUserLogin(@ModelAttribute("userLogin") UserLogin userLogin)throws Exception {
		try {
			userLoginServiceImpl.updateUserLogin(userLogin);
			AdminLogUtil.insertLog("更新用户登录", getAdminUser(), LogEnum.UPDATE);
		} catch (Exception e) {
			new YuleException("更新用户登录[updateUserLogin]错误");
			throw e;
		}
		return ActionReturnConst.REDIRECT+"/userLogin/findUserLogin.do";
	}
	
	/**
	 * 更新用户登录状态
	 */
	@RequestMapping(value = "/updateUserLoginStatus",method = RequestMethod.POST)
	public String updateUserLoginStatus(@ModelAttribute("companyLogin")UserLogin userLogin) throws Exception {
		JSONObject obj=new JSONObject();
		obj.put("status",ErrorConst.STATUS_ERROR);
		try{
			boolean flag = this.userLoginServiceImpl.updateUserLogin(userLogin);
			int status = userLogin.getStatus();
			obj.put("status", flag);
			obj.put("value",StatusConst.BUTTON_STATUS_VALUE[status]);
			obj.put("text",StatusConst.BUTTON_STATUS[status]);
			AdminLogUtil.insertLog("更新用户登录状态", getAdminUser(), LogEnum.UPDATE);
		}catch(Exception e){
			new YuleException("更新用户登录状态[updateUserLoginStatus]错误",e);
			throw e;
		} finally{
			outputResult(obj.toString());
		}
		return null;
	}

	
	/**
	 * 删除用户登录
	 */
	@RequestMapping(value = "/deleteUserLogin",method = RequestMethod.POST)
	public String deleteUserLogin(@ModelAttribute("companyLogin")UserLogin userLogin) throws Exception {
		JSONObject obj=new JSONObject();
		obj.put("status",ErrorConst.STATUS_ERROR);
		try{
			boolean flag = this.userLoginServiceImpl.deleteUserLoginById(userLogin);
			int status = userLogin.getIs_delete();
			obj.put("status", flag);
			obj.put("value",DeleteConst.BUTTON_DELETE_VALUE[status]);
			obj.put("is_delete_text",DeleteConst.IS_DELETE[status]);
			obj.put("text",DeleteConst.BUTTON_DELETE[status]);
			AdminLogUtil.insertLog("删除用户登录", getAdminUser(), LogEnum.DELETE);
		} catch(Exception e){
			new YuleException("删除用户登录[deleteUserLogin]错误",e);
			throw e;
		} finally{
			outputResult(obj.toString());
		}
		return null;
	}
	

}
