package com.yule.action;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.yule.admin.query.AdminNoticeQuery;
import com.yule.admin.service.IAdminNoticeService;
import com.yule.admin.service.IAdminPrivilegeService;
import com.yule.admin.service.ICompanyAddressService;
import com.yule.admin.service.ICompanyService;
import com.yule.admin.vo.AdminUserVO;
import com.yule.common.BaseAction;
import com.yule.constant.DeleteConst;
import com.yule.constant.MessageConst;
import com.yule.constant.NoticeConst;
import com.yule.constant.PageConst;
import com.yule.enumerate.DateStyle;
import com.yule.exception.YuleException;
import com.yule.mongo.admin.service.IAdminMessageMongo;
import com.yule.mongo.pojo.AdminMessage;
import com.yule.mongo.query.AdminMessageQuery;
import com.yule.pojo.AdminNotice;
import com.yule.pojo.AdminPrivilege;
import com.yule.util.DateUtil;
import com.yule.vo.Page;

@Controller
@Scope("prototype")
public class IndexAction extends BaseAction {
	
	@Autowired
	private IAdminPrivilegeService adminPrivilegeServiceImpl;
	
	@Autowired 
	private IAdminMessageMongo adminMessageMongoImpl;
	
	@Autowired
	private ICompanyService companyServiceImpl;
	
	@Autowired
	private ICompanyAddressService companyAddressServiceImpl;
	
	@Autowired
	private IAdminNoticeService adminNoticeService;
	
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String index() throws Exception{
		try{
			AdminUserVO adminUser = getAdminUser();
			String adminRoleId = adminUser.getAdmin_role_id();
			List<AdminPrivilege> lists = adminPrivilegeServiceImpl.findAdminPrivilegeParentListByAdminRoleId(adminRoleId, null);
			if(null!=lists&&lists.size()>0){
				StringBuffer htmls = new StringBuffer("");
				
				List<AdminPrivilege> privilegeLists = null;
//				int i=0;
//				int j=0;
				for(AdminPrivilege adminPrivilege:lists){
					
//					if(i==0){
//						htmls.append("<li><a href=\""+adminPrivilege.getUrl()+"\" class=\"nav-top-item current\">"+adminPrivilege.getName()+"</a>");
//					}else{
						htmls.append("<li><a href=\""+adminPrivilege.getUrl()+"\" class=\"nav-top-item\">"+adminPrivilege.getName()+"</a>");
//					}
					
					privilegeLists = adminPrivilegeServiceImpl.findAdminPrivilegeParentListByAdminRoleId(adminRoleId,adminPrivilege.getId());
					
					if(null!=privilegeLists&&privilegeLists.size()>0){
						htmls.append("<ul>");
						
						for(AdminPrivilege privilege:privilegeLists){
//							if(j==0){
//								htmls.append("<li><a class=\"current\" href=\""+privilege.getUrl()+"\" target=\"mainFrame\">"+privilege.getName()+"</a></li>");
//							}else{
								htmls.append("<li><a href=\""+privilege.getUrl()+"\" target=\"mainFrame\">"+privilege.getName()+"</a></li>");
//							}
//							j++;
						}
						htmls.append("</ul>");
						privilegeLists.clear();
						privilegeLists = null;
//						j = 0;
					}
					htmls.append("</li>");
//					i++;
				}	
				lists.clear();
				lists=null;
				request.setAttribute("adminPrivilegeHtml", htmls);
			}
			request.setAttribute("adminMessageNotRead", adminMessageMongoImpl.findAdminMessageCountByIsRead(adminUser.getId(),MessageConst.IS_READ_FALSE));
			request.setAttribute("adminUser", adminUser);
		}catch(Exception e){
			new YuleException(e);
			e.printStackTrace();
		}finally{
			request.setAttribute("mainPath","/home.do");
		}
		return "index";
	}
	
