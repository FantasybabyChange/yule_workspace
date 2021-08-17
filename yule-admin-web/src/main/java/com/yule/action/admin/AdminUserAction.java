package com.yule.action.admin;

import java.util.List;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.yule.admin.service.IAdminPrivilegeService;
import com.yule.admin.service.IAdminRoleService;
import com.yule.admin.service.IAdminUserService;
import com.yule.admin.vo.AdminRoleVO;
import com.yule.admin.vo.AdminUserVO;
import com.yule.common.BaseAction;
import com.yule.constant.ActionReturnConst;
import com.yule.constant.AdminErrorConst;
import com.yule.constant.AdminRedisConst;
import com.yule.constant.CheckConst;
import com.yule.constant.ErrorConst;
import com.yule.constant.PageConst;
import com.yule.constant.PrivilegeConst;
import com.yule.constant.StatusConst;
import com.yule.enumerate.DateStyle;
import com.yule.enumerate.LogEnum;
import com.yule.exception.YuleException;
import com.yule.pojo.AdminPrivilege;
import com.yule.pojo.AdminRole;
import com.yule.pojo.AdminUser;
import com.yule.redis.util.JedisUtil;
import com.yule.util.AdminLogUtil;
import com.yule.util.DateUtil;
import com.yule.util.EncryptUtil;
import com.yule.util.PaginationUtil;
import com.yule.util.StatusUtil;
import com.yule.vo.Page;

@Controller
@Scope("prototype")
@RequestMapping("/adminUser")
public class AdminUserAction extends BaseAction {
	
	@Autowired
	private IAdminUserService adminUserServiceImpl;

	@Autowired
	private IAdminPrivilegeService adminPrivilegeServiceImpl;

	@Autowired
	private IAdminRoleService adminRoleServiceImpl;

	/**
	 * 初始化页面 获取用户分页信息列表 和 分页
	 * @Title: findAdminUser
	 * @Description: TODO
	 * @param pageNo
	 * @return
	 * @throws Exception
	 * @return: String
	 */
	@RequestMapping(value = "/findAdminUser",method = RequestMethod.GET)
	public String findAdminUserList(@RequestParam(value = "pageNo", required = false) Integer pageNo) throws Exception {
		if(null==pageNo||pageNo<1){
			pageNo = 1;
		}
		try {
			Page<AdminUser> page = adminUserServiceImpl.findAdminUserPage(PageConst.PAGE_SIZE_TEN, pageNo);
			StringBuffer htmls = new StringBuffer();
			htmls.append("<tfoot><tr><td colspan=\"7\">");
			htmls.append("<div class=\"bulk-actions align-left\"></div>");
			htmls.append(PaginationUtil.getPaginationHtml(page));
			htmls.append("<div class=\"clear\"></div>");
			htmls.append("</td></tr></tfoot>");
			htmls.append("<tbody>");
			if(page.getRowCount()>0){
				AdminPrivilege adminPrivilege = null;
				for (AdminUser adminUser : page.getDatas()) {
					htmls.append("<tr>");
					htmls.append("<td>" + adminUser.getAccount() + "</td>");
					htmls.append("<td>" + DateUtil.DateToString(adminUser.getLogin_time(), DateStyle.YYYY_MM_DD_HH_MM_SS_EN) + "</td>");
					htmls.append("<td>" +StatusConst.STATUS[adminUser.getStatus()]  + "</td>");
					htmls.append("<td>");
					if(getPrivilege().containsKey(PrivilegeConst.ADMIN_PRIVILEGE_CODE_UPDATE)){
						adminPrivilege = getPrivilege().get(PrivilegeConst.ADMIN_PRIVILEGE_CODE_UPDATE);
					    htmls.append("<a class=\"button\" href=\""+adminPrivilege.getUrl()+"?id="+ adminUser.getId()+ "\">"+adminPrivilege.getName()+"</a>&nbsp;");
					}
//					if(getPrivilege().containsKey(PrivilegeConst.ADMIN_PRIVILEGE_CODE_DELETE)){
//					    htmls.append("<a class=\"button\" href=\"/adminUser/deleteAdminUser.do?id=" + adminUser.getId()+ "\" >刪除</a>&nbsp;&nbsp; ");
//					}
					if(getPrivilege().containsKey(PrivilegeConst.ADMIN_PRIVILEGE_CODE_ROLE_CONFIG)){
						adminPrivilege = getPrivilege().get(PrivilegeConst.ADMIN_PRIVILEGE_CODE_ROLE_CONFIG);
					    htmls.append("<a class=\"button\" href=\""+adminPrivilege.getUrl()+"?id="+ adminUser.getId()+ "\" >"+adminPrivilege.getName()+"</a>");
					}
					htmls.append("</td></tr>");
				}
				page.getDatas().clear();
				page = null;
			}else{
				htmls.append("<tr>");
				htmls.append("<td class=\"td-center-style\" colspan=\"8\">暂无数据</td>");
				htmls.append("</tr>");
			}
			htmls.append("</tbody>");
		    StringBuffer roleHtmls = new StringBuffer(""); 
		    List<AdminRole> adminRoles = adminRoleServiceImpl.findAdminRoleList();
			if(null != adminRoles && adminRoles.size()>0){
				int i=0;
				for (AdminRole adminRole : adminRoles) {
					if(i==0){
						roleHtmls.append("<input type=\"radio\" name=\"admin_role_id\"  value=\""+ adminRole.getId()+ "\" nullmsg=\"请选择角色\" datatype=\"\" />"+ adminRole.getName()+ "&nbsp;");
					}else{
						roleHtmls.append("<input type=\"radio\" name=\"admin_role_id\"  value=\""+ adminRole.getId()+ "\" />"+ adminRole.getName()+ "&nbsp;");
					}
					i++;
				}
				adminRoles.clear();
				adminRoles=null;
			}
			request.setAttribute("statusHtml", StatusUtil.getStatusHtml());
			request.setAttribute("htmls", htmls);
			request.setAttribute("roleHtmls", roleHtmls);
			AdminLogUtil.insertLog("查看用户", getAdminUser(), LogEnum.QUERY);
		} catch (Exception e) {
			new YuleException("初始化页面 获取用户分页信息列表 和 分页【getInitAdminUserInfo】:发生错误!",e);
			throw e;
		} 
		return "admin/user/index";
	}
	
