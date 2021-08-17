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

import com.yule.admin.service.IAdminPrivilegeService;
import com.yule.admin.service.IAdminRoleService;
import com.yule.common.BaseAction;
import com.yule.constant.ErrorConst;
import com.yule.constant.PlaceholderConst;
import com.yule.constant.PrivilegeConst;
import com.yule.constant.ShowConst;
import com.yule.enumerate.LogEnum;
import com.yule.exception.YuleException;
import com.yule.pojo.AdminPrivilege;
import com.yule.pojo.AdminRolePrivilege;
import com.yule.runnable.AdminRolePrivilegeRunnable;
import com.yule.util.AdminLogUtil;

@Controller
@Scope("prototype")
@RequestMapping("/adminPrivilege")
public class AdminPrivilegeAction extends BaseAction {
	
	@Autowired
	private IAdminPrivilegeService adminPrivilegeServiceImpl;
	
	@Autowired
	private IAdminRoleService adminRoleServiceImpl;

	
	@RequestMapping(value = "/findAdminPrivilege",method = RequestMethod.GET)
	public String findAdminPrivilege(@RequestParam(value="parentId",required = false) String parentId,@RequestParam(value="son",required = false)Integer son)throws Exception {
		try {
			StringBuffer selects = new StringBuffer();
			AdminPrivilege ap = null;
			String goBackPanrentId = null;
			Integer orderNum = 0;
			if (null!=parentId) {
				goBackPanrentId=adminPrivilegeServiceImpl.findAdminPrivilegeById(parentId).getParent_id();
				if(null==goBackPanrentId){
				   selects.append(" <a class=\"goback-btn button\" href=\"/adminPrivilege/findAdminPrivilege.do\">返回上一级</a>");
				}else{
				   selects.append(" <a class=\"goback-btn button\" href=\"/adminPrivilege/findAdminPrivilege.do?parentId="+goBackPanrentId+"\">返回上一级</a>");
				}
			}
			StringBuffer privilegeHtml = new StringBuffer("");
			if(getPrivilege().containsKey(PrivilegeConst.ADMIN_PRIVILEGE_CODE_DELETE)){
				ap = getPrivilege().get(PrivilegeConst.ADMIN_PRIVILEGE_CODE_DELETE);
				privilegeHtml.append("<a  class=\"button\" href=\"javascript:;\" data-delete=\"\"  data-id=\""+PlaceholderConst.ID+"\" data-url=\""+ap.getUrl()+"\" >"+ap.getName()+"</a>&nbsp;");
				ap = null;
			}
			if (null==parentId) {
				privilegeHtml.append("<a  class=\"button\"  href=\""+request.getServletPath()+"?son=0&&parentId="+PlaceholderConst.ID+"\" >"+"编辑子权限"+"</a>&nbsp;");
			}else{
				privilegeHtml.append("<a  class=\"button\"  href=\""+request.getServletPath()+"?parentId="+PlaceholderConst.ID+"\" >"+"编辑子权限"+"</a>&nbsp;");
			}

			StringBuffer htmls = new StringBuffer();
			List<AdminPrivilege> adminPrivileges = adminPrivilegeServiceImpl.findAdminPrivilegeListByParentId(parentId);
			StringBuffer rowHtmls = new StringBuffer(); 
			rowHtmls.append("<tr>");
			rowHtmls.append("<td class=\"name\"><input id=\"id\" type=\"hidden\" name=\"id\" value=\""+PlaceholderConst.ID+"\" />");
			rowHtmls.append(PlaceholderConst.NAME+"</td>");
			rowHtmls.append("<td>"+PlaceholderConst.URL+"</td>");
			if (parentId!=null&&son==null) {
				rowHtmls.append("<td>"+PlaceholderConst.CODE+"</td>");
			}else{
				rowHtmls.append("<td></td>");
			}
			rowHtmls.append("<td>"+PlaceholderConst.ORDER+"</td>");
			rowHtmls.append("<td>"+PlaceholderConst.IS_SHOW+"</td>");
			rowHtmls.append("<td>"+privilegeHtml.toString()+"</td>");
			rowHtmls.append("</tr>");
			htmls.append("<tbody id=\"list\">");
			if(null!=adminPrivileges&&adminPrivileges.size()>0){
				StringBuffer id = new StringBuffer();
				for(AdminPrivilege adminPrivilegeTmp:adminPrivileges){
					id.append(adminPrivilegeTmp.getId());
					htmls.append("<tr>");
					htmls.append("<td class=\"name\"><input id=\"id\" type=\"hidden\" name=\"id\" value=\""+id+"\" />");
					htmls.append(adminPrivilegeTmp.getName()+"</td>");
					htmls.append("<td>"+adminPrivilegeTmp.getUrl()+"</td>");
					htmls.append("<td>"+adminPrivilegeTmp.getCode()+"</td>");
					htmls.append("<td>"+adminPrivilegeTmp.getOrder()+"</td>");
					htmls.append("<td>"+ShowConst.IS_SHOW[adminPrivilegeTmp.getIs_show()]+"</td>");
					htmls.append("<td>"+privilegeHtml.toString().replace(PlaceholderConst.ID, id)+"</td>");
					htmls.append("</tr>");
					id.setLength(0);
				}
				orderNum = adminPrivileges.size();
				adminPrivileges.clear();
				adminPrivileges = null;
			}else{
				htmls.append("<tr>");
				htmls.append("<td style=\"text-align: center;\" colspan=\"6\">暂无数据</td>");
				htmls.append("</td>");
				htmls.append("</tr>");
			}
			htmls.append("</tbody>");
			StringBuffer insertHtmls = new StringBuffer("");
			insertHtmls.append("<tr><td class=\"name\"><input id=\"id\" type=\"hidden\" name=\"id\" value=\"\" />");
			if (!StringUtils.isEmpty(parentId)) {
				insertHtmls.append("<input id=\"parent_id\" name=\"parent_id\" type=\"hidden\" value=\""+parentId+"\"  />");
			}
			
			insertHtmls.append("<span>权限名称:<input id=\"name\" listmsg=\"\" class=\"text-input\" type=\"text\"  name=\"name\"  nullmsg=\"请输入权限名称!\" datatype=\"list\" errormsg=\"权限名称重复!\"  /></span>");
			insertHtmls.append("<span>权限地址:<input id=\"url\" class=\"text-input small-input\" type=\"text\"  name=\"url\"  nullmsg=\"请输入权限地址!\" datatype=\"\" errormsg=\"\"  /></span></br>");
			if (parentId!=null&&son==null) {
				insertHtmls.append("<span>权限code:<input id=\"code\" class=\"text-input\" type=\"text\"  name=\"code\"  /></span>");
			}
			insertHtmls.append("<span>权限排序:<input id=\"order\" class=\"text-input\" type=\"text\"  name=\"order\"  nullmsg=\"请输入排序号!\"  value=\""+orderNum+"\" errormsg=\"序号必须为数字\" datatype=\"n\" /></span></br>");
			insertHtmls.append("<span>是否显示:<select name=\"is_show\" id=\"is_show\"><option value=\"0\">显示</option><option value=\"1\">不显示</option></select></span>");
			
			if(getPrivilege().containsKey(PrivilegeConst.ADMIN_PRIVILEGE_CODE_INSERT)){
				ap = getPrivilege().get(PrivilegeConst.ADMIN_PRIVILEGE_CODE_INSERT);
				insertHtmls.append("<td><a class=\"button\" href=\"javascript:;\" data-query-insert=\"\" data-rep=\"name\" data_param=\"#parentId\" data-url=\""+ap.getUrl()+"\">"+ap.getName()+"</a></td></tr>");
				ap = null;
			}
			request.setAttribute("rowHtmls", rowHtmls);
			request.setAttribute("selects", selects);
			request.setAttribute("insertHtmls",insertHtmls);
			request.setAttribute("privilegeHtml", privilegeHtml);
			request.setAttribute("htmls", htmls);
			request.setAttribute("parentId", parentId);
			rowHtmls = null;
			selects = null;
			privilegeHtml = null;
			htmls = null;
			parentId = null;
			AdminLogUtil.insertLog("查询权限", getAdminUser(), LogEnum.QUERY);
		} catch (Exception e) {
			new YuleException("获取查询权限[findAdminPrivilege]错误!",e);
			throw e;
		} 	
		return "admin/privilege/index";
	}
	

