package com.yule.action.admin;

import java.util.List;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.yule.admin.param.InsertAdminRoleParam;
import com.yule.admin.param.UpdateAdminRolePrivilegeParam;
import com.yule.admin.service.IAdminPrivilegeService;
import com.yule.admin.service.IAdminRoleService;
import com.yule.admin.service.IAdminUserService;
import com.yule.admin.vo.AdminPrivilegeVO;
import com.yule.common.BaseAction;
import com.yule.constant.ActionReturnConst;
import com.yule.constant.AdminConst;
import com.yule.constant.CheckConst;
import com.yule.constant.ErrorConst;
import com.yule.constant.PlaceholderConst;
import com.yule.constant.PrivilegeConst;
import com.yule.enumerate.LogEnum;
import com.yule.exception.YuleException;
import com.yule.pojo.AdminPrivilege;
import com.yule.pojo.AdminRole;
import com.yule.util.AdminLogUtil;
import com.yule.util.PrivilegeUtil;

@Controller
@Scope("prototype")
@RequestMapping("/adminRole")
public class AdminRoleAction extends BaseAction {
	
	@Autowired
	private IAdminRoleService adminRoleServiceImpl;
	
	@Autowired
	private IAdminPrivilegeService adminPrivilegeServiceImpl;
	
	@Autowired
	private IAdminUserService  adminUserServiceImpl;
	
	/** 
	 * 初始化页面 获取状态 分页 信息
	 */
	@RequestMapping(value = "/findAdminRole",method = RequestMethod.GET)
	public String findAdminRoleList()throws Exception {
		try {
			List<AdminRole>	lists = adminRoleServiceImpl.findAdminRoleList();
			AdminPrivilege adminPrivilege = null;
			StringBuffer operatorHtml = new StringBuffer("");
			if(getPrivilege().containsKey(PrivilegeConst.ADMIN_PRIVILEGE_CODE_INSERT)){
				operatorHtml.append("<a class=\"button\" href=\"javascript:;\" data-add-row=\"\" />新增一行</a>&nbsp;");
			}
			if(getPrivilege().containsKey(PrivilegeConst.ADMIN_PRIVILEGE_CODE_BATCH_UPDATE)){
				adminPrivilege = getPrivilege().get(PrivilegeConst.ADMIN_PRIVILEGE_CODE_BATCH_UPDATE);
				operatorHtml.append("<input class=\"button\" type=\"submit\" value=\""+adminPrivilege.getName()+"\">");
				adminPrivilege = null;
			}
			StringBuffer privilegeHtml = new StringBuffer("");
			if(getPrivilege().containsKey(PrivilegeConst.ADMIN_PRIVILEGE_CODE_PRIVILEGE_CONFIG)){		
				adminPrivilege = getPrivilege().get(PrivilegeConst.ADMIN_PRIVILEGE_CODE_PRIVILEGE_CONFIG);
				privilegeHtml.append("<a  class=\"button\" href=\""+adminPrivilege.getUrl()+"?id="+PlaceholderConst.ID + "\" >"+adminPrivilege.getName()+"</a>&nbsp;");
			}
			if(getPrivilege().containsKey(PrivilegeConst.ADMIN_PRIVILEGE_CODE_UPDATE)){				
				adminPrivilege = getPrivilege().get(PrivilegeConst.ADMIN_PRIVILEGE_CODE_UPDATE);
				privilegeHtml.append("<a  class=\"button\" href=\"javascript:;\" data-update=\"\" data-url=\""+adminPrivilege.getUrl()+"\" >"+adminPrivilege.getName()+"</a>&nbsp;");
			}
			if(getPrivilege().containsKey(PrivilegeConst.ADMIN_PRIVILEGE_CODE_UPDATE)){				
				adminPrivilege = getPrivilege().get(PrivilegeConst.ADMIN_PRIVILEGE_CODE_DELETE);
				privilegeHtml.append("<a  class=\"button\" href=\"javascript:;\" data-delete=\"\" data-id=\""+PlaceholderConst.ID+"\" data-url=\""+adminPrivilege.getUrl()+"\" >"+adminPrivilege.getName()+"</a>");
			}
			StringBuffer rowHtml = new StringBuffer("");
			rowHtml.append("<td><input id=\"id\" type=\"hidden\" name=\"id\" value=\""+PlaceholderConst.ID+"\"  />");
			rowHtml.append("<input id=\"name\" class=\"text-input\" type=\"text\"  name=\"name\"  nullmsg=\"请输入名称!\" datatype=\"\" errormsg=\"\" repeatmsg=\"名称重复!\"  value=\""+PlaceholderConst.NAME+"\"/></td>");
			
			StringBuffer htmls = new StringBuffer();
			if(null!=lists&&lists.size()>0){
				StringBuffer id = new StringBuffer("");
				for (AdminRole adminRole : lists) {
					id.append(adminRole.getId());
					htmls.append("<tr>");
					htmls.append(rowHtml.toString().replace(PlaceholderConst.ID, id).replace(PlaceholderConst.NAME, adminRole.getName()));
					htmls.append("<td>"+privilegeHtml.toString().replace(PlaceholderConst.ID, id)+"</td>");
					htmls.append("</tr>");
					id.setLength(0);
				}
				lists.clear();
				lists = null;
			}else{
				htmls.append("<tr>");
				htmls.append("<td class=\"td-center-style\" colspan=\"5\">暂无数据</td>");
				htmls.append("</tr>");
			}
			htmls.append("</tbody>");
			rowHtml.append("<td>");
			if(getPrivilege().containsKey(PrivilegeConst.ADMIN_PRIVILEGE_CODE_INSERT)){
				adminPrivilege = getPrivilege().get(PrivilegeConst.ADMIN_PRIVILEGE_CODE_INSERT);
				rowHtml.append("<a class=\"button\" href=\"javascript:;\" data-insert=\"\" data-url=\""+adminPrivilege.getUrl()+"\">"+adminPrivilege.getName()+"</a>&nbsp;");
				adminPrivilege = null;
			}
			rowHtml.append("<a class=\"button\" href=\"javascript:;\" data-del-row=\"\" >删除</a>");
			rowHtml.append("</td>");
			request.setAttribute("rowHtml", rowHtml.toString().replace(PlaceholderConst.ID, "").replace(PlaceholderConst.NAME, ""));
			request.setAttribute("privilegeHtml", privilegeHtml);
			request.setAttribute("operatorHtml", operatorHtml);
			request.setAttribute("htmls", htmls);
			AdminLogUtil.insertLog("查看用户角色", getAdminUser(), LogEnum.QUERY);
		} catch (Exception e) {
			new YuleException("初始化页面 获取状态 分页 信息【findAdminRoleInfo】:发生错误!",e);
			throw e;
		}
		return "admin/role/index";
	}
	
