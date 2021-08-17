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

import com.yule.admin.param.InsertAdminNoticeParam;
import com.yule.admin.query.AdminNoticeQuery;
import com.yule.admin.service.IAdminNoticeService;
import com.yule.common.BaseAction;
import com.yule.constant.ActionReturnConst;
import com.yule.constant.DeleteConst;
import com.yule.constant.ErrorConst;
import com.yule.constant.NoticeConst;
import com.yule.constant.PageConst;
import com.yule.constant.PlaceholderConst;
import com.yule.constant.PrivilegeConst;
import com.yule.enumerate.DateStyle;
import com.yule.enumerate.LogEnum;
import com.yule.exception.YuleException;
import com.yule.pojo.AdminNotice;
import com.yule.pojo.AdminPrivilege;
import com.yule.util.AdminLogUtil;
import com.yule.util.DateUtil;
import com.yule.util.PaginationUtil;
import com.yule.util.StringUtil;
import com.yule.vo.AdminNoticeAttachmentVO;
import com.yule.vo.Page;

@Controller
@Scope("prototype")
@RequestMapping("/adminNotice")
public class AdminNoticeAction extends BaseAction {
	
	@Autowired
	private IAdminNoticeService adminNoticeServiceImpl;
	
	/**
	 * 获取通知分页数据
	 */
	@RequestMapping(value = "/findAdminNotice",method = RequestMethod.GET)
	public String findAdminNoticePage(AdminNoticeQuery adminNoticeQuery,@RequestParam(value = "pageNo", required = false) Integer pageNo) throws Exception{
		if(null==pageNo||pageNo<1){
			pageNo = 1;
		}
		try {
		    Page<AdminNotice> page = adminNoticeServiceImpl.findAdminNoticePage(adminNoticeQuery,PageConst.PAGE_SIZE_TEN, pageNo);
		    StringBuffer htmls = new StringBuffer();
		    htmls.append("<tfoot><tr><td colspan=\"5\">");
			htmls.append("<div class=\"bulk-actions align-left\"></div>");
			htmls.append(PaginationUtil.getPaginationHtml(page));
			htmls.append("<div class=\"clear\"></div>");
			htmls.append("</td></tr></tfoot>");
			htmls.append("<tbody>");
			AdminPrivilege adminPrivilege = null;
			StringBuffer[] privilegeHtml =new StringBuffer[]{new StringBuffer(""),new StringBuffer("")};
			if(getPrivilege().containsKey(PrivilegeConst.ADMIN_PRIVILEGE_CODE_DETAILS)){	
				adminPrivilege = getPrivilege().get(PrivilegeConst.ADMIN_PRIVILEGE_CODE_DETAILS);
				privilegeHtml[1].append("<a class=\"button\" href=\""+adminPrivilege.getUrl()+"?id="+ PlaceholderConst.ID + "\" >"+adminPrivilege.getName()+"</a>&nbsp;");
				adminPrivilege = null;
			}
			if(getPrivilege().containsKey(PrivilegeConst.ADMIN_PRIVILEGE_CODE_UPDATE)){	
				adminPrivilege = getPrivilege().get(PrivilegeConst.ADMIN_PRIVILEGE_CODE_UPDATE);
				privilegeHtml[0].append("<a class=\"button\" href=\""+adminPrivilege.getUrl()+"?id="+ PlaceholderConst.ID + "\" >"+adminPrivilege.getName()+"</a>&nbsp;");
				adminPrivilege = null;
			}
            if(getPrivilege().containsKey(PrivilegeConst.ADMIN_PRIVILEGE_CODE_DELETE)){	
            	adminPrivilege = getPrivilege().get(PrivilegeConst.ADMIN_PRIVILEGE_CODE_DELETE);
            	privilegeHtml[0].append("<a class=\"button\" data-unrestore=\"\" data-id=\""+PlaceholderConst.ID+"\" data-url=\""+adminPrivilege.getUrl()+ "\" >"+adminPrivilege.getName()+"</a>");
            	adminPrivilege = null;
			}
			if(page.getRowCount()>0){
				StringBuffer id = new StringBuffer("");
				for (AdminNotice adminNotice : page.getDatas()) {
					id.append(adminNotice.getId());
					htmls.append("<tr>");
					htmls.append("<td>" + adminNotice.getTitle() + "</td>");
					htmls.append("<td>"+StringUtil.cut(adminNotice.getContent(), 20)+"</td>");
					htmls.append("<td>"+NoticeConst.NOTICE_TYPE[adminNotice.getType()]+"</td>");
					htmls.append("<td>" + DateUtil.DateToString(adminNotice.getCreate_time(), DateStyle.YYYY_MM_DD_HH_MM_SS_EN) + "</td>");
					htmls.append("<td>");
					htmls.append(privilegeHtml[adminNotice.getIs_delete()].toString().replace(PlaceholderConst.ID, id));
					htmls.append("</td></tr>");
					id.setLength(0);
				}
				id=null;
				page.cleanDatas();
				page=null;
			}else{
				htmls.append("<tr>");
				htmls.append("<td class=\"td-center-style\" colspan=\"8\">暂无数据</td>");
				htmls.append("</tr>");
			}
			htmls.append("</tbody>");
			request.setAttribute("privilegeHtml", privilegeHtml[1]);
			request.setAttribute("htmls", htmls);
			request.setAttribute("adminNoticeQuery", adminNoticeQuery);
			privilegeHtml = null;
			AdminLogUtil.insertLog("获取通知消息列表", getAdminUser(), LogEnum.QUERY);
		} catch (Exception e) {
			new YuleException("获取通知消息列表【findAdminNoticePage】",e);
			throw e;
		}
		return "admin/notice/index";
	}
	
	
	/**
	 * 系统用户获取通知分页数据 不能更新 新增
	 */
	@RequestMapping(value = "/findAdminNoticeIsAdmin",method = RequestMethod.GET)
	public String findAdminNoticePageIsAdmin(AdminNoticeQuery adminNoticeQuery,@RequestParam(value = "pageNo", required = false) Integer pageNo) throws Exception{
		if(null==pageNo||pageNo<1){
			pageNo = 1;
		}
		try {
			adminNoticeQuery.setType(NoticeConst.NOTICE_TYPE_ADMIN);
			adminNoticeQuery.setIs_delete(DeleteConst.IS_DELETE_TRUE);
		    Page<AdminNotice> page = adminNoticeServiceImpl.findAdminNoticePage(adminNoticeQuery,PageConst.PAGE_SIZE_TEN, pageNo);
		    StringBuffer htmls = new StringBuffer();
		    htmls.append("<tfoot><tr><td colspan=\"5\">");
			htmls.append("<div class=\"bulk-actions align-left\"></div>");
			htmls.append(PaginationUtil.getPaginationHtml(page));
			htmls.append("<div class=\"clear\"></div>");
			htmls.append("</td></tr></tfoot>");
			htmls.append("<tbody>");
			StringBuffer privilegeHtml =new StringBuffer();
			privilegeHtml.append("<a class=\"button\" href=\"/adminNotice/showNotice.do?id="+ PlaceholderConst.ID + "\" >查看</a>&nbsp;");
			if(page.getRowCount()>0){
				StringBuffer id = new StringBuffer("");
				for (AdminNotice adminNotice : page.getDatas()) {
					id.append(adminNotice.getId());
					htmls.append("<tr>");
					htmls.append("<td>" + adminNotice.getTitle() + "</td>");
					htmls.append("<td>"+StringUtil.cut(adminNotice.getContent(), 20)+"</td>");
					htmls.append("<td>" + DateUtil.DateToString(adminNotice.getCreate_time(), DateStyle.YYYY_MM_DD_HH_MM_SS_EN) + "</td>");
					htmls.append("<td>");
					htmls.append(privilegeHtml.toString().replace(PlaceholderConst.ID, id));
					htmls.append("</td></tr>");
					id.setLength(0);
				}
				id=null;
				page.cleanDatas();
				page=null;
			}else{
				htmls.append("<tr>");
				htmls.append("<td class=\"td-center-style\" colspan=\"8\">暂无数据</td>");
				htmls.append("</tr>");
			}
			htmls.append("</tbody>");
			request.setAttribute("htmls", htmls);
			privilegeHtml = null;
			AdminLogUtil.insertLog("获取通知消息列表", getAdminUser(), LogEnum.QUERY);
		} catch (Exception e) {
			new YuleException("获取通知消息列表【findAdminNoticeIsAdmin】",e);
			throw e;
		}
		return "admin/notice/notices";
	}
	