	/**
	 * 新增用户
	 */
	@RequestMapping(value = "/insertAdminUser", method = RequestMethod.POST)
	public String insertAdminUser(@ModelAttribute("adminUsers") AdminUser adminUsers)throws Exception {
		try {
			adminUserServiceImpl.insertAdminUser(adminUsers);
			AdminLogUtil.insertLog("新增用户", getAdminUser(), LogEnum.INSERT);
		} catch (Exception e) {
			new YuleException("用户注册【insertAdminUser】:发生错误!",e);
			throw e;
		} 
		return ActionReturnConst.REDIRECT+"/adminUser/findAdminUser.do";
	}
	
	/**
	 * 验证新增用户
	 */
	@RequestMapping(value = "/verifyAdminUser", method = RequestMethod.POST)
	public String verifyAdminUser( @RequestParam(value = "account", required = false)String account)throws Exception {
		JSONObject obj = new JSONObject();
		obj.put("status",ErrorConst.STATUS_ERROR);
		try {
			int num = adminUserServiceImpl.findAdminUserCountByAccount(account);
			obj.put("name", "account");
			if(num>0){
				obj.put("message",AdminErrorConst.ADMIN_USER_2);
				obj.put("status",ErrorConst.STATUS_ERROR);
			}else{
				obj.put("status",ErrorConst.STATUS_SUCCESS);
			}
		} catch (Exception e) {
			new YuleException("用户注册【insertAdminUser】:发生错误!",e);
			throw e;
		} finally{
			outputResult(obj.toString());
		}
		return null;
	}
	
	/**
	 * 删除用户信息
	 */
	@RequestMapping(value = "/deleteAdminUser",method = RequestMethod.POST)
	public String deleteAdminUser(@RequestParam(value="id",required=false) String id)
			throws Exception {
		try {
			adminUserServiceImpl.deleteAdminUserById(id);
			JedisUtil.getInstance().del(AdminRedisConst.ADMIN_PRIVILEGE+id);
			JedisUtil.getInstance().del(AdminRedisConst.ADMIN_USER+id);
			AdminLogUtil.insertLog("删除用户", getAdminUser(), LogEnum.DELETE);
		} catch (Exception e) {
			new YuleException("删除用户信息【getInitAdminUserInfo】:发生错误!",e);
			throw e;
		}
		return ActionReturnConst.REDIRECT+"/adminUser/findAdminUser.do";
	}