	@RequestMapping(value = "/batchUpdateAdminRole",method = RequestMethod.POST)
	public String batchInsertCompanyAdminRole(InsertAdminRoleParam insertAdminRoleParam) throws Exception {
		try {
			this.adminRoleServiceImpl.batchInsertAndUpdateAdminRole(insertAdminRoleParam);
			AdminLogUtil.insertLog("批量更新角色", getAdminUser(), LogEnum.BATCH);
		} catch (Exception e) {
			new YuleException(e);
			throw e;
		} 
		return ActionReturnConst.REDIRECT+"/adminRole/findAdminRole.do";
	}

	/**
	 * 修改角色信息 
	 */
	@RequestMapping(value = "/updateAdminRole", method = RequestMethod.POST)
	public String updateAdminRole(@ModelAttribute("adminRole") AdminRole adminRole) throws Exception {
		JSONObject obj = new JSONObject();
		obj.put("status",ErrorConst.STATUS_ERROR);
		try {
			boolean flag = adminRoleServiceImpl.updateAdminRole(adminRole);
			obj.put("status", flag);
			AdminLogUtil.insertLog("更新角色", getAdminUser(), LogEnum.UPDATE);
		} catch (Exception e) {
			new YuleException("修改角色信息 【updateAdminRole】:发生错误!",e);
			throw e;
		} finally{
			outputResult(obj.toString());
		}
		return null;
	}

	/**
	 * 添加角色信息
	 */
	@RequestMapping(value = "/insertAdminRole", method = RequestMethod.POST)
	public String insertAdminRole(@ModelAttribute("adminRole") AdminRole adminRole) throws Exception {
		JSONObject obj = new JSONObject();
		obj.put("status",ErrorConst.STATUS_ERROR);
		try {
			adminRole.setIs_admin(AdminConst.IS_ADMIN_FALSE);
			boolean flag = adminRoleServiceImpl.insertAdminRole(adminRole);
			obj.put("id", adminRole.getId());
			obj.put("status", flag);
			AdminLogUtil.insertLog("新增角色", getAdminUser(), LogEnum.INSERT);
		} catch (Exception e) {
			new YuleException("添加角色【insertAdminRole】:发生错误!",e);
			throw e;
		} finally{
			outputResult(obj.toString());
		}
		return null;
	}

	/**
	 * 删除角色信息
	 */
	@RequestMapping(value = "/deleteAdminRole",method = RequestMethod.POST)
	public String deleteAdminRoleById(@RequestParam(value="id",required=false) String id)throws Exception {
		JSONObject obj = new JSONObject();
		obj.put("status",ErrorConst.STATUS_ERROR);
		try {
			boolean flag = adminRoleServiceImpl.deleteAdminRoleById(id);
			obj.put("status", flag);
			AdminLogUtil.insertLog("删除角色", getAdminUser(), LogEnum.DELETE);
		} catch (Exception e) {
			new YuleException("删除角色信息【deleteAdminRole】:发生错误!",e);
			throw e;
		} finally{
			outputResult(obj.toString());
		}
		return null;
	}
	