	@RequestMapping(value = "/insertAdminNotice",method = RequestMethod.POST)
	public String insertAdminNotice(InsertAdminNoticeParam adminNoticeParam) throws Exception{
		try {
			adminNoticeServiceImpl.insertAdminNotice(adminNoticeParam);
			AdminLogUtil.insertLog("添加通知消息", getAdminUser(), LogEnum.INSERT);
		} catch (Exception e) {
			new YuleException("添加通知消息【insertAdminNotice】",e);
			throw e;
		}
		return ActionReturnConst.REDIRECT+"/adminNotice/findAdminNotice.do";
	}
	
	@RequestMapping(value = "/deleteAdminNotice",method = RequestMethod.POST)
	public String deleteAdminNotice(@RequestParam(value="id",required=false)String id) throws Exception {
		JSONObject obj = new JSONObject();
		obj.put("status",ErrorConst.STATUS_ERROR);
		try {
			boolean flag = adminNoticeServiceImpl.deleteAdminNoticeById(id);
			obj.put("status", flag);
			AdminLogUtil.insertLog("删除用户通知消息", getAdminUser(), LogEnum.DELETE);
		} catch (Exception e) {
			new YuleException("删除通知消息【deleteAdminNotice】",e);
			throw e;
		} finally{
			outputResult(obj.toString());
			obj.clear();
			obj=null;
		}
		return null;
	}
	
	
	@RequestMapping(value = "/findAdminNoticeById",method = RequestMethod.GET)
	public String findAdminNoticeById(@RequestParam(value="id",required=false) String id) throws Exception{
		try {
		    AdminNotice adminNotice = adminNoticeServiceImpl.findAdminNoticeVOById(id);
		    List<AdminNoticeAttachmentVO> adminNoticeAttachments =adminNotice.getAdminNoticeAttachmentVOs();
		    StringBuffer htmls = new StringBuffer("");
			htmls.append("<ul style=\"padding-bottom: 60px;\" id=\"upload-ul\">");
			for(AdminNoticeAttachmentVO adminNoticeAttachmentVo: adminNoticeAttachments){
				if (adminNoticeAttachmentVo.getAttachment_id()!=null) {
					htmls.append("<li style=\"float: left\">");
					htmls.append("<div><img src=\"http://static.yuleing.com/admin/filetype/"+adminNoticeAttachmentVo.getType()+".gif\" /></div>");
				    htmls.append("<div class=\"title\">");
					htmls.append(StringUtil.cut(adminNoticeAttachmentVo.getName()+"."+adminNoticeAttachmentVo.getType(), 8)+"</div>");
					htmls.append("<div ><a class=\"button\" href=\""+adminNoticeAttachmentVo.getPath()+adminNoticeAttachmentVo.getSystem_name()+"\" >下载</a>&nbsp;<a class=\"button\" gallery-delete=\"\" href=\"javascript:;\"  data-url=\"/adminNoticeAttachment/deleteAdminNoticeAttachment.do\" data-id=\""+adminNoticeAttachmentVo.getAttachment_id()+"\"  />删除</a>&nbsp;</div>");
					htmls.append("</li>");
				}
			}
			htmls.append("</ul>");
			request.setAttribute("htmls", htmls);
			request.setAttribute("adminNotice", adminNotice);
			AdminLogUtil.insertLog("查看用户等级", getAdminUser(), LogEnum.QUERY);
		} catch (Exception e) {
			new YuleException("根据通知id获取通知对象【findAdminNoticeById】",e);
			throw e;
		}
		return "admin/notice/update";
	}
	