	/**
	 * 获取消息列表
	 * @Title: findAdminNotice
	 * @Description: TODO
	 * @return
	 * @throws Exception
	 * @return: String
	 */
	@RequestMapping(value = "/home")
	public String findAdminNotice() throws Exception{
		int pageNo = 1;
		try {
			AdminNoticeQuery adminNoticeQuery = new AdminNoticeQuery();
			adminNoticeQuery.setType(NoticeConst.NOTICE_TYPE_ADMIN);
			adminNoticeQuery.setIs_delete(DeleteConst.IS_DELETE_TRUE);
			Page<AdminNotice> page = adminNoticeService.findAdminNoticePage(adminNoticeQuery,PageConst.PAGE_SIZE_FIVE, pageNo);
			StringBuffer htmls = new StringBuffer();
			List<AdminNotice> adminNotices = page.getDatas();
			htmls.append("<tbody>");	
			if(null != adminNotices && adminNotices.size()>0){
				for (AdminNotice adminNotice : adminNotices) {
	                htmls.append("<tr>");
	                htmls.append("<td><a href=\"/adminNotice/showNotice.do?id="+ adminNotice.getId() + "\" >" + adminNotice.getTitle() + "</a></td>");
	                htmls.append("<td>" + DateUtil.DateToString(adminNotice.getCreate_time(), DateStyle.YYYY_MM_DD_HH_MM_SS_EN) + "</td>");
                    htmls.append("</tr>");
				}
				if(page.getRowCount()>PageConst.PAGE_SIZE_FIVE){
					htmls.append("<tr>");
					htmls.append("<td class=\"td-center-style\" colspan=\"4\" ><a href=\"/adminNotice/findAdminNoticeIsAdmin.do\" class=\"mianright\">更多>></a></td>");
					htmls.append("</tr>");
				}
				adminNotices.clear();
				adminNotices = null;
			}else{
				htmls.append("<tr>");
				htmls.append("<td class=\"td-center-style\" colspan=\"4\" >暂无通知</td>");
				htmls.append("</tr>");
			}
			page=null;
			htmls.append("</tbody>");
			request.setAttribute("htmls", htmls);
			
			StringBuffer adminMessageHtmls = new StringBuffer("");
			AdminMessageQuery adminMessageMongoQuery = new AdminMessageQuery();
			adminMessageMongoQuery.setAdmin_user_id(getAdminUserId());
			adminMessageMongoQuery.setIs_read(MessageConst.IS_READ_FALSE);
			Page<AdminMessage> adminMessagePage = adminMessageMongoImpl.findAdminMessagePage(adminMessageMongoQuery, PageConst.PAGE_SIZE_TEN, pageNo);
			List<AdminMessage> adminMessages = adminMessagePage.getDatas();
			adminMessageHtmls.append("<tbody>");
			if(null != adminMessages && adminMessages.size()>0){
				for (AdminMessage adminMessage : adminMessages) {
					adminMessageHtmls.append("<tr>");
					adminMessageHtmls.append("<td><a href=\"/adminMessage/findAdminMessageById.do?id="+ adminMessage.getId() + "\" >" + adminMessage.getTitle() + "</a></td>");
					adminMessageHtmls.append("<td>" + DateUtil.DateToString(adminMessage.getCreate_time(), DateStyle.YYYY_MM_DD_HH_MM_SS_EN) + "</td>");
                    adminMessageHtmls.append("</tr>");
				}
				if(adminMessagePage.getRowCount()>PageConst.PAGE_SIZE_TEN){
					adminMessageHtmls.append("<tr>");
					adminMessageHtmls.append("<td class=\"td-center-style\" colspan=\"4\" ><a href=\"/adminMessage/findAdminMessage.do\" class=\"mianright\">更多>></a></td>");
					adminMessageHtmls.append("</tr>");
				}
				adminMessages.clear();
				adminMessages=null;
			}else{
				adminMessageHtmls.append("<tr>");
				adminMessageHtmls.append("<td class=\"td-center-style\" colspan=\"4\" >暂无消息</td>");
				adminMessageHtmls.append("</tr>");
			}
			adminMessageHtmls.append("</tbody>");
			request.setAttribute("adminMessageHtmls", adminMessageHtmls);
			
		} catch (Exception e) {
			new YuleException("获取通知消息列表【findAdminNotice】", e);
			throw e;
		}
		return "home";
	}
}