	/**
	 * 新增权限
	 */
	@RequestMapping(value = "/insertAdminPrivilege",method = RequestMethod.POST)
	public String insertAdminPrivilege(@ModelAttribute("adminPrivilege")AdminPrivilege adminPrivilege) throws Exception {
		JSONObject obj = new JSONObject();
		obj.put("status",ErrorConst.STATUS_ERROR);
		try {
			boolean flag = adminPrivilegeServiceImpl.insertAdminPrivilege(adminPrivilege);
			String id = adminPrivilege.getId();
			if (flag) {
				Thread t =new Thread(new AdminRolePrivilegeRunnable(id,null));
				t.start();
			}
			obj.put("id", id);
			obj.put("status", flag);
			obj.put("is_show_text", ShowConst.IS_SHOW[adminPrivilege.getIs_show()]);
			AdminLogUtil.insertLog("新增权限", getAdminUser(), LogEnum.INSERT);
		} catch (Exception e) {
			new YuleException("新增权限[insertAdminPrivilege]错误",e);
			throw e;
		} finally{
			outputResult(obj.toString());
		}
		return null;
	}
	
	/**
	 * 更新权限
	 */
	@RequestMapping(value = "/updateAdminPrivilege",method = RequestMethod.POST)
	public String updateAdminPrivilege(@ModelAttribute("adminPrivilege")AdminPrivilege adminPrivilege) throws Exception {
		JSONObject obj = new JSONObject();
		obj.put("status",ErrorConst.STATUS_ERROR);
		try {
			boolean flag = adminPrivilegeServiceImpl.updateAdminPrivilege(adminPrivilege);
			if (flag) {
				Thread t =new Thread(new AdminRolePrivilegeRunnable(adminPrivilege.getId(),null));
				t.start();
			}
			obj.put("status", flag);
			AdminLogUtil.insertLog("更新权限", getAdminUser(), LogEnum.UPDATE);
		} catch (Exception e) {
			new YuleException("更新权限[updateAdminPrivilege]错误",e);
			throw e;
		} finally{
			outputResult(obj.toString());
		}
		return null;
	}
	/**
	 * 删除权限
	 */
	@RequestMapping(value = "/deleteAdminPrivilege",method = RequestMethod.POST)
	public String deleteAdminPrivilege(@RequestParam(value="id",required=false)String id) throws Exception {
		JSONObject obj = new JSONObject();
		obj.put("status",ErrorConst.STATUS_ERROR);
		try {
			List<AdminRolePrivilege> adminRolePrivileges = adminRoleServiceImpl.findAdminRoleIdListByAdminPrivilegeId(id);
			boolean flag = adminPrivilegeServiceImpl.deleteAdminPrivilegeById(id);
			if (flag) {
				Thread t =new Thread(new AdminRolePrivilegeRunnable(null,adminRolePrivileges));
				t.start();
			}
			obj.put("status", flag);
			AdminLogUtil.insertLog("删除权限", getAdminUser(), LogEnum.DELETE);
		} catch (Exception e) {
			new YuleException("删除权限[deleteAdminPrivilege]错误",e);
			throw e;
		} finally{
			outputResult(obj.toString());
		}
		return null;
	}

}
 