	@RequestMapping(value = "/updateAdminNotice",method = RequestMethod.POST)
	public String updateAdminNotice(@ModelAttribute("adminNotice") AdminNotice adminNotice) throws Exception{
		try {
			adminNoticeServiceImpl.updateAdminNotice(adminNotice);
			AdminLogUtil.insertLog("修改通知消息", getAdminUser(), LogEnum.UPDATE);
		} catch (Exception e) {
			new YuleException("修改通知消息【updateAdminNotice】",e);
			throw e;
		}
		return ActionReturnConst.REDIRECT+"/adminNotice/findAdminNotice.do";
	}
	
	/**
	 * 根据通知id显示通知信息
	 */
	@RequestMapping(value = "/showNotice",method = RequestMethod.GET)
	public String showNotice(@RequestParam(value="id",required=false) String id) throws Exception{
		try {
		    AdminNotice adminNotice = adminNoticeServiceImpl.findAdminNoticeVOById(id);
		    List<AdminNoticeAttachmentVO> adminNoticeAttachmentVOs =adminNotice.getAdminNoticeAttachmentVOs();
		    StringBuffer htmls = new StringBuffer("");
			htmls.append("<ul style=\"padding-bottom: 60px;\" id=\"upload-ui\">");
			for(AdminNoticeAttachmentVO adminNoticeAttachmentVo: adminNoticeAttachmentVOs){
				if (adminNoticeAttachmentVo.getAttachment_id()!=null) {
					htmls.append("<li style=\"float: left\">");
					htmls.append("<div><img src=\"http://static.yuleing.com/admin/filetype/"+adminNoticeAttachmentVo.getType()+".gif\" /></div>");
				    htmls.append("<div class=\"title\">");
					htmls.append(StringUtil.cut(adminNoticeAttachmentVo.getName()+"."+adminNoticeAttachmentVo.getType(), 8)+"</div>");
					htmls.append("<div ><a class=\"button\" href=\""+adminNoticeAttachmentVo.getPath()+adminNoticeAttachmentVo.getSystem_name()+"\" >下载</a>&nbsp;</div>");
					htmls.append("</li>");
				}
			}
			htmls.append("</ul>");
			request.setAttribute("htmls", htmls);
			request.setAttribute("adminNotice", adminNotice);
			AdminLogUtil.insertLog("根据通知id显示通知信息", getAdminUser(), LogEnum.QUERY);
		} catch (Exception e) {
			new YuleException("根据通知id显示通知信息【showNotice】",e);
			throw e;
		}
		return "admin/notice/showAdminNotice";
	}
	
}