	/**
	 * 根据用户id获取用户及用户角色信息
	 */
	@RequestMapping(value = "/findAdminUserById",method = RequestMethod.GET)
	public String findAdminUserById(@RequestParam(value="id",required=false) String id)
			throws Exception {
		try {
			AdminUser adminUser = adminUserServiceImpl.findAdminUserById(id);
			request.setAttribute("statusHtml", StatusUtil.getStatusHtml());
			request.setAttribute("adminUser", adminUser);
		} catch (Exception e) {
			new YuleException("根据用户id获取用户【findAdminUserById】:发生错误!",e);
			throw e;
		} 
		return "admin/user/updateAdminUser";
	}
	
	/**
	 * 根据用户id获取用户角色
	 */
	@RequestMapping(value = "/findAdminUserRole",method = RequestMethod.GET)
	public String findAdminUserRoleById(@RequestParam(value="id",required=false) String id) throws Exception {
		try {
			StringBuffer htmls = new StringBuffer();
			List<AdminRoleVO> adminRoleVOs = adminRoleServiceImpl.findAdminRoleVOByAdminUserId(id);
			htmls.append("<input type=\"hidden\" name=\"id\" value=\""+ id + "\"  />");
			if(null != adminRoleVOs && adminRoleVOs.size()>0){
				htmls.append("<ul class=\"tab-content-4ul\">");
				for (AdminRoleVO adminRoleVO : adminRoleVOs) {
					if(CheckConst.IS_CHECK_TRUE == adminRoleVO.getIs_check()){
						htmls.append("<input type=\"radio\" name=\"admin_role_id\"  checked=\"checked\"  value=\""+ adminRoleVO.getId()+ "\" />"+ adminRoleVO.getName()+ " ");
					}else{
						htmls.append("<input type=\"radio\" name=\"admin_role_id\"  value=\""+ adminRoleVO.getId()+ "\" />"+ adminRoleVO.getName()+ "");
					}
				}
				adminRoleVOs.clear();
				adminRoleVOs=null;
				htmls.append("</ul>");
			}
			htmls.append("<input class=\"button\" type=\"submit\" value=\"更新\" />");
			request.setAttribute("htmls", htmls);
		} catch (Exception e) {
			new YuleException("根据用户id获取用户角色【findAdminUserRoleById】:发生错误!",e);
			throw e;
		} 
		return "admin/user/userRoleConfig";
	}

	/**
	 * 修改用户信息
	 */
	@RequestMapping(value = "/updateAdminUser",method = RequestMethod.POST)
	public String updateAdminUser(@ModelAttribute("adminUsers") AdminUser adminUsers) throws Exception {
		if(null != adminUsers.getPassword() && !adminUsers.getPassword().equals("")){
			adminUsers.setPassword(EncryptUtil.encryptToMD5(adminUsers.getPassword()));
		}
		try {
			adminUserServiceImpl.updateAdminUser(adminUsers);
			AdminLogUtil.insertLog("更新用户", getAdminUser(), LogEnum.UPDATE);
		} catch (Exception e) {
			new YuleException("修改用户信息【updateAdminUser】:发生错误!",e);
			throw e;
		} 
		return ActionReturnConst.REDIRECT+"/adminUser/findAdminUser.do";
	}
	
	@RequestMapping(value = "/findAdminUserPassword",method = RequestMethod.GET)
	public String AdminUserPassword()throws Exception {
		return "admin/user/updateAdminUserPassword";
	}
	
	/**
	 * 修改登陆用户的密码
	 */
	@RequestMapping(value = "/updateAdminUserPassword",method = RequestMethod.POST)
	public String updateAdminUserPassword(@RequestParam(value="password",required=false) String password,@RequestParam(value="newPassword",required=false) String newPassword) throws Exception {
		try {
		    AdminUserVO adminUserVO = this.getAdminUser();
		    if(!adminUserVO.getPassword().equals(EncryptUtil.encryptToMD5(password))){
		    	//log.info("密码错误!");
		    	return "error";
		    }
		    AdminUser adminUser = new AdminUser();
		    adminUser.setId(adminUserVO.getId());
		    adminUser.setPassword(EncryptUtil.encryptToMD5(newPassword));
		    adminUserServiceImpl.updateAdminUser(adminUser);
		    AdminLogUtil.insertLog("更新用户密码", getAdminUser(), LogEnum.UPDATE);
		} catch (Exception e) {
			new YuleException("修改登陆用户的密码【updateAdminUserPassword】:发生错误!",e);
			throw e;
		} 
		return ActionReturnConst.REDIRECT+"/adminUser/findAdminUserPassword.do";
	}
	
}
