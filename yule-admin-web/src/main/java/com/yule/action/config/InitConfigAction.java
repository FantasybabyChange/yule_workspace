package com.yule.action.config;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.yule.common.BaseAction;
import com.yule.constant.PrivilegeConst;
import com.yule.pojo.AdminPrivilege;

@Controller
@Scope("prototype")
@RequestMapping("/initConfig")
public class InitConfigAction extends BaseAction{
	
	@RequestMapping(value = "/findInit",method = RequestMethod.GET)
	public String findInit() throws Exception{
		AdminPrivilege adminPrivilege = null;
		StringBuffer htmls = new StringBuffer();
		/*			htmls.append("<tr><td>系统后台</td>");	
		if(getPrivilege().containsKey(PrivilegeConst.ADMIN_PRIVILEGE_INITADMIN_DB)){				
			htmls.append("<td>");
			adminPrivilege = getPrivilege().get(PrivilegeConst.ADMIN_PRIVILEGE_INITADMIN_DB);
			htmls.append("<a class=\"button\" href=\"javascript:;\" data-url=\""+adminPrivilege.getUrl() + "\" >"+adminPrivilege.getName()+"</a>&nbsp;");
			adminPrivilege = null;
			htmls.append("</td>");
		}
		htmls.append("<td>");
	if(getPrivilege().containsKey(PrivilegeConst.ADMIN_PRIVILEGE_INITADMIN_REDIS)){				
			adminPrivilege = getPrivilege().get(PrivilegeConst.ADMIN_PRIVILEGE_INITADMIN_REDIS);
			htmls.append("<a class=\"button\" href=\"javascript:;\" data-url=\""+"/initAdmin/initAdminRedis.do" + "\" >"+"初始化Redis"+"</a>&nbsp;");
			*/
/*			adminPrivilege = null;
		}
		htmls.append("</td>");
*/		htmls.append("</tr>");
		htmls.append("<tr><td>企业后台</td>");
		htmls.append("<td>");
/*		if(getPrivilege().containsKey(PrivilegeConst.ADMIN_PRIVILEGE_INIT_COMPANY_CATEGORY_REDIS)){				
			adminPrivilege = getPrivilege().get(PrivilegeConst.ADMIN_PRIVILEGE_INIT_COMPANY_CATEGORY_REDIS);*/
			htmls.append("<a class=\"button\" href=\"javascript:;\" data-url=\""+"/initCompany/initCompanyCategoryRedis.do" + "\" >"+"初始化企业分类Redis"+"</a>&nbsp;");
/*			adminPrivilege = null;
		}*/
/*		if(getPrivilege().containsKey(PrivilegeConst.ADMIN_PRIVILEGE_INIT_COMPANY_COMMENT_CATEGORY_REDIS)){				
			adminPrivilege = getPrivilege().get(PrivilegeConst.ADMIN_PRIVILEGE_INIT_COMPANY_COMMENT_CATEGORY_REDIS);*/
			htmls.append("<a class=\"button\" href=\"javascript:;\" data-url=\""+"/initCompany/initCompanyCommentCategoryRedis.do" + "\" >"+"初始化企业评论分类Redis"+"</a>&nbsp;");
		/*	adminPrivilege = null;
		}*/
		if(getPrivilege().containsKey(PrivilegeConst.ADMIN_PRIVILEGE_INIT_COMPANY_GRAD_REDIS)){				
			adminPrivilege = getPrivilege().get(PrivilegeConst.ADMIN_PRIVILEGE_INIT_COMPANY_GRAD_REDIS);
			htmls.append("<a class=\"button\" href=\"javascript:;\" data-url=\""+"/initCompany/initCompanyGradeRedis.do" + "\" >"+"初始化档次Redis"+"</a>&nbsp;");
			adminPrivilege = null;
		}
/*		if(getPrivilege().containsKey(PrivilegeConst.ADMIN_PRIVILEGE_INITCOMPANY_POINT_CATEGOY_REDIS)){				
			adminPrivilege = getPrivilege().get(PrivilegeConst.ADMIN_PRIVILEGE_INITCOMPANY_POINT_CATEGOY_REDIS);
*/			htmls.append("<a class=\"button\" href=\"javascript:;\" data-url=\""+"/initCompany/initCompanyPointCategoryRedis.do" + "\" >"+"初始化评分分类Redis"+"</a>&nbsp;");
/*			adminPrivilege = null;
		}*/
		htmls.append("</td>");
		htmls.append("</tr>");
		
		htmls.append("<tr><td>用户</td>");
		htmls.append("<td>");
		if(getPrivilege().containsKey(PrivilegeConst.ADMIN_PRIVILEGE_INITUSER_REDIS)){				
			adminPrivilege = getPrivilege().get(PrivilegeConst.ADMIN_PRIVILEGE_INITUSER_REDIS);
			htmls.append("<a class=\"button\" href=\"javascript:;\" data-url=\""+adminPrivilege.getUrl() + "\" >"+adminPrivilege.getName()+"</a>&nbsp;");
			adminPrivilege = null;
		}
		htmls.append("</td>");
		htmls.append("</tr>");
		
		htmls.append("<tr><td>区域</td>");
/*		if(getPrivilege().containsKey(PrivilegeConst.ADMIN_PRIVILEGE_INITAREA_REDIS)){				
			adminPrivilege = getPrivilege().get(PrivilegeConst.ADMIN_PRIVILEGE_INITAREA_REDIS);
*/			htmls.append("<td>");
			htmls.append("<a class=\"button\"  href=\"javascript:;\" data-url=\""+"/initArea/initAreaRedis.do" + "\" >"+"初始化Redis"+"</a>&nbsp;");
			htmls.append("</td>");
/*			adminPrivilege = null;
		}
*/		htmls.append("</tr>");

		htmls.append("<tr><td>前台首页</td>");
		htmls.append("<td>");
		htmls.append("<a class=\"button\"  href=\"javascript:;\" data-url=\""+"/initPc/initIndexHtml.do" + "\" >"+"生成首页HTML"+"</a>&nbsp;");
		htmls.append("</td>");
		htmls.append("</tr>");
		request.setAttribute("htmls", htmls);
		return "/config/init/index";
	}

}
