package com.yule.action.company;


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

import com.yule.admin.service.ICompanyPrivilegeService;
import com.yule.admin.service.ICompanyUserService;
import com.yule.common.BaseAction;
import com.yule.constant.ActionReturnConst;
import com.yule.constant.AdminErrorConst;
import com.yule.constant.CheckConst;
import com.yule.constant.DeleteConst;
import com.yule.constant.ErrorConst;
import com.yule.constant.PageConst;
import com.yule.constant.PrivilegeConst;
import com.yule.constant.ShowConst;
import com.yule.constant.StatusConst;
import com.yule.enumerate.DateStyle;
import com.yule.enumerate.LogEnum;
import com.yule.exception.YuleException;
import com.yule.param.UpdateCompanyUserPrivilegeParam;
import com.yule.pojo.AdminPrivilege;
import com.yule.pojo.CompanyUser;
import com.yule.runnable.CompanyPrivilegeRunnable;
import com.yule.util.AdminLogUtil;
import com.yule.util.DateUtil;
import com.yule.util.PaginationUtil;
import com.yule.vo.CompanyUserPrivilegeVO;
import com.yule.vo.Page;

@Controller
@Scope("prototype")
@RequestMapping("/companyUser")
public class CompanyUserAction extends BaseAction{
	
	@Autowired
	private ICompanyUserService companyUserServiceImpl;
	@Autowired
	private ICompanyPrivilegeService companyPrivilegeServiceImpl; 
	@RequestMapping(value="/findCompanyUser",method=RequestMethod.GET)
	public String findCompanyUser(@RequestParam(value = "companyId", required = false)String companyId,@RequestParam(value = "pageNo", required = false) Integer pageNo) throws Exception{
		if(null==pageNo||pageNo<1){
			pageNo = 1;
		}
		try {
			Page<CompanyUser> page = this.companyUserServiceImpl.findCompanyUserPage(pageNo,PageConst.PAGE_SIZE_TEN,companyId);
			//拼凑html
			StringBuffer htmls = new StringBuffer();
			htmls.append("<tfoot><tr><td colspan=\"7\">");
			htmls.append("<div class=\"bulk-actions align-left\"></div>");
			htmls.append(PaginationUtil.getPaginationHtml(page));
			htmls.append("<div class=\"clear\"></div>");
			htmls.append("</td></tr></tfoot>");
			htmls.append("<tbody>");
			if(page.getPageCount()>0){
				AdminPrivilege adminPrivilege = null;
				for (CompanyUser companyUser : page.getDatas()) {
					htmls.append("<tr>");
					htmls.append("<td>" + companyUser.getAccount() + "</td>");
					htmls.append("<td>" + DateUtil.DateToString(companyUser.getCreate_time(), DateStyle.YYYY_MM_DD_HH_MM_SS_EN) + "</td>");
					if (StringUtils.isEmpty(companyUser.getIs_delete())) {
						htmls.append("<td class=\"is_delete\">" + DeleteConst.IS_DELETE[DeleteConst.IS_DELETE_FALSE] + "</td>");	
					}else{
						htmls.append("<td class=\"is_delete\">" + DeleteConst.IS_DELETE[companyUser.getIs_delete()] + "</td>");
					}
					htmls.append("<td>");
					if(getPrivilege().containsKey(PrivilegeConst.ADMIN_PRIVILEGE_CODE_UPDATE)){
						adminPrivilege = getPrivilege().get(PrivilegeConst.ADMIN_PRIVILEGE_CODE_UPDATE);
						htmls.append("<a class=\"button\" href=\""+adminPrivilege.getUrl()+"?id="+companyUser.getId()+"\" \">"+adminPrivilege.getName()+"</a>&nbsp;");
					}
					if(getPrivilege().containsKey(PrivilegeConst.ADMIN_PRIVILEGE_CODE_UPDATE_STATUS)){
						adminPrivilege = getPrivilege().get(PrivilegeConst.ADMIN_PRIVILEGE_CODE_UPDATE_STATUS);
						if (StringUtils.isEmpty(companyUser.getStatus())) {
							htmls.append("<a class=\"button\" href=\"javascript:;\" data-url=\""+adminPrivilege.getUrl()+"\" data-id=\""+companyUser.getId()+"\" data-name=\"status\" data-status=\""+StatusConst.BUTTON_STATUS_VALUE[StatusConst.STATUS_FALSE]+"\" />"+StatusConst.BUTTON_STATUS[StatusConst.STATUS_FALSE]+"</a>&nbsp;");	
						}else{
							htmls.append("<a class=\"button\" href=\"javascript:;\" data-url=\""+adminPrivilege.getUrl()+"\" data-id=\""+companyUser.getId()+"\" data-name=\"status\" data-status=\""+StatusConst.BUTTON_STATUS_VALUE[companyUser.getStatus()]+"\" />"+StatusConst.BUTTON_STATUS[companyUser.getStatus()]+"</a>&nbsp;");
						}
					}
					if(getPrivilege().containsKey(PrivilegeConst.ADMIN_PRIVILEGE_CODE_DELETE)){
						adminPrivilege = getPrivilege().get(PrivilegeConst.ADMIN_PRIVILEGE_CODE_DELETE);
						if (StringUtils.isEmpty(companyUser.getIs_delete())) {
							htmls.append("<a class=\"button\" data-name=\"is_delete\" href=\"javascript:;\"  name=\"is_delete\" data-url=\""+adminPrivilege.getUrl()+"\" data-id=\""+companyUser.getId()+"\" data-status=\""+DeleteConst.BUTTON_DELETE_VALUE[DeleteConst.IS_DELETE_FALSE]+"\" />"+DeleteConst.BUTTON_DELETE[DeleteConst.IS_DELETE_FALSE]+"</a>&nbsp;");
						}else{
							htmls.append("<a class=\"button\" data-name=\"is_delete\" href=\"javascript:;\"  name=\"is_delete\" data-url=\""+adminPrivilege.getUrl()+"\" data-id=\""+companyUser.getId()+"\" data-status=\""+DeleteConst.BUTTON_DELETE_VALUE[companyUser.getIs_delete()]+"\" />"+DeleteConst.BUTTON_DELETE[companyUser.getIs_delete()]+"</a>&nbsp;");
						}
					}
					if(getPrivilege().containsKey(PrivilegeConst.ADMIN_PRIVILEGE_CODE_COMPANY_USERPRIVILEGE_CONFIG)){
						adminPrivilege = getPrivilege().get(PrivilegeConst.ADMIN_PRIVILEGE_CODE_COMPANY_USERPRIVILEGE_CONFIG);
						htmls.append("<a  class=\"button\" href=\"" + adminPrivilege.getUrl() + "?id=" + companyUser.getId() + "&companyId=" + companyId + "\"  >" + adminPrivilege.getName() + "</a>&nbsp;&nbsp; ");
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
			request.setAttribute("htmls", htmls);
			request.setAttribute("companyId",companyId );
			AdminLogUtil.insertLog("查询企业子用户", getAdminUser(), LogEnum.QUERY);
		} catch (Exception e) {
			new YuleException("查询企业用户【findCompanyUser】出现异常",e);
			throw e;
		}
		return "company/user/index";
	}
	@RequestMapping(value = "/insertCompanyUser", method = RequestMethod.POST)
	public String insertCompanyUser(@ModelAttribute("companyUser")CompanyUser companyUser) throws Exception{
		try {
			this.companyUserServiceImpl.insertCompanyUser(companyUser);
			AdminLogUtil.insertLog("插入企业子用户", getAdminUser(), LogEnum.INSERT);
		} catch (Exception e) {
			new YuleException("新增企业用户【insertCompanyUser】出现异常",e);
			throw e;
		}
		return ActionReturnConst.REDIRECT+"/companyUser/findCompanyUser.do?companyId="+companyUser.getCompany_id();
	}

	/**
	 * 验证新增用户
	 */
	@RequestMapping(value = "/verifyCompanyUser", method = RequestMethod.POST)
	public String verifyCompanyUser( @RequestParam(value = "account", required = false)String account,@RequestParam(value = "companyId", required = false)String companyId)throws Exception {
		JSONObject obj = new JSONObject();
		obj.put("status", ErrorConst.STATUS_ERROR);
		try {
			boolean flag = companyUserServiceImpl.findCompanyUserByAccount(account,companyId);
			obj.put("name", "account");
			if(!flag){
				obj.put("message",AdminErrorConst.ADMIN_USER_2);
			}else{
				obj.put("status",ErrorConst.STATUS_SUCCESS);
			}
		} catch (Exception e) {
			new YuleException("子用户验证【verifyCompanyUser】:发生错误!",e);
			throw e;
		} finally{
			outputResult(obj.toString());
		}
		return null;
	}
	@RequestMapping(value = "/deleteCompanyUser",method = RequestMethod.POST)
	public String deleteCompanyUser(@ModelAttribute("companyUser")CompanyUser companyUser) throws Exception{
		JSONObject obj = new JSONObject();
		obj.put("status",ErrorConst.STATUS_ERROR);
		try {
			boolean flag = this.companyUserServiceImpl.deleteCompanyUserById(companyUser);
			int status = companyUser.getIs_delete();
			obj.put("status", flag);
			obj.put("value",DeleteConst.BUTTON_DELETE_VALUE[status]);
			obj.put("is_delete_text",DeleteConst.IS_DELETE[status]);
			obj.put("text",DeleteConst.BUTTON_DELETE[status]);
			AdminLogUtil.insertLog("删除企业子用户", getAdminUser(), LogEnum.DELETE);
		} catch (Exception e) {
			new YuleException("删除子用户【deleteCompanyUser】发生异常",e);
			throw e;
		} finally{
			outputResult(obj.toString());
		}
		return null;
	}
	/**
	 * 根据用户id获取用户相关信息
	 */
	@RequestMapping(value = "/findCompanyUserById",method = RequestMethod.GET)
	public String findCompanyUserById(@RequestParam(value="id",required=false) String id)
			throws Exception {
		try {
			CompanyUser companyUser = companyUserServiceImpl.findCompanyUserById(id);
			request.setAttribute("companyUser", companyUser);
			AdminLogUtil.insertLog("通过ID查询子用户相关信息", getAdminUser(), LogEnum.QUERY);
		} catch (Exception e) {
			new YuleException("根据用户id获取用户【findCompanyUserById】:发生错误!",e);
			throw e;
		} 
		return "company/user/updateCompanyUser";
	}

	/**
	 * 修改用户信息
	 */
	@RequestMapping(value = "/updateCompanyUser",method = RequestMethod.POST)
	public String updateCompanyUser(@ModelAttribute("companyUser") CompanyUser companyUser,String companyId) throws Exception {
		try {
			companyUserServiceImpl.updateCompanyUser(companyUser);
		} catch (Exception e) {
			new YuleException("修改用户信息【updateCompanyUser】:发生错误!",e);
			throw e;
		} 
		return ActionReturnConst.REDIRECT+"/companyUser/findCompanyUser.do?companyId="+companyId;
	}

	@RequestMapping(value = "/findCompanyUserPrivilege",method = RequestMethod.GET)
	public String findCompanyUserPrivilege(@RequestParam(value="id",required=false)String id,@RequestParam(value="companyId",required=false)String companyId)throws Exception{
		try {
			StringBuffer htmls = new StringBuffer();
		   List<CompanyUserPrivilegeVO> companyPrivilegeVOList = this.companyPrivilegeServiceImpl.findCompanyPrivilegeVOList(companyId, id);
		    if(null!=companyPrivilegeVOList&&companyPrivilegeVOList.size()>0){
		    	int i=0;
		    	boolean is_oneGread = true;
		    	StringBuffer show_type = new StringBuffer();
			    for (CompanyUserPrivilegeVO companyPrivilege : companyPrivilegeVOList) {
			    	if(StringUtils.isEmpty(companyPrivilege.getParent_id())){
			    		show_type.setLength(0);
			    		if (companyPrivilege.getIs_show() == ShowConst.IS_SHOW_FALSE) {
			    			show_type.append("style=\"display:none\"");
						}
			    		htmls.append("<div class=\"first-list\" "+show_type+">");
				    	htmls.append("<div data-trigger=\"collapse\" class=\"type-list-item  alt-row active\">");
				    	if(CheckConst.IS_CHECK_TRUE==companyPrivilege.getIs_check()){
					    	htmls.append("<input type=\"checkbox\" name=\"company_privilege_id\" checked=\"checked\" value=\""+companyPrivilege.getPrivilege_id()+"\" />");
				    	}else{
					    	htmls.append("<input type=\"checkbox\" name=\"company_privilege_id\"  value=\""+companyPrivilege.getPrivilege_id()+"\" />");
				    	}
				    	htmls.append("<label>"+companyPrivilege.getName()+"</label>");
				    	htmls.append("</div>");
				    	htmls.append("<div class=\"type-list-part collapsible \">");
				    	i=0;
				    	for (CompanyUserPrivilegeVO companyPrivilegeTwo :companyPrivilegeVOList) {
				    		if(!StringUtils.isEmpty(companyPrivilegeTwo.getParent_id())){
				    			if(companyPrivilege.getPrivilege_id().equals(companyPrivilegeTwo.getParent_id())){
				    				show_type.setLength(0);
				    				if (companyPrivilegeTwo.getIs_show() == ShowConst.IS_SHOW_FALSE) {
						    			show_type.append("style=\"display:none\"");
									}
				    				is_oneGread = false;
				    				if(i%4==0){
										if(i>0){
											htmls.append("</div>");
										}
										htmls.append("<div class=\"type-list-list clearfix\">");
									}
									htmls.append("<div class=\"type-list\" "+show_type+">");
						    		htmls.append("<div data-trigger=\"collapse\"  class=\"type-list-item active\">");
						    		if(CheckConst.IS_CHECK_TRUE==companyPrivilegeTwo.getIs_check()){
						    		    htmls.append("<input type=\"checkbox\" name=\"company_privilege_id\" checked=\"checked\"  value=\""+companyPrivilegeTwo.getPrivilege_id()+"\" />");
						    		}else{
						    		    htmls.append("<input type=\"checkbox\" name=\"company_privilege_id\" value=\""+companyPrivilegeTwo.getPrivilege_id()+"\"  />");
						    		}
						    		htmls.append(companyPrivilegeTwo.getName());
				                    htmls.append("</div>");
				                    htmls.append(" <div class=\"collapsible  clearfix\">");
									for (CompanyUserPrivilegeVO CompanyUserPrivilegeThree : companyPrivilegeVOList) {
										if(!StringUtils.isEmpty(companyPrivilegeTwo.getParent_id())){
							    			if(companyPrivilegeTwo.getPrivilege_id().equals(CompanyUserPrivilegeThree.getParent_id())){
							    				 htmls.append("<span class=\"type-list-sub-item\" style=\"display:none;\" >");
												 if(CheckConst.IS_CHECK_TRUE==CompanyUserPrivilegeThree.getIs_check()){
													 htmls.append("<input type=\"checkbox\" name=\"company_privilege_id\" checked=\"checked\" value=\""+CompanyUserPrivilegeThree.getPrivilege_id()+"\" />");
												 }else{
													 htmls.append("<input type=\"checkbox\" name=\"company_privilege_id\" value=\""+CompanyUserPrivilegeThree.getPrivilege_id()+"\" />");
												 }
												 htmls.append("<label>"+CompanyUserPrivilegeThree.getName()+"</label>");
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
				    	if (!is_oneGread) {
				    		htmls.append("</div>");	
						}
			    	}
			    	show_type.setLength(0);
				}
			    
		    }
			request.setAttribute("companyId", companyId);
			request.setAttribute("companyUserId", id);
			request.setAttribute("account", companyUserServiceImpl.findCompanyUserById(companyId).getAccount());
			request.setAttribute("htmls", htmls);
		} catch (Exception e) {
			new YuleException("根据角色id获取和配置权限【findRolePrivilege】:发生错误!",e);
			throw e;
		}
		return "company/user/userPrivilegeConfig";
	}
	@RequestMapping(value = "/insertCompanyUserPrivilege", method = RequestMethod.POST)
	public String insertCompanyUserPrivilege(@RequestParam(value="companyId",required=false)String companyId,UpdateCompanyUserPrivilegeParam updateCompanyUserPrivilegeParam) throws Exception{
		try {
			boolean flag = this.companyUserServiceImpl.updateCompanyUserPrivilege(updateCompanyUserPrivilegeParam);
			if (flag) {
				Thread t =new Thread(new CompanyPrivilegeRunnable(companyId, CompanyPrivilegeRunnable.IS_COMPANY_USER));
				t.start();
			}
			AdminLogUtil.insertLog("插入用户权限关系", getAdminUser(), LogEnum.INSERT);
		} catch (Exception e) {
			new YuleException("插入用户权限关系【insertCompanyUserPrivilege】出现异常",e);
			throw e;
		}
		return ActionReturnConst.REDIRECT+"/companyUser/findCompanyUserPrivilege.do?companyId=" + companyId + "&id=" + updateCompanyUserPrivilegeParam.getCompany_user_id();
	}
	
	 /**
	 * 更新企业状态
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/updateCompanyUserStatus",method = RequestMethod.POST)
	public String updateCompanyManagerVOStatus(@ModelAttribute("companyUser")CompanyUser companyUser) throws Exception {
		JSONObject obj=new JSONObject();
		obj.put("status", ErrorConst.STATUS_ERROR);
		try{
			boolean flag = companyUserServiceImpl.updateCompanyUser(companyUser);
			int status = companyUser.getStatus();
			obj.put("status", flag);
			obj.put("value",StatusConst.BUTTON_STATUS_VALUE[status]);
			obj.put("text",StatusConst.BUTTON_STATUS[status]);
		}catch(Exception e){
			new YuleException(e);
			throw e;
		}
		outputResult(obj.toString());
		return null;
	}
	
	
}
