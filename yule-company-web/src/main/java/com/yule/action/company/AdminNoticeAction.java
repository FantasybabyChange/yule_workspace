package com.yule.action.company;
import java.util.List;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.yule.common.BaseAction;
import com.yule.company.query.AdminNoticeQuery;
import com.yule.company.service.IAdminNoticeService;
import com.yule.constant.PageConst;
import com.yule.constant.StatusConst;
import com.yule.enumerate.DateStyle;
import com.yule.enumerate.LogEnum;
import com.yule.exception.YuleException;
import com.yule.pojo.AdminNotice;
import com.yule.util.CompanyLogUtil;
import com.yule.util.DateUtil;
import com.yule.util.PaginationUtil;
import com.yule.vo.AdminNoticeAttachmentVO;
import com.yule.vo.Page;

@Controller
@Scope("prototype")
@RequestMapping("/adminNotice")
public class AdminNoticeAction extends BaseAction {
	
	@Autowired
	private IAdminNoticeService adminNoticeServiceImpl;
	/**
	 * 消息分页数据
	 */
	@RequestMapping(value = "/findAdminNotice")
	public String findAdminMessagePage(AdminNoticeQuery adminNoticeQuery,@RequestParam(value = "pageNo", required = false) Integer pageNo) throws Exception{
		if(null==pageNo){
			return "company/notice/index";
		}
		if(null==adminNoticeQuery){
			adminNoticeQuery = new AdminNoticeQuery();
		}
		JSONObject obj = new JSONObject();
		try {
			Page<AdminNotice> page = adminNoticeServiceImpl.findAdminNoticePage(adminNoticeQuery, PageConst.PAGE_SIZE_TEN, pageNo);
			StringBuffer tfoot = new StringBuffer();
			StringBuffer tbody = new StringBuffer();
			tfoot.append("<tr><td colspan=\"7\">");
			tfoot.append("<div class=\"bulk-actions align-left\"></div>");
			tfoot.append(PaginationUtil.getPaginationHtml(page));
			tfoot.append("<div class=\"clear\"></div>");
			tfoot.append("</td></tr>");
			if(page.getRowCount()>0){
				for(AdminNotice adminNotice: page.getDatas()){
					tbody.append("<tr>");
					tbody.append("<td><a href=\"#notice-wizard\"  data-toggle=\"modal\"  read-toggle=\"modal\" modal-notice=\"\"modal-dialog=\""+adminNotice.getId()+"\" >" + adminNotice.getTitle() + "</a></td>");
					tbody.append("<td>" + DateUtil.DateToString(adminNotice.getCreate_time(), DateStyle.YYYY_MM_DD_HH_MM_SS_EN) + "</td>");
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
			CompanyLogUtil.insertLog("查询系统通知", getCompanyUser(), LogEnum.QUERY);
		} catch (Exception e) {
			new YuleException(e);
			throw e;
		} 
		return null;
	}
	@RequestMapping(value = "/findAdminNoticeById",method=RequestMethod.POST)
	public String findAdminNoticeById(@RequestParam(value = "id", required = false) String id) throws Exception{
		JSONObject obj = new JSONObject();
		try {
			 AdminNotice admiNotice = this.adminNoticeServiceImpl.findAdminNoticeVOById(id);
			if(admiNotice !=null){
				obj.put("title", admiNotice.getTitle());
				obj.put("content", admiNotice.getContent());
				obj.put("createTime", DateUtil.DateToString(admiNotice.getCreate_time(), DateStyle.YYYY_MM_DD_HH_MM_SS_EN));
				List<AdminNoticeAttachmentVO> attachments = admiNotice.getAdminNoticeAttachmentVOs();
				StringBuffer attachmentHtmls = new StringBuffer();
				if (attachments != null && attachments.size() > 0) {
					for (AdminNoticeAttachmentVO attachment : attachments) {
						if (null!=attachment.getAttachment_id()) {
							attachmentHtmls.append("<a href=\""+attachment.getPath()+attachment.getSystem_name()+"\">"+attachment.getName()+"</a></br>");		
						}
					}
				}
				obj.put("attachmentHtmls", attachmentHtmls.toString());
			}else{
				obj.put("status", StatusConst.STATUS_FALSE);
			}
		} catch (Exception e) {
			new YuleException("通过ID查询消息出现异常【findAdminMessage】",e);
			throw e;
		}finally{
			outputResult(obj.toString());
			obj.clear();
			obj = null;
			CompanyLogUtil.insertLog("findAdminNoticeById", getCompanyUser(), LogEnum.QUERY);
		}
		return null;
	}
}
