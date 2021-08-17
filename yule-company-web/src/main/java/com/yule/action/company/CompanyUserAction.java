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

import com.yule.common.BaseAction;
import com.yule.company.query.CompanyUserQuery;
import com.yule.company.service.ICompanyPrivilegeService;
import com.yule.company.service.ICompanyUserService;
import com.yule.company.vo.CompanyUserVO;
import com.yule.constant.ActionReturnConst;
import com.yule.constant.AdminErrorConst;
import com.yule.constant.CheckConst;
import com.yule.constant.ErrorConst;
import com.yule.constant.PageConst;
import com.yule.constant.ShowConst;
import com.yule.constant.StatusConst;
import com.yule.enumerate.DateStyle;
import com.yule.enumerate.LogEnum;
import com.yule.exception.YuleException;
import com.yule.param.UpdateCompanyUserPrivilegeParam;
import com.yule.pojo.CompanyUser;
import com.yule.runnable.CompanyPrivilegeRunnable;
import com.yule.util.CompanyLogUtil;
import com.yule.util.DateUtil;
import com.yule.util.EncryptUtil;
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
	@RequestMapping(value="/findCompanyUser")
	public String findCompanyUser(CompanyUserQuery companyUserQuery,@RequestParam(value = "pageNo", required = false) Integer pageNo) throws Exception{
		if(null==pageNo){
			return "company/user/index";
		}
		if(null==companyUserQuery){
			companyUserQuery = new CompanyUserQuery();
		}
		companyUserQuery.setCompany_id(getCompanyUser().getCompany_id());
		JSONObject obj = new JSONObject();
		try {
			Page<CompanyUser> page = companyUserServiceImpl.findCompanyUserPage(companyUserQuery,PageConst.PAGE_SIZE_TEN, pageNo);
			StringBuffer tfoot = new StringBuffer();
			StringBuffer tbody = new StringBuffer();
			tfoot.append("<tr><td colspan=\"7\">");
			tfoot.append("<div class=\"bulk-actions align-left\"></div>");
			tfoot.append(PaginationUtil.getPaginationHtml(page));
			tfoot.append("<div class=\"clear\"></div>");
			tfoot.append("</td></tr>");
			if(page.getRowCount()>0){
				for(CompanyUser companyUser : page.getDatas()){
					tbody.append("<tr>");
					tbody.append("<td>"+companyUser.getAccount()+"</td>");
					if (StringUtils.isEmpty(companyUser.getLogin_time())) {
						tbody.append("<td>从未登陆</td>");
					}else{
						tbody.append("<td>"+DateUtil.DateToString(companyUser.getLogin_time(), DateStyle.YYYY_MM_DD_HH_MM_SS_EN)+"</td>");
					}
					tbody.append("<td>"+DateUtil.DateToString(companyUser.getCreate_time(), DateStyle.YYYY_MM_DD_HH_MM_SS_EN)+"</td>");
					tbody.append("<td>");
					tbody.append("<div class=\"hidden-sm hidden-xs btn-group\">");
					tbody.append("<button data-name=\"status\" data-id=\""+companyUser.getId()+"\"  data-status=\""+StatusConst.BUTTON_STATUS_VALUE[companyUser.getStatus()]+"\" data-url=\"/companyUser/updateCompanyUserStatus.do\" class=\"btn btn-xs btn-success\">");
					tbody.append(StatusConst.BUTTON_STATUS[companyUser.getStatus()]);
					tbody.append("</button>");
					tbody.append("<a href=\"/companyUser/findCompanyUserPrivilege.do?id="+companyUser.getId()+"\" class=\"btn btn-xs btn-success\">权限配置</a>");
					tbody.append("<a href=\"#companyUser-wizard\" modal-password=\"\"  data-toggle=\"modal\"  class=\"btn btn-xs btn-success\" modal-dialog=\""+companyUser.getId()+"\">修改密码</a>");
					tbody.append("</td>");
					tbody.append("</tr>");					
				}
			}else{
				tbody.append("<tr>");
				tbody.append("<td colspan=\"7\">没有数据</td>");
				tbody.append("</tr>");	
			}
			obj.put("tfoot", tfoot.toString());
			obj.put("tbody", tbody.toString());
			outputResult(obj.toString());
			tfoot.setLength(0);
			tbody.setLength(0);
			obj.clear();
			obj = null;
			CompanyLogUtil.insertLog("查询企业用户", getCompanyUser(), LogEnum.QUERY);
		} catch (Exception e) {
			new YuleException(e);
			throw e;
		} 
		return null;
	}
	
	@RequestMapping(value = "/findCompanyUserPassword",method = RequestMethod.GET)
	public String findCompanyUserPassword() throws Exception{
		return "/company/user/updatePassword";
	}
	/**
	 * 更新企业用户状态
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/updateCompanyUserStatus",method = RequestMethod.POST)
	public String updateCompanyUserStatus(@ModelAttribute("companyUser")CompanyUser companyUser) throws Exception {
		JSONObject obj=new JSONObject();
		obj.put("statuc", ErrorConst.STATUS_ERROR);
		try{
			boolean flag = companyUserServiceImpl.updateCompanyUser(companyUser);
			int status = companyUser.getStatus();
			obj.put("status", flag);
			obj.put("value",StatusConst.BUTTON_STATUS_VALUE[status]);
			obj.put("text",StatusConst.BUTTON_STATUS[status]);
			CompanyLogUtil.insertLog("更改企业状态", getCompanyUser(), LogEnum.UPDATE);
		} catch(Exception e){
			new YuleException(e);
			throw e;
		} finally{
			outputResult(obj.toString());
		}
		return null;
	}

	@RequestMapping(value = "/updateCompanyUserPassword",method = RequestMethod.POST)
	public String updateCompanyUserPassword(@RequestParam(value="oldPassword",required=false)String oldPassword,@RequestParam(value="password",required=false)String password)throws Exception{
		JSONObject json = new JSONObject();
		json.put("status", ErrorConst.STATUS_ERROR);
		try {
			CompanyUserVO companyUserVO = getCompanyUser();
			if (companyUserVO != null) {
				String id = companyUserVO.getId();
				CompanyUser companyUserPas = this.companyUserServiceImpl.findCompanyUserById(id);
				if (!StringUtils.isEmpty(oldPassword) && EncryptUtil.encryptToMD5(oldPassword).equals(companyUserPas.getPassword())) {
					CompanyUser companyUser = new CompanyUser();
					companyUser.setId(id);
					companyUser.setPassword(password);
					boolean flag = this.companyUserServiceImpl.updateCompanyUser(companyUser);
					json.put("status", flag);
				}else{
					json.put("message", "原密码错误");
					return null;
				}
			}
			CompanyLogUtil.insertLog("修改企业用户密码", getCompanyUser(), LogEnum.UPDATE);
		} catch (Exception e) {
			new YuleException("修改密码【updateCompanyUserPassword】出现异常",e);
			throw e;
		}finally{
			outputResult(json.toString());
		}
		return null;
	}
	@RequestMapping(value = "/updateCompanyUser",method = RequestMethod.POST)
	public String updateCompanyUser(@ModelAttribute("companyUser")CompanyUser companyUser)throws Exception{
		JSONObject json = new JSONObject();
		try {
			boolean flag = this.companyUserServiceImpl.updateCompanyUser(companyUser);
			json.put("status", flag);
			outputResult(json.toString());
			CompanyLogUtil.insertLog("修改用户", getCompanyUser(), LogEnum.UPDATE);
		} catch (Exception e) {
			new YuleException("修改密码【updateCompanyUser】出现异常",e);
			throw e;
		}
		return null;
	}
	@RequestMapping(value = "/findCompanyUserPrivilege",method = RequestMethod.GET)
	public String findCompanyUserPrivilege(@RequestParam(value="id",required=false)String id)throws Exception{
		try {
			StringBuffer htmls = new StringBuffer();
		   List<CompanyUserPrivilegeVO>companyPrivilegeVOList = this.companyPrivilegeServiceImpl.findCompanyPrivilegeVOList(getCompanyUser().getCompany_id(), id);
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
						    		htmls.append("<div data-trigger=\"collapse\" class=\"type-list-item active\">");
						    		if(CheckConst.IS_CHECK_TRUE==companyPrivilegeTwo.getIs_check()){
						    		    htmls.append("<input type=\"checkbox\" name=\"company_privilege_id\" checked=\"checked\"  value=\""+companyPrivilegeTwo.getPrivilege_id()+"\" />");
						    		}else{
						    		    htmls.append("<input type=\"checkbox\" name=\"company_privilege_id\" value=\""+companyPrivilegeTwo.getPrivilege_id()+"\"  />");
						    		}
						    		htmls.append(companyPrivilegeTwo.getName());
				                    htmls.append("</div>");
				                    htmls.append(" <div style=\"display:none;\" class=\"collapsible  clearfix\">");
									for (CompanyUserPrivilegeVO CompanyUserPrivilegeThree : companyPrivilegeVOList) {
										if(!StringUtils.isEmpty(companyPrivilegeTwo.getParent_id())){
							    			if(companyPrivilegeTwo.getPrivilege_id().equals(CompanyUserPrivilegeThree.getParent_id())){
							    				 htmls.append("<span style=\"display:none;\" class=\"type-list-sub-item\">");
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
			request.setAttribute("companyUserId", id);
			request.setAttribute("htmls", htmls);
		} catch (Exception e) {
			new YuleException("根据企业id获取和配置权限【findCompanyUserPrivilege】:发生错误!", e);
			throw e;
		}
		return "company/user/userPrivilegeConfig";
	}
	@RequestMapping(value = "/insertCompanyUserPrivilege", method = RequestMethod.POST)
	public String insertCompanyUserPrivilege(@RequestParam(value="companyId",required=false)String companyId,UpdateCompanyUserPrivilegeParam updateCompanyUserPrivilegeParam) throws Exception{
		try {
			boolean flag = this.companyUserServiceImpl.updateCompanyUserPrivilege(updateCompanyUserPrivilegeParam);
			if (flag) {
				Thread t =new Thread(new CompanyPrivilegeRunnable(companyId));
				t.start();
			}
			CompanyLogUtil.insertLog("修改用户权限关系", getCompanyUser(), LogEnum.UPDATE);
		} catch (Exception e) {
			new YuleException("插入用户权限关系【insertCompanyUserPrivilege】出现异常",e);
			throw e;
		}
		return ActionReturnConst.REDIRECT+"/companyUser/findCompanyUserPrivilege.do?id=" + updateCompanyUserPrivilegeParam.getCompany_user_id();
	}
	@RequestMapping(value = "/insertCompanyUser", method = RequestMethod.POST)
	public String insertCompanyUser(@ModelAttribute("companyUser")CompanyUser companyUser) throws Exception{
		try {
			companyUser.setCompany_id(getCompanyUser().getCompany_id());
			this.companyUserServiceImpl.insertCompanyUser(companyUser);
			CompanyLogUtil.insertLog("插入企业子用户", getCompanyUser(), LogEnum.INSERT);
		} catch (Exception e) {
			new YuleException("新增企业用户【insertCompanyUser】出现异常",e);
			throw e;
		}
		return ActionReturnConst.REDIRECT+"/companyUser/findCompanyUser.do";
	}
	@RequestMapping(value = "/verifyCompanyUser", method = RequestMethod.POST)
	public String verifyCompanyUser( @RequestParam(value = "account", required = false)String account)throws Exception {
		JSONObject obj = new JSONObject();
		obj.put("status", ErrorConst.STATUS_ERROR);
		try {
			boolean flag = companyUserServiceImpl.findCompanyUserByAccount(account,getCompanyUser().getCompany_id());
			if(!flag){
				obj.put("message",AdminErrorConst.ADMIN_USER_2);
			}else{
				obj.put("status",ErrorConst.STATUS_SUCCESS);
			}
		} catch (Exception e) {
			new YuleException("子用户验证【verifyCompanyUser】:发生错误!", e);
			throw e;
		} finally{
			outputResult(obj.toString());
		}
		return null;
	}
	
}
