package com.yule.util;

import java.util.List;

import org.springframework.util.StringUtils;

import com.yule.company.service.ICompanyPrivilegeService;
import com.yule.constant.CustomBeanConst;
import com.yule.constant.ShowConst;
import com.yule.constant.TimeConst;
import com.yule.pojo.CompanyPrivilege;
import com.yule.redis.constant.RedisConst;
import com.yule.redis.util.JedisUtil;

public class LeftCompanyUtil {
	
	public static String getCompanyMenuHtml(String companyUserId) throws  Exception{
		String key = RedisConst.COMPANY_PRIVILEGE+companyUserId;
		if(!JedisUtil.getInstance().exists(key)){
			ICompanyPrivilegeService companyPrivilegeServiceImpl = (ICompanyPrivilegeService)CustomBeanFactory.getContext(CustomBeanConst.COMPANY_SERVICE_PATHS).getBean("companyPrivilegeServiceImpl");
			List<CompanyPrivilege> lists = companyPrivilegeServiceImpl.findCompanyPrivilegeListByCompanyUserId(companyUserId,ShowConst.IS_SHOW_TRUE);
			if(null!=lists&&lists.size()>0){
				StringBuffer menuHtmls = new StringBuffer();
				menuHtmls.append("<li class=\"/index.do\">");
				menuHtmls.append("<a href=\"/index.do\">");
				menuHtmls.append("<i class=\"menu-icon fa fa-tachometer\"></i>");
				menuHtmls.append("<span class=\"menu-text\"> 首页 </span>");
				menuHtmls.append("</a>");
				menuHtmls.append("<b class=\"arrow\"></b>");
				menuHtmls.append("</li>");
				int i=0;
				StringBuffer menuTwoHtmls = new StringBuffer("");
				StringBuffer menuTwoOpenHtmls = new StringBuffer("");
				StringBuffer companyPrivilegeUrl = new StringBuffer("");
				StringBuffer companyPrivilegeName = new StringBuffer("");
				StringBuffer companyPrivilegeTwoUrl = new StringBuffer("");
				for(CompanyPrivilege companyPrivilege :lists){
					if(StringUtils.isEmpty(companyPrivilege.getParent_id())){
						companyPrivilegeUrl.append(companyPrivilege.getUrl());
						companyPrivilegeName.append(companyPrivilege.getName());
						for(CompanyPrivilege companyPrivilegeTwo :lists){
							if(!StringUtils.isEmpty(companyPrivilegeTwo.getParent_id())&&companyPrivilege.getId().equals(companyPrivilegeTwo.getParent_id())){
								companyPrivilegeTwoUrl.append(companyPrivilegeTwo.getUrl());
								if(i==0){
									menuTwoHtmls.append("<ul class=\"submenu\">");
									menuTwoOpenHtmls.append("<li class=\"parent-"+companyPrivilegeTwoUrl+"\">");
								}
								menuTwoHtmls.append("<li class=\""+companyPrivilegeTwoUrl+"\">");
								menuTwoHtmls.append("<a href=\""+companyPrivilegeTwoUrl+"\">");
								menuTwoHtmls.append("<i class=\"menu-icon fa fa-caret-right\"></i>");
								menuTwoHtmls.append(companyPrivilegeTwo.getName());
								menuTwoHtmls.append("</a>");
								menuTwoHtmls.append("<b class=\"arrow\"></b>");
								menuTwoHtmls.append("</li>");
								companyPrivilegeTwoUrl.setLength(0);
								i++;
							}
						}
						if(i>0){
							menuHtmls.append(menuTwoOpenHtmls);
							menuHtmls.append("<a href=\"javascript:;\" class=\"dropdown-toggle\">");
							menuHtmls.append("<i class=\"menu-icon fa fa-list\"></i>");
							menuHtmls.append("<span class=\"menu-text\"> "+companyPrivilegeName);
							menuHtmls.append("<span class=\"badge badge-primary\">"+i+"</span>");
							menuHtmls.append("</span>");
							menuHtmls.append("<b class=\"arrow fa fa-angle-down\"></b>");
							menuHtmls.append("</a>");
							menuHtmls.append("<b class=\"arrow\"></b>");
							menuHtmls.append(menuTwoHtmls);
							menuHtmls.append("</ul>");
							menuHtmls.append("</li>");
						}else{
							menuHtmls.append("<li class=\""+companyPrivilegeUrl+"\">");
							menuHtmls.append("<a href=\""+companyPrivilegeUrl+"\">");
							menuHtmls.append("<i class=\"menu-icon fa fa-tachometer\"></i>");
							menuHtmls.append("<span class=\"menu-text\"> "+companyPrivilegeName+" </span>");
							menuHtmls.append("</a>");
							menuHtmls.append("<b class=\"arrow\"></b>");
							menuHtmls.append("</li>");
						}
						i = 0;
						menuTwoHtmls.setLength(0);
						menuTwoOpenHtmls.setLength(0);
						companyPrivilegeUrl.setLength(0);
						companyPrivilegeName.setLength(0);
					}
				}
				JedisUtil.getInstance().set(key, menuHtmls.toString(),TimeConst.ONE_HOUR);
				lists.clear();
				lists = null;
			}
			companyPrivilegeServiceImpl = null;
		}
		String privilegeHtml = JedisUtil.getInstance().get(key);
		return privilegeHtml;
	}
	
}