	/**
	 * 根据角色id获取和配置权限
	 */
	@RequestMapping(value = "/findAdminRolePrivilege",method = RequestMethod.GET)
	public String findAdminRolePrivilege(@RequestParam(value="id",required=false) String id) throws Exception {
		try {
			StringBuffer htmls = new StringBuffer();
		    List<AdminPrivilegeVO> adminPrivileges=adminPrivilegeServiceImpl.findAdminPrivilegeVOList(id);
		    if(null!=adminPrivileges&&adminPrivileges.size()>0){
		    	int i=0;
			    for (AdminPrivilegeVO adminPrivilege : adminPrivileges) {
			    	if(StringUtils.isEmpty(adminPrivilege.getParent_id())){
			    		htmls.append("<div class=\"first-list\">");
				    	htmls.append("<div data-trigger=\"collapse\" class=\"type-list-item  alt-row active\">");
				    	if(CheckConst.IS_CHECK_TRUE==adminPrivilege.getIs_check()){
					    	htmls.append("<input type=\"checkbox\" name=\"admin_privilege_id\" checked=\"checked\" value=\""+adminPrivilege.getId()+"\" />");
				    	}else{
					    	htmls.append("<input type=\"checkbox\" name=\"admin_privilege_id\"  value=\""+adminPrivilege.getId()+"\" />");
				    	}
				    	htmls.append("<label>"+adminPrivilege.getName()+"</label>");
				    	htmls.append("</div>");
				  
				    	htmls.append("<div class=\"type-list-part collapsible \">");
				    	i=0;
				    	for (AdminPrivilegeVO adminPrivilegeTwo :adminPrivileges) {
				    		if(!StringUtils.isEmpty(adminPrivilegeTwo.getParent_id())){
				    			if(adminPrivilege.getId().equals(adminPrivilegeTwo.getParent_id())){
				    				if(i%4==0){
										if(i>0){
											htmls.append("</div>");
										}
										htmls.append("<div class=\"type-list-list clearfix\">");
									}
									htmls.append("<div class=\"type-list\">");
						    		htmls.append("<div data-trigger=\"collapse\" class=\"type-list-item active\">");
						    		if(CheckConst.IS_CHECK_TRUE==adminPrivilegeTwo.getIs_check()){
						    		    htmls.append("<input type=\"checkbox\" name=\"admin_privilege_id\" checked=\"checked\"  value=\""+adminPrivilegeTwo.getId()+"\" />");
						    		}else{
						    		    htmls.append("<input type=\"checkbox\" name=\"admin_privilege_id\" value=\""+adminPrivilegeTwo.getId()+"\"  />");
						    		}
						    		htmls.append(adminPrivilegeTwo.getName());
				                    htmls.append("</div>");
				                    htmls.append(" <div class=\"collapsible  clearfix\">");
									for (AdminPrivilegeVO adminPrivilegeThree : adminPrivileges) {
										if(!StringUtils.isEmpty(adminPrivilegeTwo.getParent_id())){
							    			if(adminPrivilegeTwo.getId().equals(adminPrivilegeThree.getParent_id())){
							    				 htmls.append("<span class=\"type-list-sub-item\">");
												 if(CheckConst.IS_CHECK_TRUE==adminPrivilegeThree.getIs_check()){
													 htmls.append("<input type=\"checkbox\" name=\"admin_privilege_id\" checked=\"checked\" value=\""+adminPrivilegeThree.getId()+"\" />");
												 }else{
													 htmls.append("<input type=\"checkbox\" name=\"admin_privilege_id\" value=\""+adminPrivilegeThree.getId()+"\" />");
												 }
												 htmls.append("<label>"+adminPrivilegeThree.getName()+"</label>");
							                     htmls.append("</span>");	
							    			}
										}
									}
						    		htmls.append("</div>");
						    		htmls.append("</div>");
						    		i++;
				    			}
				    		}
						}
				    	htmls.append("</div>");
				    	htmls.append("</div>");
				    	htmls.append("</div>");
			    	}
				}
			    adminPrivileges.clear();
				adminPrivileges=null;
		    }
			request.setAttribute("htmls", htmls);
			request.setAttribute("roleName", adminRoleServiceImpl.findAdminRoleById(id).getName());
			request.setAttribute("adminRoleId", id);
		} catch (Exception e) {
			new YuleException("根据角色id获取和配置权限【findRolePrivilege】:发生错误!",e);
			throw e;
		}
		return "admin/role/rolePrivilegeConfig";
	}
	
	/**
	 * 修改角色权限数据
	 */
	@RequestMapping(value = "/updateAdminRolePrivilege",method = RequestMethod.POST)
	public String updateRolePrivilege(UpdateAdminRolePrivilegeParam updateAdminRolePrivilegeParam)throws Exception {
		String adminRoleId = "";
		try {
			adminRoleId = updateAdminRolePrivilegeParam.getAdmin_role_id();
			adminRoleServiceImpl.updateAdminRolePrivilege(updateAdminRolePrivilegeParam);
			PrivilegeUtil.initPrivilege(adminRoleId);
			AdminLogUtil.insertLog("角色权限配置", getAdminUser(), LogEnum.CONFIG);
		} catch (Exception e) {
			new YuleException("修改角色权限数据【updateRolePrivilege】:发生错误!",e);
			throw e;
		} 
		return ActionReturnConst.REDIRECT+"/adminRole/findAdminRolePrivilege.do?id="+adminRoleId;
	}
	
}
